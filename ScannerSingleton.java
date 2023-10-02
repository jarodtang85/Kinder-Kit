/** Authorship Statement
 * COMP90041, Sem1, 2023: Assignment 1
 * @author: Jarod Tang, ID = 1175923, email = jarodt@student.unimelb.edu.au
 */

// The below code used the template for a Singleton pattern outlined in this website: https://www.javatpoint.com/singleton-design-pattern-in-java 
import java.util.Scanner;

public class ScannerSingleton {
   private static Scanner scanner;

   public static Scanner getScanner() { // returns instance of Scanner (scanner)

      if (scanner == null) { // checks if scanner has been initialised 
         
         // otherwise, create new scanner object
         scanner = new Scanner(System.in);
      }
      
      return scanner;
   }

}

