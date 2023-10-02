/** Authorship Statement
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Jarod Tang, ID = 1175923, email = jarodt@student.unimelb.edu.au
 */

import java.util.Scanner;
import java.util.ArrayList;

public class DrawingCanvas {

  // All the methods here were kept as public methods since they are to be used by other classes within the codebase

  // initialise/update canvas settings
  public String[] updateCanvas() {
    Scanner update = ScannerSingleton.getScanner();
    String columns, rows, background;
  
    System.out.print("Canvas width: ");
    columns = String.valueOf(update.nextInt());
    System.out.print("Canvas height: ");
    rows = String.valueOf(update.nextInt());
    System.out.print("Background character: ");
    background = update.next();
    String[] updatedSettings = {rows, columns, background};
    return updatedSettings;
  }
  
  // Make a 2D array
  public String[][] make2D(String[] settings, ArrayList trigSettings) {
    
    int row = Integer.parseInt(settings[0]);
    int column = Integer.parseInt(settings[1]);
    String[][] canvas = new String[row][column];

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        canvas[i][j] = settings[2]; 
      }
    }

    int currSize = ((ArrayList<Integer>) trigSettings.get(1)).size();
    // unpacking
    // index 0 = side length of each triangle
    // index 1 = xpos of each triangle
    // index 2 = ypos of each triangle
    // index 3 = printing character of each triangle
    // index 4 = orientation of each triangle
    ArrayList<Integer> sideLen = ((ArrayList<Integer>) trigSettings.get(0));
    ArrayList<Integer> xpos = ((ArrayList<Integer>) trigSettings.get(1));
    ArrayList<Integer> ypos = ((ArrayList<Integer>) trigSettings.get(2));
    ArrayList<String> printingChar = ((ArrayList<String>) trigSettings.get(3));
    ArrayList<Integer> orientation = ((ArrayList<Integer>) trigSettings.get(4));
    
    for (int k = 0; k<currSize; k++) {
      int maxSideLen = sideLen.get(k);
      int currSideLen = sideLen.get(k);
      int xStartingPos, yStartingPos;
      boolean printed = false;
      boolean firstPrint = true;
      for (int i = 0; i < row; i++) {
        firstPrint = true;
        int n = 0;
        for (int j = 0; j < column; j++) {

          // save triangle according to orientation
          if ( ((xpos.get(k)<=j) && (j<(currSideLen+xpos.get(k))))  &&  ((ypos.get(k)<=i) && (i<(maxSideLen+ypos.get(k)))) ) {
            if (orientation.get(k) == 0) { // k = 0 is 0 degrees
              canvas[i][j] = printingChar.get(k); 
            }
            else if (orientation.get(k) == 1) { // k = 1 is 90 degrees

              if (firstPrint) { 
                firstPrint = false;
              }
              xStartingPos = (xpos.get(k)+maxSideLen-1)-n;
              canvas[i][xStartingPos] = printingChar.get(k); 
            }

            else if (orientation.get(k) == 2) { // k = 2 is 180 degrees

              if (firstPrint) {
                firstPrint = false;
              }
              xStartingPos = (xpos.get(k)+maxSideLen-1)-n;
              yStartingPos = (ypos.get(k)+currSideLen-1);
              canvas[yStartingPos][xStartingPos] = printingChar.get(k);   

            }
            else if (orientation.get(k) == 3) { // k = 3 is 270 degrees

              if (firstPrint) {
                firstPrint = false;
              }
              yStartingPos = (ypos.get(k)+currSideLen-1);

              canvas[yStartingPos][j] = printingChar.get(k);   
            }

            printed = true;
            n = n + 1;
          }
        }

        if (printed) {
          currSideLen = currSideLen - 1;
        }
        printed = false;
      }
    }

    return canvas;
  }


  // Prints 2d array
  public void printCanvas(String[][] canvas) {
    
    int row = canvas.length;
    int column = canvas[0].length;
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < column; j++) {
            System.out.print(canvas[i][j]);
        }
        System.out.print("\n");
    } 
  }

  // If user wants to share their drawing

  public void shareFormat(String[][] canvas) {
    int row = canvas.length;
    int column = canvas[0].length;
    String[][] sharedCanv = new String[row][column+(column-1)];
    for (int i = 0; i < row; i++) {
        for (int j = 0; j < column; j++) {
            System.out.print(canvas[i][j]);
            if (j < column-1) {
            System.out.print(",");
            }
        }
        System.out.print("\n");
    } 
  }

  // Clear Canvas of triangles
  public ArrayList clearCanv(ArrayList trigSettings) {

    int currLen = trigSettings.size();
    for (int k = 0; k<currLen; k++) {
      if (k != 3) {
        ((ArrayList<Integer>) trigSettings.get(k)).clear();
      }
      else {
        ((ArrayList<String>) trigSettings.get(k)).clear();
      }
    }
    return trigSettings;
  }

}





