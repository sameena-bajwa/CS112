/*
 * Palindrome.java
 * 
 * Author: Sameena Bajwa
 * Boston University CS 112, Spring 2016
 * 
 * Purpose: This is an exercise in debugging by implementing a
 * palindrome-checker using an auxiliary array. 
 */


public class PalindromeRedux {
    
    public static final int SIZE = 10; 
    
    public static boolean debug = true; 
    
    // Check whether str is a palindrome by moving first half of the string
    // to an array, and then go backward through array and forward
    // through str and compare. 
    
    public static boolean palindrome(String str) {
        
        char[] A = new char[SIZE];        // array to hold first half of string
        int next = 0;                     // next location to put a character                  
        
        db("Calling palindrome ( " + str + " )"); 
        
        // Move first half of str into the array.
        for (int i = 0; i < str.length() / 2; i++) { 
            A[next] = str.charAt(i);
            db("Letters of string moving into array: " + str.charAt(i));
            ++next;
        }
        
        
        // Check if a string is either even or odd
        if ((str.length() % 2) != 0) {
            
            //If odd, the middle index should be incremented by one
            next++;
        }
        
        db("The index for the middle of the array is marked as: " + next);
        
        // Variable to count how many numbers from each half match
        int palindromeCounter = 0;
        
        // initialize count to see how many times the loop is iterated 
        int count = 1;
            
        // Compare array backwards with rest of str.
        
        for (int i = str.length() / 2; i < str.length(); i++) {                                 
            --next; 
            char c = A[next];
                        
            db("Round of loop: " + count);
            count ++;
            
            //Keep track of letters being compared
            db("Letters being compared from first half: " + c);        
            db("Letters being compared from second half: " + str.charAt(i));
            
            if (c == str.charAt(i))    {       
                palindromeCounter++;
                db("Number of matching letters: " + palindromeCounter);
            }
        }
        
        // Returns true if the amount of matching letters is equal to the amount
        // of characters compared
        
        if (palindromeCounter == str.length()/2) {
            return true;
        }
            return false;
        
        
        
    }     
    
    
    public static void db(String s) { 
        if(debug)
            System.err.println("\t" + s);
    }    
    
    public static void main(String[] args) {
        
        System.out.println("\nIs redder a palindrome? Should be true:");
        System.out.println(palindrome("redder"));
        
        System.out.println("\nIs reddet a palindrome? Should be false:");
        System.out.println(palindrome("reddet"));
        
        System.out.println("\nIs a string with one letter a palindrome? Should be true:");
        System.out.println(palindrome("a"));
        
        System.out.println("\nIs a string with an odd amount of letters a palindrome? Should be true:");
        System.out.println(palindrome("civic"));
                           
        System.out.println("\n Is an empty string a palindrome? Should be true:");
        System.out.println(palindrome(""));
                           
        
       

        
    }
    
}