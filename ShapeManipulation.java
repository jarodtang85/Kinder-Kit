/** Authorship Statement
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Jarod Tang, ID = 1175923, email = jarodt@student.unimelb.edu.au
 */

import java.util.Scanner;
import java.util.ArrayList;

public class ShapeManipulation {
  private Scanner getMode = ScannerSingleton.getScanner();
  private String mode, zoom, move, rotation;

  public ArrayList chooseMode(String[] settings, ArrayList trigSettings, Triangle playTrig, DrawingCanvas currCanv, ShapeManipulation editTrig, int currPos) {
    while (true) {
      System.out.println("Type Z/M/R for zooming/moving/rotating. Use other keys to quit the Zooming/Moving/Rotating mode.");
      mode = getMode.next().toUpperCase();
      if (mode.equals("Z")) {
        // Call ZoomMode
        trigSettings = zoomMode(settings, trigSettings, currCanv, currPos);
      }
      else if (mode.equals("M")) {
        // Call MoveMode
        trigSettings = moveMode(settings, trigSettings, currCanv, currPos);
      }

      else if (mode.equals("R")) {
        // Call rotateMode
        trigSettings = rotateMode(settings, trigSettings, currCanv, currPos);
      }
      
      else {

          break;
      }

    }
    return trigSettings;
  }

  // Manipulator Functions

  // Zooming Mode
  private ArrayList zoomMode(String[] settings, ArrayList trigSettings, DrawingCanvas currCanv, int currPos) {
    
    // Initialising
    currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
    int canvHeight = Integer.parseInt(settings[0]);
    int canvWidth = Integer.parseInt(settings[1]);

    //unpacking
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

    while (true) {
      System.out.println("Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving/Rotating menu.");
      zoom = getMode.next().toUpperCase();
      // Zooming in
      if (zoom.equals("I")) {
        if (( (xpos.get(currPos)+sideLen.get(currPos)) == canvWidth) || ( (ypos.get(currPos)+sideLen.get(currPos)) == canvHeight)) {
          System.out.println("This triangle reaches its limit. You cannot make it bigger!");
        }
        else {
          sideLen.set(currPos, sideLen.get(currPos) + 1); // Update position 
        }
      }

      // Zooming out
      else if (zoom.equals("O")) {
        if (sideLen.get(currPos) == 1) {
          System.out.println("This triangle reaches its limit. You cannot make it smaller!");
        }
        else {
          sideLen.set(currPos, sideLen.get(currPos) - 1); 
        }
      }
      else {
        currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
        break;
      }
      currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
    }
    return trigSettings;
  }

  private ArrayList moveMode(String[] settings, ArrayList trigSettings, DrawingCanvas currCanv, int currPos) {

    // Initialising
    currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
    int canvHeight = Integer.parseInt(settings[0]);
    int canvWidth = Integer.parseInt(settings[1]);
    //unpacking
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
    int movement;
    

    while (true) {
      System.out.println("Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving/Rotating menu.");
      move = getMode.next().toUpperCase();
      // left
      if (move.equals("A")) {
        movement = xpos.get(currPos) - 1;
        // check if corner element closest to border can move left
        if (movement >= 0) {
          xpos.set(currPos, movement); 
        }
        else {
          System.out.println("You cannot move this triangle outside of the drawing canvas!");
        } 
      }

      // right
      else if (move.equals("S")) {
        movement = xpos.get(currPos) + sideLen.get(currPos) + 1;
        // check if corner element closest to border can move right
        if (movement <= canvWidth) {
          xpos.set(currPos, xpos.get(currPos) + 1); 
        }
        else {
          System.out.println("You cannot move this triangle outside of the drawing canvas!");
        }
      }
      
      // up
      else if (move.equals("W")) {
        movement = ypos.get(currPos)-1;
        // check if corner element closest to border can move up
        if (movement >= 0) {
          ypos.set(currPos, movement); 
        }
        else {
          System.out.println("You cannot move this triangle outside of the drawing canvas!");
        } 
      }
      // down
      else if (move.equals("Z")) { 
        movement = ypos.get(currPos)+sideLen.get(currPos)+1;
        if (movement <= canvHeight) {
          ypos.set(currPos, ypos.get(currPos) + 1); 
        }
        else {
          System.out.println("You cannot move this triangle outside of the drawing canvas!");
        }
      }
      else {
        currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
        break;
      }
      currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
    }
    return trigSettings;
  }

  private ArrayList rotateMode(String[] settings, ArrayList trigSettings, DrawingCanvas currCanv, int currPos) {

    // Initialising
    int completeRotation = 4;
    currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
    int canvHeight = Integer.parseInt(settings[0]);
    int canvWidth = Integer.parseInt(settings[1]);
    
    //unpacking
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

    while (true) {
      System.out.println("Type R/L to rotate clockwise/anti-clockwise. Use other keys to go back to the Zooming/Moving/Rotating menu.");
      rotation = getMode.next().toUpperCase();

      // get modulus of 4 to when orientation is at original (pre-rotated) position (0)
      if (rotation.equals("R")) { // Clockwise Rotation
        orientation.set(currPos, (orientation.get(currPos)+1)%completeRotation); 
      
      }
      else if (rotation.equals("L")) { // Anticlockwise Rotation
        if (orientation.get(currPos) == 0) {
          orientation.set(currPos, 3); 
        }
        else {
          orientation.set(currPos, (orientation.get(currPos)-1)%completeRotation); 
        }
      }

      else {
        currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
        break;
      }
      currCanv.printCanvas(currCanv.make2D(settings, trigSettings));
    }
    return trigSettings;
    
  }




}
