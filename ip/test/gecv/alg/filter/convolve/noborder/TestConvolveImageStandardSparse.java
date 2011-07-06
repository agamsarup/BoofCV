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

package gecv.alg.filter.convolve.noborder;

import gecv.alg.filter.convolve.ConvolveImageNoBorder;
import gecv.alg.filter.convolve.KernelFactory;
import gecv.alg.misc.ImageTestingOps;
import gecv.core.image.ConvertImage;
import gecv.core.image.GeneralizedImageOps;
import gecv.struct.convolve.Kernel1D_F32;
import gecv.struct.convolve.Kernel1D_I32;
import gecv.struct.image.ImageBase;
import gecv.struct.image.ImageFloat32;
import gecv.struct.image.ImageUInt8;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * @author Peter Abeles
 */
public class TestConvolveImageStandardSparse {

	Random rand = new Random(0xFF);

	static int width = 10;
	static int height = 12;
	static int kernelRadius = 2;
	static int targetX = 5;
	static int targetY = 6;

	static Kernel1D_F32 kernelF32;
	static Kernel1D_I32 kernelI32;
	static float expectedOutput;

	/**
	 * Automatically compares all the box filters against a generalize convolution
	 */
	@Test
	public void compareToGeneral() {
		Method methods[] = ConvolveImageStandardSparse.class.getMethods();

		// sanity check to make sure the functions are being found
		int numFound = 0;
		for (Method m : methods) {
			// search for methods which have equivalent in the other class
			Class<?> paramTypes[] = m.getParameterTypes();
			if (paramTypes.length < 3)
				continue;

			checkMethod(m, width, height, kernelRadius, rand);
			numFound++;
		}

		// update this as needed when new functions are added
		assertEquals(5, numFound);
	}

	private void checkMethod(Method method, int width, int height, int kernelRadius, Random rand) {
		ImageUInt8 seedImage = new ImageUInt8(width,height);
		ImageTestingOps.randomize(seedImage,rand,0,255);

		// creates a floating point image with integer elements
		ImageFloat32 floatImage = new ImageFloat32(width,height);
		ConvertImage.convert(seedImage,floatImage);

		kernelI32 = KernelFactory.gaussian1D_I32(kernelRadius);
		kernelF32 = new Kernel1D_F32(kernelI32.width);
		for( int i = 0; i < kernelI32.width; i++ )
			kernelF32.data[i] = kernelI32.data[i];

		expectedOutput = computeExpected(floatImage);
		boolean isFloatingKernel = method.getParameterTypes()[0] == Kernel1D_F32.class;

		ImageBase<?> inputImage = GeneralizedImageOps.convert(floatImage,null,method.getParameterTypes()[2]);
		Object inputKernel = isFloatingKernel ? kernelF32 : kernelI32;
		Object inputStorage = isFloatingKernel ? new float[kernelI32.width] : new int[ kernelI32.width];

		checkResults(method,inputKernel,inputImage,inputStorage);
	}

	private void checkResults(Method method, Object inputKernel, ImageBase<?> inputImage, Object inputStorage) {
		try {
			Number result;
			if( method.getParameterTypes().length == 6 )
				result = (Number)method.invoke(null,inputKernel,inputKernel,inputImage,targetX,targetY,inputStorage);
			else
				result = (Number)method.invoke(null,inputKernel,inputKernel,inputImage,targetX,targetY,inputStorage,1,1);
			assertEquals((int)expectedOutput,result.intValue());
		} catch (IllegalAccessException e) {                                                         
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private float computeExpected( ImageFloat32 image ) {
		ImageFloat32 temp = new ImageFloat32(image.width,image.height);
		ImageFloat32 temp2 = new ImageFloat32(image.width,image.height);

		ConvolveImageNoBorder.horizontal(kernelF32,image,temp,true);
		ConvolveImageNoBorder.vertical(kernelF32,temp,temp2,true);

		return temp2.get(targetX,targetY);
	}
}
