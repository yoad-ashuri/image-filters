/*
Assignment number: 5.1.1
File name: BasicEditor.java
Name: Yoad Ashuri
Student ID: 311162606
Email: Yoad.Ashuri@post.idc.ac.il
 */

/**
 *  A program that take the user photo and make changes according to the user wish.
 *  If he just give the photo and no wish the it will show the photo as is.
 *  This program use ImageOps class methods.
 */
public class BasicEditor {
    public static void main (String [] args) {

        if (args.length == 0) {                              // Check for empty input.
            System.out.println("No input given." + "\n" + "Try again and insert input.");
            return;
        }
        String fileName = args[0];
        int [][][] pic = ImageOps.read(fileName);            // Gets the user photo.
        if (args.length == 2){                               // Check if he want to make changes.
            switch (args[1]) {                               // Check which change he want to make.

                case "fh":
                    ImageOps.flipHorizontally(pic);
                    break;
                case "fv":
                    ImageOps.flipVertically(pic);
                    break;
                case "gr":
                    pic = ImageOps.greyScaled(pic);
                    break;
                default:                                     // If he enter incorrect input.
                    System.out.println("Incorrect input.");
                    break;
            }
        }
        ImageOps.show(pic);                                  // Print the image.

    }
}
