// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] imageOut;

		// Tests the horizontal flipping of an image:
		 // imageOut = flippedHorizontally(tinypic);
		//  System.out.println();
		// print(imageOut);  

		// Tests the vertical flipping of an image:
		 imageOut = flippedVertically(tinypic);
		 System.out.println();
		 print(imageOut);
		 
		
		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];

		for (int i =0; i < numRows; i++ ){
			for (int j=0; j<numCols; j++){
				int red = in.readInt();
				int green = in.readInt();
				int blue = in.readInt();
				Color c = new Color(red, green, blue);
				image[i][j] = c; 
			}
		}
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		for (int i = 0; i <image.length; i++){
			for (int j=0; j<image[i].length; j++ ){
				print(image[i][j]);
				if (j==image[i].length-1){
					System.out.println("");
				}
			}
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		int rows = image.length;
		int colms = image[1].length; 

		Color[][] newImage = new Color[rows][colms]; 
		if (colms % 2 == 0 ) {
			for (int i =0; i<rows; i++){
				for (int j= 0; j < colms/2; j++){
					int k = image[1].length-j-1; // the horizontally flipped place. 
					newImage [i][j] = image [i][k]; 
					newImage [i][k] = image [i][j];
	
				}
			}
		}
		else {
			for (int i =0; i<rows; i++){
				for (int j= 0; j < (colms/2)+1; j++){
					int k = image[1].length-j-1; // the horizontally flipped place. 
					newImage [i][j] = image [i][k]; 
					newImage [i][k] = image [i][j];
	
				}
			}
		}
		return newImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		int rows = image.length;
		int colms = image[1].length; 

		Color[][] newImage = new Color[rows][colms]; 
		if (rows % 2 == 0 ) {
			for (int i =0; i<rows/2; i++){
				for (int j= 0; j < colms; j++){
					int k = image.length-i-1; // the horizontally flipped place. 
					newImage [i][j] = image [k][j]; 
					newImage [k][j] = image [i][j];
				}
			}
		}
		else {
			for (int i =0; i<(rows/2)+1; i++){
				for (int j= 0; j < colms; j++){
					int k = image.length-i-1; // the horizontally flipped place. 
					newImage [i][j] = image [k][j]; 
					newImage [k][j] = image [i][j];
				}
			}
		}
		return newImage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		int r = pixel.getRed();
		int g = pixel.getRed();
		int b = pixel.getRed();
		int lum = (int)(0.299 * r + 0.587 * g + 0.114 * b); 
		Color newPixle = new Color(lum, lum, lum); 
		return newPixle;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		int rows = image.length;
		int colms = image[1].length; 
		Color[][] newImage = new Color[rows][colms]; 
		for (int i=0; i<rows; i++){
			for (int j=0; j<colms; j++){
				Color greyPix = image[i][j]; 
				newImage[i][j] = luminance(greyPix);
			}
		}
		return newImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		int rows = image.length;
		int colms = image[1].length; 
		Color[][] newImage = new Color[height][width]; 
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){ 
				int newRows = (rows*i)/height; 
				int newColm = (colms*j)/width; 

				newImage[i][j] = image[newRows][newColm];
			}
		}
		return newImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		
		int r1 = c1.getRed();
		int g1 = c1.getGreen();
		int b1 = c1.getBlue();

		int r2 = c2.getRed();
		int g2 = c2.getGreen();
		int b2 = c2.getBlue();

		int r3 = (int)((r1*alpha) + ((1-alpha)*r2)); 
		int g3 = (int)((g1*alpha) + ((1-alpha)*g2));
		int b3 = (int)((b1*alpha) + ((1-alpha)*b2));
		
		Color v = new Color(r3,g3,b3); 

		return v;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		int rows = image1.length;
		int colms = image1[1].length; 
		Color[][] newImage = new Color[rows][colms];

		for (int i=0; i<rows; i++){
			for (int j=0; j<colms; j++){ 
				Color v = blend(image1[i][j], image2[i][j], alpha);
				newImage[i][j] = v; 
			}
		}
		return newImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		int rows = source.length;
		int colms = source[0].length;

		if ((rows != target.length) || (colms != target[0].length)){
			target = scaled(target, colms, rows); 
		}
		for (int i=0; i<n; i++){
			int alpha = (n-i)/n; 
			target = blend(source, target, alpha); 
		}
		display(target);
	}
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}

