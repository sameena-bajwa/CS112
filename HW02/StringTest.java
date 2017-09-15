/* File: PalindromeTest.java
 * Author: Sameena Bajwa
 * Date: January 30th 2016
 * Purpose: This program will test if an inputted word or sentence is a palindrome.
 */


import java.util.Scanner;

public class PalindromeTest { 
  
  public static void main(String[] args) {
    
    // Print out welcome message
    
    System.out.println("\nWelcome to the String Test Program!");
    System.out.println("Demonstrate various features of the String Library");
    
    // Define a scanner for user input
    
    Scanner userInput = new Scanner(System.in);
    

    System.out.println("\nType in a sentence or Control-d to end:"); 
    while(userInput.hasNextLine()) {
       String line = userInput.nextLine();
       line = line.replace(",", "");
       System.out.println("\"" + line + "\""); 
    }
    System.out.println("bye!");
 
    
  }
  
}
