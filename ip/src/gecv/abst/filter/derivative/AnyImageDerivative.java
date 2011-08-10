/*
 * Copyright 2011 Peter Abeles
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gecv.abst.filter.derivative;

import gecv.abst.filter.convolve.ConvolveInterface;
import gecv.abst.filter.convolve.FactoryConvolve;
import gecv.alg.filter.kernel.FactoryKernel;
import gecv.core.image.ImageGenerator;
import gecv.core.image.border.BorderType;
import gecv.struct.GecvDefaults;
import gecv.struct.convolve.Kernel1D;
import gecv.struct.convolve.Kernel2D;
import gecv.struct.image.ImageBase;

/**
 * <p>
 * A helpful class which allows a derivative of any order to be computed from an input image using a simple to use
 * interface.  Higher order derivatives are computed from lower order derivatives.  Derivatives are computed
 * using convolution kernels and thus might not be as efficient as when using functions from {@link FactoryDerivative}.
 * </p>
 *
 * @author Peter Abeles
 */
public class AnyImageDerivative<I extends ImageBase , D extends ImageBase> {

	// filters for computing image derivatives
	private ConvolveInterface<I, D> derivX;
	private ConvolveInterface<I, D> derivY;

	// gaussian blur the derivative image
	private ConvolveInterface<D, D> derivDerivX;
	private ConvolveInterface<D, D> derivDerivY;

	// how the borders are handled
	private BorderType borderDeriv = GecvDefaults.DERIV_BORDER_TYPE;

	private I inputImage;

		// stores computed derivative images
	private D[][] derivatives;
	// if true then
	private boolean[][] stale;

	private ImageGenerator<D> derivGen;

	/**
	 * Constructor for 1D kernels.
	 *
	 * @param deriv 1D convolution kernel for computing derivative along x and y axises.
	 * @param inputType The type of input image.
	 * @param derivGen Generator for derivative images.
	 */
	public AnyImageDerivative( Kernel1D deriv , Class<I> inputType , ImageGenerator<D> derivGen )
	{
		this.derivGen = derivGen;
		Class<D> derivType = derivGen.getType();

		derivX = FactoryConvolve.convolve(deriv,inputType,derivType, borderDeriv,true);
		derivY = FactoryConvolve.convolve(deriv,inputType,derivType, borderDeriv,false);

		derivDerivX = FactoryConvolve.convolve(deriv,derivType,derivType, borderDeriv,true);
		derivDerivY = FactoryConvolve.convolve(deriv,derivType,derivType, borderDeriv,false);
	}

	/**
	 * Constructor for 2D kernels.
	 *
	 * @param derivX 2D convolution kernel for computing derivative along x axis
	 * @param inputType The type of input image.
	 * @param derivGen Generator for derivative images.
	 */
	public AnyImageDerivative( Kernel2D derivX , Class<I> inputType , ImageGenerator<D> derivGen )
	{
		this.derivGen = derivGen;
		Kernel2D derivY = FactoryKernel.transpose(derivX);
		Class<D> derivType = derivGen.getType();

		this.derivX = FactoryConvolve.convolve(derivX,inputType,derivType, borderDeriv);
		this.derivY = FactoryConvolve.convolve(derivY,inputType,derivType, borderDeriv);

		derivDerivX = FactoryConvolve.convolve(derivX,derivType,derivType, borderDeriv);
		derivDerivY = FactoryConvolve.convolve(derivY,derivType,derivType, borderDeriv);
	}

	/**
	 * Constructor for when all derivative filters are specified
	 *
	 * @param derivX Filter for computing derivative along x axis from input image.
	 * @param derivY Filter for computing derivative along y axis from input image.
	 * @param derivXX Filter for computing derivative along x axis from input image.
	 * @param derivYY Filter for computing derivative along y axis from input image.
	 * @param inputType The type of input image.
	 * @param derivGen Generator for derivative images.
	 */
	public AnyImageDerivative(ConvolveInterface<I, D> derivX , ConvolveInterface<I, D> derivY ,
							  ConvolveInterface<D, D> derivXX , ConvolveInterface<D, D> derivYY ,
							  Class<I> inputType , ImageGenerator<D> derivGen )
	{
		this.derivGen = derivGen;

		this.derivX = derivX;
		this.derivY = derivY;

		this.derivDerivX = derivXX;
		this.derivDerivY = derivYY;
	}

	/**
	 * Sets the new input image from which the image derivatives are computed from.
	 *
	 * @param input Input image.
	 */
	public void setInput( I input ) {
		this.inputImage = input;

		// reset the state flag so that everything need sto be computed
		if( stale != null ) {
			for( int i = 0; i < stale.length; i++) {
				boolean a[] = stale[i];
				for( int j = 0; j < a.length; j++ ) {
					a[j] = true;
				}
			}
		}
	}

	/**
	 * Computes derivative images using previously computed lower level derivatives.  Only
	 * computes/declares images as needed.
	 */
	public D getDerivative(boolean... isX) {
		if( derivatives == null ) {
			declareTree(isX.length);
		} else if( isX.length > stale.length ) {
			growTree(isX.length);
		}

		int index = 0;
		int prevIndex = 0;
		for( int level = 0; level < isX.length; level++ ) {
			index |= isX[level] ? 0 : 1 << level;

			if( stale[level][index] ) {
				stale[level][index] = false;
				if( derivatives[level][index] == null ) {
					derivatives[level][index] = derivGen.createInstance(inputImage.getWidth(),inputImage.getHeight());
				} else {
					derivatives[level][index].reshape(inputImage.getWidth(),inputImage.getHeight());
				}

				if( level == 0 ) {
					if( isX[level]) {
						derivX.process(inputImage,derivatives[level][index]);
					} else {
						derivY.process(inputImage,derivatives[level][index]);
					}
				} else {
					D prev = derivatives[level-1][prevIndex];
					if( isX[level]) {
						derivDerivX.process(prev,derivatives[level][index]);
					} else {
						derivDerivY.process(prev,derivatives[level][index]);
					}
				}
			}
			prevIndex = index;
		}

		return derivatives[isX.length-1][index];
	}

	private void declareTree( int maxDerivativeOrder ) {
		derivatives = (D[][])new ImageBase[maxDerivativeOrder][];
		stale = new boolean[maxDerivativeOrder][];

		for( int i = 0; i < maxDerivativeOrder; i++) {
			int N = (int)Math.pow(2,i+1);
			derivatives[i] = (D[])new ImageBase[N];
			stale[i] = new boolean[N];
			for( int j = 0; j < N; j++ ) {
				stale[i][j] = true;
			}
		}
	}

	private void growTree( int maxDerivativeOrder ) {
		D[][] oldDerives = derivatives;
		boolean[][] oldStale = stale;

		declareTree(maxDerivativeOrder);

		int N = oldStale.length;
		for( int i = 0; i < N; i++ ) {
			int M = oldStale[i].length;
			for( int j = 0; j < M; j++ ) {
				derivatives[i][j] = oldDerives[i][j];
				stale[i][j] = oldStale[i][j];
			}
		}

	}
}
