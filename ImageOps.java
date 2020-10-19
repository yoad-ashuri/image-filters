/*
Assignment number: 5.1
File name: ImageOps.java
Name: Yoad Ashuri
Student ID: 311162606
Email: Yoad.Ashuri@post.idc.ac.il
 */
/**
 * A library of image editing functions.
 */
public class ImageOps {
	// Use these constants, as needed.
	static final int NUM_OF_COLORS = 3;
	static final int R = 0;
	static final int G = 1;
	static final int B = 2;
	static final int MAX_COLOR_VALUE = 255;
	
	public static void main (String [] args) {


	}

	/**
	 * Reads an image in PPM format from the given filename.
	 *
	 /* @param fileName - name of the given PPM file
	 * @return - the image, as a 3-dimensional array
	 */
	public static int[][][] read(String filename) {

		StdIn.setInput(filename);
		StdIn.readLine();               // skip p3
		int imageW = StdIn.readInt();   // get the image wight
		int imageH = StdIn.readInt();   // get the image height
		int [][][] imageData = new int[imageH][imageW][NUM_OF_COLORS];  // create an array in the right size
		StdIn.readLine();
		StdIn.readLine();
		for (int i=0; i<imageH; i++) {
			for (int j=0; j<imageW; j++) {
				for (int k=0; k<NUM_OF_COLORS; k++) {
					imageData [i][j][k] = StdIn.readInt();     // fill the array
				}
			}
		}


		return imageData;
	}
	
	/**
	 * Prints the array values, nicely formatted. 
	 * 
	 * @param pic - the image to display.
	 */
	public static void showData (int[][][] pic) {

		for (int i=0; i<pic.length; i++) {
			for (int j = 0; j < pic[0].length; j++) {
				for (int k = 0; k < pic[0][0].length; k++) {
					System.out.printf("%3d", pic[i][j][k]);   // print the array boxes
					System.out.print("  ");                   // make spaces between the numbers

				}
				System.out.print("   ");                      // make spaces between the columns
			}
			System.out.println();                             // next line
		}
	}
	
