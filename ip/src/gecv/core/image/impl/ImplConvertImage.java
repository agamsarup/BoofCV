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

package gecv.core.image.impl;

import gecv.alg.InputSanityCheck;
import gecv.struct.image.*;

/**
 * <p>
 * Functions for converting between different primitive image types. Numerical values do not change or are closely approximated
 * in these functions.  
 * </p>
 *
 * <p>
 * DO NOT MODIFY: This class was automatically generated by {@link gecv.core.image.impl.GenerateImplConvertImage}
 * </p>
 *
 * @author Peter Abeles
 */
public class ImplConvertImage {

	public static void convert( ImageUInt8 from, ImageInt8 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( byte )( from.data[indexFrom++] & 0xFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			System.arraycopy(from.data, 0, to.data, 0, N);
		}
	}

	public static void convert( ImageUInt8 from, ImageInt16 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( short )( from.data[indexFrom++] & 0xFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( short )( from.data[i] & 0xFF);
			}
		}
	}

	public static void convert( ImageUInt8 from, ImageSInt32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( from.data[indexFrom++] & 0xFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( from.data[i] & 0xFF);
			}
		}
	}

	public static void convert( ImageUInt8 from, ImageFloat32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( float )( from.data[indexFrom++] & 0xFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( float )( from.data[i] & 0xFF);
			}
		}
	}

	public static void convert( ImageUInt8 from, ImageFloat64 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( double )( from.data[indexFrom++] & 0xFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( double )( from.data[i] & 0xFF);
			}
		}
	}

	public static void convert( ImageSInt8 from, ImageInt8 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			System.arraycopy(from.data, 0, to.data, 0, N);
		}
	}

	public static void convert( ImageSInt8 from, ImageInt16 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( short )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( short )( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt8 from, ImageSInt32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt8 from, ImageFloat32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( float )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( float )( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt8 from, ImageFloat64 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( double )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( double )( from.data[i] );
			}
		}
	}

	public static void convert( ImageUInt16 from, ImageInt8 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( byte )( from.data[indexFrom++] & 0xFFFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( byte )( from.data[i] & 0xFFFF);
			}
		}
	}

	public static void convert( ImageUInt16 from, ImageInt16 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( short )( from.data[indexFrom++] & 0xFFFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			System.arraycopy(from.data, 0, to.data, 0, N);
		}
	}

	public static void convert( ImageUInt16 from, ImageSInt32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( from.data[indexFrom++] & 0xFFFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( from.data[i] & 0xFFFF);
			}
		}
	}

	public static void convert( ImageUInt16 from, ImageFloat32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( float )( from.data[indexFrom++] & 0xFFFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( float )( from.data[i] & 0xFFFF);
			}
		}
	}

	public static void convert( ImageUInt16 from, ImageFloat64 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( double )( from.data[indexFrom++] & 0xFFFF);
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( double )( from.data[i] & 0xFFFF);
			}
		}
	}

	public static void convert( ImageSInt16 from, ImageInt8 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( byte )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( byte )( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt16 from, ImageInt16 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			System.arraycopy(from.data, 0, to.data, 0, N);
		}
	}

	public static void convert( ImageSInt16 from, ImageSInt32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt16 from, ImageFloat32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( float )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( float )( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt16 from, ImageFloat64 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( double )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( double )( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt32 from, ImageInt8 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( byte )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( byte )( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt32 from, ImageInt16 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( short )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( short )( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt32 from, ImageFloat32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( float )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( float )( from.data[i] );
			}
		}
	}

	public static void convert( ImageSInt32 from, ImageFloat64 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( double )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( double )( from.data[i] );
			}
		}
	}

	public static void convert( ImageFloat32 from, ImageInt8 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( byte )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( byte )( from.data[i] );
			}
		}
	}

	public static void convert( ImageFloat32 from, ImageInt16 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( short )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( short )( from.data[i] );
			}
		}
	}

	public static void convert( ImageFloat32 from, ImageSInt32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( int )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( int )( from.data[i] );
			}
		}
	}

	public static void convert( ImageFloat32 from, ImageFloat64 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( double )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( double )( from.data[i] );
			}
		}
	}

	public static void convert( ImageFloat64 from, ImageInt8 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( byte )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( byte )( from.data[i] );
			}
		}
	}

	public static void convert( ImageFloat64 from, ImageInt16 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( short )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( short )( from.data[i] );
			}
		}
	}

	public static void convert( ImageFloat64 from, ImageSInt32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( int )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( int )( from.data[i] );
			}
		}
	}

	public static void convert( ImageFloat64 from, ImageFloat32 to ) {

		InputSanityCheck.checkSameShape(from, to);

		if (from.isSubimage() || to.isSubimage()) {

			for (int y = 0; y < from.height; y++) {
				int indexFrom = from.getIndex(0, y);
				int indexTo = to.getIndex(0, y);

				for (int x = 0; x < from.width; x++) {
					to.data[indexTo++] = ( float )( from.data[indexFrom++] );
				}
			}

		} else {
			final int N = from.width * from.height;

			for (int i = 0; i < N; i++) {
				to.data[i] = ( float )( from.data[i] );
			}
		}
	}


}
