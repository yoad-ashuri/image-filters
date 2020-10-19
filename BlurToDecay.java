/*
Assignment number: 5.2.1
File name: BlurToDecay.java
Name: Yoad Ashuri
Student ID: 311162606
Email: Yoad.Ashuri@post.idc.ac.il
 */
public class BlurToDecay {
    public static void main (String [] args) {

        if (args.length == 0) {                              // Check for empty input.
            System.out.println("No input given." + "\n" + "Try again and insert input.");
            return;
        }
        int [][][] pic = ImageOps.read(args[0]);            // Gets the user photo.
        ImageOps.show(pic);
        int n =Integer.parseInt(args[1]);
        for (int i=0; i<n; i++) {
            pic = ImageOps.edges(pic);
            ImageOps.show(pic);
            ImageOps.blurred(pic);
            ImageOps.show(pic);
        }
    }
}
