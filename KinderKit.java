/** Authorship Statement
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Jarod Tang, ID = 1175923, email = jarodt@student.unimelb.edu.au
 */

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class KinderKit {

  public static void main(String[] args) {
    //DON'T TOUCH LINES 8 to 59
    //Check program arguments
    if (args.length != 1) {
      System.out.println("This program takes exactly one argument!");
      return;
    }

    //Read sample drawing from file
    Scanner inputStream = null;
    char[][] bitmap = null;
    int rows = 0, cols = 0;
    char bgChar;

    try {
      inputStream = new Scanner(new FileInputStream(args[0]));
      String line;
 
      //Read the first line
      if (inputStream.hasNextLine()) {
        line = inputStream.nextLine();
        String[] tmps = line.split(",");
        if (tmps.length != 3) {
          System.out.println("The given file is in wrong format!");
          return;
        } else {
          rows = Integer.parseInt(tmps[0]);
          cols = Integer.parseInt(tmps[1]);
          bgChar = tmps[2].charAt(0);
          bitmap = new char[rows][cols];
        }
      } else {
        System.out.println("The given file seems empty!");
        return;
      }

      //Read the remaining lines
      int rowIndex = 0;
      while (inputStream.hasNextLine()) {
        line = inputStream.nextLine();
        String[] tmps = line.split(",");
        for(int i = 0; i < tmps.length; i++) {
          bitmap[rowIndex][i] = tmps[i].charAt(0);
        }
        rowIndex++;
      }
      //Close the file input stream
      inputStream.close();
    } catch (FileNotFoundException e) {
      System.out.println("The given file is not found!");
      return;
    }

    // Start of code
    System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----");
    String height  = "0", width = "0", background = "0"; // must initialise with initial settings otherwise code won't run
    String[] settings = {height, width, background};
    KinderKit Options = new KinderKit(); // make instance of class to call printing function later
    Triangle playTrig = new Triangle();
    String choice;
    DrawingCanvas currCanv = new DrawingCanvas();

    // Triangle settings initialisation
    ArrayList<ArrayList<?> > trigSettings = new ArrayList<ArrayList<?> >();
    ArrayList<Integer> sideLen = new ArrayList<Integer>();
    trigSettings.add(sideLen);
    ArrayList<Integer> xpos = new ArrayList<Integer>();
    trigSettings.add(xpos);
    ArrayList<Integer> ypos = new ArrayList<Integer>();
    trigSettings.add(ypos);
    ArrayList<String> trigChar = new ArrayList<String>();
    trigSettings.add(trigChar);
    ArrayList<Integer> orientation = new ArrayList<Integer>();
    trigSettings.add(orientation);

    while (true) {
      Options.printInitialOptions(); // prints options
      choice = Options.getChoice();
      
      if (choice.equals("1")) { // option 1
        
        // clear trigSettings elements
        currCanv.clearCanv(trigSettings);
        Options.optionOne(bitmap, settings, trigSettings, playTrig, currCanv);
      }
      else if (choice.equals("2")) { // option 2

        // clear trigSettings elements
        currCanv.clearCanv(trigSettings);
        Options.optionTwo(settings, trigSettings, playTrig, currCanv);

      }
      else if (choice.equals("3")) { // option 3
        System.out.println("Goodbye! We hope you had fun :)");
        break; // Exit while loop
      }
      else { // non-registered option
        System.out.println("Unsupported option. Please try again!");
      }
    }
  }

  public void printInitialOptions() {
    System.out.println("Please select an option. Type 3 to exit.");
    System.out.println("1. Draw a predefined object");
    System.out.println("2. Freestyle Drawing");
    System.out.println("3. Exit");
  }

  public void optionOne(char[][] bitmap, String[] settings, ArrayList trigSettings, Triangle playTrig, DrawingCanvas currCanv) {

    KinderKit op1 = new KinderKit();
    DrawingTask preview = new DrawingTask();
    // get settings from the parsed in bitmap
    settings = preview.getSettings(bitmap);
    while (true) {
      op1.printOptionOne();
      String choice; // resets variable options after each iteration
      choice = op1.getChoice();

      // All cases
      if (choice.equals("1")) {
        System.out.println("This is your task. Just try to draw the same object. Enjoy!");
        preview.previewDrawing(bitmap);
      }
      else if (choice.equals("2")) {
        trigSettings = playTrig.trigOptions(settings, trigSettings, playTrig, currCanv);
        
      }
      else if (choice.equals("3")) {
        preview.checkResults(bitmap, trigSettings, settings, currCanv, preview);
      }
      else if (choice.equals("4")) { // option 3
        break; // Exit while loop
      }
      else { // non-registered option
        System.out.println("Unsupported option. Please try again.");
      }
    }
    
  }

  public void printOptionOne() {
    System.out.println("Please select an option. Type 4 to go back to the main menu.");
    System.out.println("1. Preview the sample drawing");
    System.out.println("2. Start/edit the current canvas");
    System.out.println("3. Check result");
    System.out.println("4. Go back to the main menu");
  }
  

  public void optionTwo(String[] settings, ArrayList trigSettings, Triangle playTrig, DrawingCanvas currCanv) {
    
    KinderKit op2 = new KinderKit();
    settings = currCanv.updateCanvas();

    while (true) {
      op2.printOptionTwo();
      String choice; // resets variable options after each iteration
      choice = op2.getChoice();

      // All cases
      if (choice.equals("1")) {
        trigSettings = playTrig.trigOptions(settings, trigSettings, playTrig, currCanv);
      }
      else if (choice.equals("2")) {
        System.out.println("This is the detail of your current drawing");
        op2.shareMode(settings, trigSettings, currCanv);
      }
      else if (choice.equals("3")) {
        break; // Exit while loop
      }
      else {
        System.out.println("Unsupported option. Please try again.");
      }
    }
  }

  public void printOptionTwo() {
    System.out.println("Please select an option. Type 3 to go back to the main menu.");
    System.out.println("1. Start/edit your current canvas");
    System.out.println("2. Share your current drawing");
    System.out.println("3. Go back to the main menu");
  }

  public String getChoice() {
    String choice;
    Scanner keyboardInput = ScannerSingleton.getScanner();
    choice = keyboardInput.next();
    return choice;
  }

  public void shareMode(String[] settings, ArrayList trigSettings, DrawingCanvas currCanv) {
    
    for (int k = 0; k<settings.length; k++) {
      System.out.print(settings[k]);
      if (k<settings.length-1) {
        System.out.print(",");
      }
    }
    System.out.print("\n");
    currCanv.shareFormat(currCanv.make2D(settings, trigSettings));
  }


}




