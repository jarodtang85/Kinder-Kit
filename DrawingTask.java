/** Authorship Statement
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Jarod Tang, ID = 1175923, email = jarodt@student.unimelb.edu.au
 */

import java.util.Scanner;
import java.util.ArrayList;

public class DrawingTask {

    private DrawingCanvas viewDrawing;
    private DrawingCanvas currCanv;

    // Methods previewDrawing, getSettings and checkResults kept as public methods since they are to be used
    // by other classes within the codebase

    public void previewDrawing(char[][] bitmap) {
        
        String[][] canvas = new String[bitmap.length][bitmap[0].length];
        // [Row][Column]

        // Converts char array to string array
        for (int i = 0; i < bitmap.length; i++) {
            for (int j = 0; j < bitmap[0].length; j++) {
                canvas[i][j] = Character.toString(bitmap[i][j]);
            }
        }
        
        viewDrawing = new DrawingCanvas();
        viewDrawing.printCanvas(canvas);
    }

    public String[] getSettings(char[][] bitmap) {
        String rows = Integer.toString(bitmap.length);
        String columns = Integer.toString(bitmap[0].length);
        String background = Character.toString(bitmap[0][0]);
        String[] settings = {rows, columns, background};
        return settings;
    }

    public void checkResults(char[][] bitmap, ArrayList trigSettings, String[] settings, DrawingCanvas currCanv, DrawingTask preview) {
        boolean match = true;
        int rows = bitmap.length;
        int columns = bitmap[0].length;
        String[][] canvas = new String[rows][columns];
        canvas = currCanv.make2D(settings, trigSettings);
        // Check if the values in each cell of double array are the same
        
        outerloop:
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (Character.toString(bitmap[i][j]).equals(canvas[i][j])) {
                }
                else {
                    match = false;
                    break outerloop; // exit out of loop
                }
            }
        }

        if (match) {
            System.out.println("You successfully complete the drawing task. Congratulations!!");
        } 
        else {
            System.out.println("Not quite! Please edit your canvas and check the result again.");
        }
        System.out.println("This is the sample drawing:");
        preview.previewDrawing(bitmap);
        System.out.println("And this is your drawing:");
        currCanv.printCanvas(canvas);
    }
}