	/**
	 * Renders an image, using StdDraw. 
	 * 
	 * @param pic - the image to render.
	 */
	public static void show(int[][][] pic) {
		StdDraw.setCanvasSize(pic[0].length, pic.length);
		int height = pic.length;
		int width = pic[0].length;
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		StdDraw.show(30);
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				StdDraw.setPenColor(pic[i][j][R], pic[i][j][G], pic[i][j][B] );
				StdDraw.filledRectangle(j + 0.5, height - i - 0.5, 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
	
	/**
	 * Flips an image horizontally.
	 * SIDE EFFECT: Changes the given image.
	 * 
	 * @param pic - the image to flip
	 */
	public static void flipHorizontally(int[][][] pic) {

		int x = pic[0].length - 1;

		for (int i = 0; i < pic.length; i++) {
			for (int j = 0; j < (pic[0].length) / 2; j++) {
				swap(pic,i,j,i,x);
				x--;
			}
			x = pic[0].length - 1;
		}

	}
	/**
	 * Flips an image vertically
	 * * SIDE EFFECT: Changes the given image.
	 * 
	 * @param pic - the image to flip
	 */
	public static void flipVertically(int[][][] pic) {

		int x = pic.length - 1;
		for (int i = 0; i < (pic.length) / 2; i++) {
			for (int j = 0; j < pic[0].length; j++) {
				swap(pic,i,j,x,j);
			}
			x--;
		}



	}
	
	// Swaps the two given pixels in the given image.
	// SIDE EFFECT: Changes the pixles in the given image.
	// i1,j1 - coordinates of the first pixel
	// i2,j2 - coordinates of the second pixel
	private static void swap(int[][][] pic, int i1, int j1, int i2, int j2) {

		int x;

		for (int i=0; i<3; i++) {
			x = pic[i1][j1][i];
			pic[i1][j1][i] = pic[i2][j2][i];
			pic[i2][j2][i] = x;
		}


	}
	
	/**
	 * Turns an RGB color into a greycale value, using a luminance formula.
	 * The luminance is a weighted mean of the color's value, and is given by:
	 * 0.299 * r + 0.587 * b + 0.114 * b.
	 * 
	 * @param color - the color to be greyScaled.
	 * @return the greyscale value, as an array {greyscale, greyscale, greyscale}
	 */
	public static int[] luminance(int[] color) {

		int [] ans = new int [3];
		int lumV = (int)(0.299 * color[0] + 0.587 * color[1] + 0.114 * color[2]);   //calculate the luminance value

		for (int i=0; i<3; i++) {
			ans[i] = lumV;          // enter the luminance value to the new array
		}

		return ans;
	}
	
	/**
	 * Creates a greyscaled version of an image.
	 * 
	 * @param pic - the given image
	 * @return - the greyscaled version of the image
	 */
	public static int[][][] greyScaled (int[][][] pic) {

		for (int i=0; i<pic.length; i++) {
			for (int j=0; j<pic[0].length; j++) {
				pic[i][j] = luminance(pic[i][j]);
			}
		}
		return pic;
	}	
	
	/**
	 * Creates a blurred version of an image.
	 * Uses a block blur algorithm.
	 * 
	 * @param pic - the given image
	 * @return - the blurred version of the image
	 */
	public static int[][][] blurred (int[][][] pic) {
		// Replace the following statement with your code

		int[][][] temp = new int[pic.length + 2][pic[0].length + 2][3];      // make enlarge matrix.
		for (int i = 0; i < pic.length; i++) {
			for (int j = 0; j < pic[0].length; j++) {
				for (int k = 0; k < 3; k++) {
					temp[i + 1][j + 1][k] = pic[i][j][k];                     // fill the matrix.
				}
			}
		}
		for (int i = 1; i <= pic.length; i++) {
			for (int j = 1; j <= pic[0].length; j++) {
				for (int k = 0; k < 3; k++) {
					blurColor(temp, pic, i, j, k);                           // blur the pixel.
				}
			}
		}
		return  pic;
	}

	// Blurs a given color of a given pixel in a given image.
	// Stores the result in a blurred version of the given image, without effecting the given image.
	// Uses a block blur algorithm.
	// pic - the given image
	// blurredPic - the blurred version of the given image
    // row - the row of the pixel
	// col - the column of the pixel
	// color - the color to blur: 0-red, 1-green, 2-blue
	private static void blurColor(int[][][] pic, int[][][] blurredPic, int row, int col, int color) {
		//take in account that the matrix had padding of zeroes.

		boolean rowEdg = false;
		boolean colEdg = false;
		int count = 0;
		int sum = 0;

		if ((row == 1) || (row == pic.length - 2)) {
			rowEdg = true;
		}
		if ((col == 1) || (col == pic[0].length - 2)) {
			colEdg = true;
		}
		if (rowEdg && colEdg) {
		count = 4;
		}else {
			if (!(rowEdg) && !(colEdg)) {
				count = 9;
			}
			else {
				count = 6;
			}
		}
		for (int i=row-1; i<=row+1; i++) {
			for (int j=col-1; j<=col+1;j++) {
				sum += getColorIntensity(pic,i,j,color);
			}
		}
		blurredPic[row-1][col-1][color] = sum / count;
		return;
	}
	
	// Returns the color intensity of a pixel, or -1 if the coordinates of the pixel are illegal.
	// pic - the given source image
	// row - the given row of the pixel
	// col - the given column of the pixel
	// color - the given color: 0-red, 1-green, 2-blue
	private static int getColorIntensity(int[][][] pic, int row, int col, int color) {

		if ((row >= pic.length) || (row < 0) || (col >= pic[0].length) || (col < 0)) { // Check if the coordinates are illegal.
			return -1;
		}
		if ((color < 0) || (color > 3)) {                                              // Check if the color is illegal.
			return -1;
		}
		return pic[row][col][color];
	}
	
	/**
	 * Calculates the horizontal gradient of the greyscaled version of an image
	 * 
	 * @param pic - the given image
	 * @return - the gradient of the greyscaled version of the given image.
	 */
	public static int[][] horizontalGradient(int[][][] pic) {

		int [][] gradientMatrix = new int[pic.length][pic[0].length];
		for (int i=0; i<pic.length; i++) {
			for (int j=1; j<pic[0].length - 1; j++) {
				gradientMatrix[i][j] = pic[i][j+1][0] - pic[i][j-1][0];
			}
		}
		return gradientMatrix;
	}
	
	/**
	 * Normalizes a 2D array so that all the values are between 0 to 255
	 * SIDE EFFECT: Changes the given array
	 * 
	 * @param arr - the given array
	 */
	public static void normalize(int[][] arr) {

		int maxInArray = arr[0][0];
		int minInArray = arr[0][0];

		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr[0].length; j++) {
				if(arr[i][j] > maxInArray) {
					maxInArray = arr[i][j];
				}
				if (arr[i][j] < minInArray) {
					minInArray = arr[i][j];
				}
			}
		}
		if ((maxInArray - minInArray) == 0) {          // avoid dividing by zero.
			return;
		}
		for (int i=0; i<arr.length; i++) {
			for (int j=1; j<arr[0].length - 1; j++) {
				arr[i][j] = ((arr[i][j] - minInArray) * maxInArray) / (maxInArray - minInArray) ; // Calculate the normalize value
			}
		}
	}
	
	/**
	 * Creates a greyscaled image showing the horizontal edges of a given image.
	 * Uses gradient edge detection.
	 * 
	 * @param pic - the given image
	 * @return - a greyscaled image showing the horizontal edges of the given image
	 */
	public static int[][][] edges(int[][][] pic) {

		pic = greyScaled(pic);
		int [][] horGradPic = horizontalGradient(pic);
		normalize(horGradPic);

		for (int i=0; i<pic.length; i++) {
			for (int j=0; j<pic[0].length; j++) {
				pic[i][j][0] = horGradPic[i][j];
				pic[i][j][1] = horGradPic[i][j];
				pic[i][j][2] = horGradPic[i][j];
			}
		}


		return pic;
	}
}
