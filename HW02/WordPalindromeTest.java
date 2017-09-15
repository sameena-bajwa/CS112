 /* File: WordPalindromeTest.java
 * Author: Sameena Bajwa
 * Date: January 30th 2016
 * Purpose: This program will test if an sentence is a word palindrome
 */


import java.util.Scanner;

public class WordPalindromeTest { 
  
  public static void main(String[] args) {
    
    // Print out welcome message
    System.out.println("\nWelcome to the Word Palindrome Test Program!");
       
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
    
        // Checks every character in the array and removes it if it is present
        // in the inputted string
        for (int i = 0 ; i < charsToRemove.length ; i ++) {
        
            //Converts the characters to a string, removes the character from the input
            String removedCharacter = Character.toString(charsToRemove[i]);
            lowerInput = lowerInput.replace(removedCharacter, "");
        }
        
        String[] words = lowerInput.split("\\s+");
        
        // Printing an array
        System.out.print("\n[ ");
        for (int i = 0; i < words.length - 1; ++i) 
            
            System.out.print(words[i] + ", ");
        
        System.out.println(words [words.length - 1] + " ]");
            
        
        // Integer that represents that halfway point of the array
        int middleOfArray = words.length / 2;
        String result = "true";
    
        // Checks to see if the first and last word of the array match
        // Loop continues until the middle of the array is reached
        for (int i = 0 ; i <= middleOfArray ; i ++) {
            
            if (words[i].equals(words[words.length - 1 - i]) == false){
                result = "false";
                break;
            }
        }
    
        if (result == "false") {
            System.out.println("Not a word palindrome");
        }
        else {
            System.out.println("Word palindrome!");
        }
        
    }
  
}

}