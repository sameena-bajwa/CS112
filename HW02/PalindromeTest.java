/* File: PalindromeTest.java
 * Author: Sameena Bajwa
 * Date: January 30th 2016
 * Purpose: This program will test if an inputted word or sentence is a palindrome.
 */


import java.util.Scanner;

public class PalindromeTest { 
  
  public static void main(String[] args) {
    
    // Print out welcome message
    System.out.println("\nWelcome to the Palindrome Test Program!");
       
    // Define a scanner for user input
    Scanner userInput = new Scanner(System.in);
    
    System.out.println("\nType in a sentence to be tested or Control-d to end:"); 
    
    while(userInput.hasNextLine()) {
        String sentence = userInput.nextLine();
    
        // Converts inputted string to an all lower-cased string
        String lowerInput = sentence.toLowerCase();
    
        // Initialize an array of punctuation marks that need to be removed
        char[] charsToRemove = {'.', ',', ':', ';', '!', '?', '"', '\"', '/', '-', 
                               '(', ')', '~'};
    
        // Checks every character in the string for white space 
        for (int i = 0 ; i < lowerInput.length() ; i ++) {
        
            // Removes white space
            if (Character.isWhitespace( lowerInput.charAt(i) ) == true) {
                lowerInput = lowerInput.replace(" ", "");
            }
        }
    
        // Checks every character in the array and removes it if it is present
        // in the inputted string
        for (int i = 0 ; i < charsToRemove.length ; i ++) {
        
            //Converts the characters to a string, removes the character from the input
            String removedCharacter = Character.toString(charsToRemove[i]);
            lowerInput = lowerInput.replace(removedCharacter , "");
        }
    
        // Integer that represents that halfway point of the string
        int middleOfWord = lowerInput.length() / 2;
        String result= "true";
    
        // Checks to see if the first and last character of the string match
        // Loop continues until the middle of the word is reached
        for (int i = 0; i <= middleOfWord ; i ++) {
        
            if (lowerInput.charAt(i) != lowerInput.charAt(lowerInput.length()-1-i)){
                result = "false";
                break;
            }
        }
    
        if (result == "false") {
            System.out.println("Not a palindrome");
        }
        else {
            System.out.println("Palindrome!");
        }
    }
  
}

}