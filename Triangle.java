/** Authorship Statement
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Jarod Tang, ID = 1175923, email = jarodt@student.unimelb.edu.au
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Triangle {
  private int sideLen, maxSideLen, currPos;
  private String printingChar;
  private ShapeManipulation editTrig = new ShapeManipulation();
  private Scanner keyboardInput = ScannerSingleton.getScanner();

  public ArrayList trigOptions(String[] settings, ArrayList trigSettings, Triangle playTrig, DrawingCanvas currCanv) {
    
    while (true) {
      currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
      playTrig.printTrigOps();
      String choice; // resets variable options after each iteration
      choice = keyboardInput.next();

      if (choice.equals("1")) {
        // Adding Triangle
        sideLen = getSideLen(settings);
        printingChar = getPrintChar();
        trigSettings = addNewTrig(sideLen, printingChar, trigSettings);
        currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
        // currIndex by default should be the last element (aka most recent element added)
        trigSettings = editTrig.chooseMode(settings, trigSettings, playTrig, currCanv, editTrig, currPos = ((ArrayList<Integer>) trigSettings.get(0)).size()-1);
      }

      else if (choice.equals("2")) {
        trigSettings = playTrig.changeTrig(trigSettings, settings, currCanv, editTrig, playTrig);
      }
      else if (choice.equals("3")) {
        trigSettings = playTrig.removeTrig(trigSettings, settings, currCanv);
      }
      else if (choice.equals("4")) {
        break; // Exit
      }
      else {
        System.out.println("Unsupported option. Please try again!"); 
      }
    }
    return trigSettings;
  }

  public void printTrigOps() {
    System.out.println("Please select an option. Type 4 to go back to the previous menu.");
    System.out.println("1. Add a new Triangle");
    System.out.println("2. Edit a triangle");
    System.out.println("3. Remove a triangle");
    System.out.println("4. Go back");
  }
  

  // Methods called to manipulate triangle settings

  private ArrayList addNewTrig(int sideLen, String printingChar, ArrayList trigSettings) {
    
    // append sidelength of current triangle
    ((ArrayList<Integer>) trigSettings.get(0)).add(sideLen);
    // initial position will be (0, 0), so xpos and ypos set to (0, 0) by default
    ((ArrayList<Integer>) trigSettings.get(1)).add(0);
    ((ArrayList<Integer>) trigSettings.get(2)).add(0);
    // append printing character of current triangle
    ((ArrayList<String>) trigSettings.get(3)).add(printingChar);
    // by default, orientation should be set to 0 (no rotation has yet to be performed)
    ((ArrayList<Integer>) trigSettings.get(4)).add(0);

    return trigSettings;
  }

  // Functions used to create triangle
  private int getSideLen(String[] settings) {
    // must initialise sideLen = 0 otherwise code won't run        
    sideLen = 0; 
    int canvHeight = Integer.parseInt(settings[0]);
    int canvWidth = Integer.parseInt(settings[1]);

    while (true) {
      Scanner getSideLen = ScannerSingleton.getScanner();
      System.out.println("Side length:");
      sideLen = getSideLen.nextInt(); 

      // check to ensure side length is shorter than width and height of canvas
      if ((sideLen > canvWidth) || (sideLen > canvHeight)) {
        System.out.println("Error! The side length is too long (Current canvas size is " + canvWidth + "x" + canvHeight + "). Please try again.");
      }
      else {
        break; // side length is valid
      }
    }
    return sideLen;
  }

  private String getPrintChar() {
    System.out.println("Printing character:");
    Scanner getChar = ScannerSingleton.getScanner();
    printingChar = getChar.next();
    return printingChar;
  }

  private ArrayList removeTrig(ArrayList trigSettings, String[] settings, DrawingCanvas currCanv) {

    //unpacking
    int trigAmount = ((ArrayList<Integer>) trigSettings.get(0)).size();
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

    // Initialising other variables
    int index, pos;
    
    if (trigAmount == 0) {
      System.out.println("The current canvas is clean; there are no shapes to remove!");
      return trigSettings;
    }

    for (int i = 0; i < trigAmount; i++) {
      index = i + 1;
      System.out.println("Shape #" + index + " - Triangle: xPos = " + xpos.get(i) + ", yPos = " + ypos.get(i) + ", tChar = " + printingChar.get(i));
    }

    System.out.println("Index of the shape to remove:");
    pos = keyboardInput.nextInt();
    if (pos > trigAmount) {
      System.out.println("The shape index cannot be larger than the number of shapes!");
    }
    else {
      pos = pos - 1;
      sideLen.remove(pos);
      xpos.remove(pos);
      ypos.remove(pos);
      printingChar.remove(pos);
      orientation.remove(pos);
    }
    return trigSettings;
  }

  private ArrayList changeTrig(ArrayList trigSettings, String[] settings, DrawingCanvas currCanv, ShapeManipulation editTrig, Triangle playTrig) {
    
    //unpacking
    int trigAmount = ((ArrayList<Integer>) trigSettings.get(0)).size();
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

    // Initialising other variables
    int index, pos;
    
    if (trigAmount == 0) {
      System.out.println("The current canvas is clean; there are no shapes to edit!");
      return trigSettings;
    }

    for (int i = 0; i < trigAmount; i++) {
      index = i + 1;
      System.out.println("Shape #" + index + " - Triangle: xPos = " + xpos.get(i) + ", yPos = " + ypos.get(i) + ", tChar = " + printingChar.get(i));
    }

    System.out.println("Index of the shape:");
    // give "pos" to ShapeManipulation
    pos = keyboardInput.nextInt();
    if (pos > trigAmount) {
      System.out.println("The shape index cannot be larger than the number of shapes!");
    }
    else {
      pos = pos - 1;
      currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
      // parse "pos" into chooseMode
      trigSettings = editTrig.chooseMode(settings, trigSettings, playTrig, currCanv, editTrig, pos);
    }
    return trigSettings;
  }
}


  
    

