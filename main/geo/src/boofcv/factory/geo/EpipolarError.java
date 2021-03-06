/*
 * Copyright (c) 2011-2013, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.factory.geo;

/**
 * Used to specify the type of error function used when optimizing multiview geometric functions
 *
 * @author Peter Abeles
 */
public enum EpipolarError {
	/**
	 * Simple to compute error function.  Has a minimum at the global solution, but the cost
	 * does not try to compute any "optimal" error
	 */
	SIMPLE,
	/**
	 * Second order approximation of the "best" error function.  Often a good trade between
	 * accuracy and efficiency.
	 */
	SAMPSON,
}
