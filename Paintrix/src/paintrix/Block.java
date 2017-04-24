
//***************************************************************
//Block class
//Purpose: Generate random blocks, assign block an id
//Called by: ControlPannel.java
//Calls:none
//***************************************************************

package paintrix;

public class Block {
	final int windowLength = 300;
	final int windoWidth = 600;
	final int arrayLength = 15;
	final int arrayWidth = 30;
	//int[][] controlPannel = new int[arrayLength][arrayWidth];

	private int position() {
		return 0;
	}

	public Block() {

	}

	public int blockGenerator(int[][] Array) {
		int arrayMid = arrayLength / 2;
		double rand = Math.random();
		rand = 7 * rand;
		if (rand > 0 && rand <= 1) {
			// block
			// **
			// **
			Array[2][arrayMid] = 9;
			Array[2][arrayMid + 1] = 1;
			Array[1][arrayMid] = 1;
			Array[1][arrayMid + 1] = 1;
			return 10;

		} else if (rand > 1 && rand <= 2) {
			// L
			// *
			// ***
			Array[2][arrayMid] = 9;
			Array[2][arrayMid + 1] = 1;
			Array[2][arrayMid + 2] = 1;
			Array[1][arrayMid] = 1;
			return 11;
			
		} else if (rand > 2 && rand <= 3) {
			// L
			//   *
			// ***
			Array[2][arrayMid] = 9;
			Array[2][arrayMid - 1] = 1;
			Array[2][arrayMid - 2] = 1;
			Array[1][arrayMid] = 1;
			return 12;
			
		} else if (rand > 3 && rand <= 4) {
			// s
			//  **
			// **
			Array[2][arrayMid] = 9;
			Array[1][arrayMid] = 1;
			Array[2][arrayMid - 1] = 1;
			Array[1][arrayMid + 1] = 1;
			return 13;
			
		} else if (rand > 4 && rand <= 5) {
			// s
			// **
			//  **
			Array[2][arrayMid] = 9;
			Array[2][arrayMid + 1] = 1;
			Array[1][arrayMid] = 1;
			Array[1][arrayMid - 1] = 1;
			return 14;
			
		} else if (rand > 5 && rand <= 6) {
			// T
			//  *
			// ***
			Array[2][arrayMid] = 9;
			Array[2][arrayMid + 1] = 1;
			Array[2][arrayMid - 1] = 1;
			Array[1][arrayMid] = 1;
			return 15;
			
		} else {
			// ---
			// ****
			Array[2][arrayMid] = 9;
			Array[2][arrayMid - 1] = 1;
			Array[2][arrayMid + 1] = 1;
			Array[2][arrayMid + 2] = 1;
			return 16;
			
		}
//		for (int i = 0; i < arrayWidth; i++) {
//			for (int j = 0; j < arrayLength; j++) {
//				System.out.println(Array[i][j] + " ");
//			}
//			System.out.println("\n");
//		}
	}
}