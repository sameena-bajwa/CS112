/* File: MyStringArray.java
 * Author: Sameena Bajwa
 * Date: February 1, 2016
 * Purpose: This is a program that implements a library of methods for manipulating
 * strings represented explicity as arrays of chars
 * NOTE: You MUST rewrite this header comment to describe your solution.
 * For each method, instead of using character/string/array libraries, 
 * I used a combination of loops and indexing, in order to  * access the 
 * characters/digits I needed to manipulate. Each method returns an error value
 * when necessary.      
 */

public class MyStringArray { 
  
  // Declare various final values to be used to indicate errors of various types
  
  public static final char[] errorString  = { 'N', 'a', 'S' };   
  public static final char errorCharacter = '#';
  public static final int errorInteger    = Integer.MIN_VALUE;
  public static final double errorDouble  = Double.NaN;          
    
    // Used indexing to find the character at location i of the array
  
    public static char charAt(char[] s, int i) {
        
        // Loop to determine if location i is valid for the given character array
       
        if (i < 0 || i > s.length) {            
            return errorCharacter;
        }
        else           
            return s[i];
      }
  
  // Loop to calculate the length of an array by using an increment inside a "for"
  // loop that will iterate the amount of times equal to the amount of characters
  // in the array
    
  public static int length(char[] s) {
    
      // increment to count the amount of characters            
      int size = 0;
      
      for ( int i:  s) {
          size++;
      }
      
      return size;    
  }
  
  // Returns a new array that only covers the original array from point l
  // to point r - 1
  
  public static char[] subString(char[] s, int l, int r) {
      
      // Length of new array      
      int difference = r - l;
      
      // Initialize new array for the substring      
      char [] shortArray = new char [difference];
      
      // Return error string if left and right bounds are not valid
      
      if (l < 0 || r > s.length) {
          return errorString;
      }
      
      else {
          
          // Iterate through new array to add values into it
          
          for (int i = 0; i < difference; i++) {
              shortArray[i] = s[l + i];
          }
          
          return shortArray;
      }    
  }
  
  // Returns the same array except upper case letters are now converted to 
  // lower case
  
  public static char[] toLowerCase(char[] c) {
      
      //Initialize an array to put the lowercase letters in  
      char [] lowerArray = new char [c.length];
      
      // ASCII value of the letters
      int newNumber;
      
      // Convert new ASCII value to the new letter
      char newLetter;
      
      // Loop through characters of the array and change them accordingly 
      for (int i = 0; i < c.length; i++) {
          
          newNumber = (int)c[i];
          
          // If letter is already lowercase, do not change it 
          if (newNumber >= 97 && newNumber <= 122) {
              
              newLetter = (char)newNumber;
              lowerArray[i] = newLetter;
              continue;
          }
          
          // If character is a symbol, do not change it
          else if (newNumber < 64){
              
              newLetter = (char)newNumber;
              lowerArray[i] = newLetter;
              continue;
          }
          
          // Convert uppercase letter to lowercase letter, assign accordingly
          newNumber = newNumber + 32;
          newLetter = (char)newNumber;
          lowerArray[i] = newLetter;
      }
      
      return lowerArray;
  }
  
  // Returns a new array that represents the merge of array a and array b
  
  public static char[] concatenate(char[] a, char[] b) {
    
      // Initialize new array to hold both arrays a and b 
      char [] longArray = new char [a.length + b.length];
      
      // Assign values of array a and b into the new array
      
      for (int i = 0; i < longArray.length; i++) {
          
          if (i < a.length){
              longArray[i] = a[i];
          }
          
          else {
              longArray[i] = b[i - a.length];
          }
        }
      
        return longArray;  
  }
  
  // Returns the integer value of all integers present in the array concatenated
  
  public static int intValueOf(char[] a) {
    
      // Initialize variable for the integer that will be returned
      int arrayInt = 0;
      
      // Initialize variable to see if number is negative
      boolean negative = false;
       
      // Iterate through entirety character array 
      for (int i = 0; i < a.length; i++) {
          
          // If a character is a digit, multiply it by some value of 10 and 
          // add it to the returned integer value
          
          if (Character.isDigit(a[i]) == true) {
              arrayInt = arrayInt * 10 + a[i] - '0';
          }
          
          // If character displays the negative sign, boolean variable equates
          // to true
          
          else if (a[0] == '-') {
               negative = true;
               i++;
               arrayInt = arrayInt * 10 + a[i] - '0';
          }
      
          else {
              return errorInteger;
          }
      }
      
      // If boolean variable equates to true, make arrayInt a negative integer
      
      if (negative == true) {
          arrayInt *= -1;
      }
      
      return arrayInt;
  }
  
  
  // Returns the double that the array creates once all numbers and a decimal
  // point are combined
  
  public static double doubleValueOf(char[] a) {
    
      // Initialize variable to hold the returning double
      double arrayDouble = 0.0;
      
      // Initialize boolean variable to determine if final number is negative
      boolean negative = false;
      
      // Initialize variable to count the amount of decimal points present
      int decimal = 0;
      
      // Initialize variable to count the number of digits to the right of the decimal 
      int numRight = 0;
      
      // Iterate through entirety of character array 
      for (int i = 0; i < a.length; i ++) {
         
          if (Character.isDigit(a[i]) == true) {
               arrayDouble = arrayDouble * 10.0 + a[i] - '0';
          }
          
          // If a decimal point is present, increment the decimal variable by 1
          else if (a[i] == '.' && decimal == 0) {
              decimal ++;      
              numRight = a.length - 1 - i;
          }
          
          // If a negative sign is present, assign the boolean variable to true
          else if (a[0] == '-') {
               negative = true;
          }
          
          else {
              return errorDouble;
          }
          }
      
      // Divide the array by the power of 10 that corresponds to the length of 
      // the array
      
      if (decimal == 1) {
          arrayDouble = arrayDouble / java.lang.Math.pow(10.0, numRight);
          }
            
      if (negative == true) {
          arrayDouble *= -1;
      }
      
      return arrayDouble;        
                       
  }
  
  // Convert a given integer m into a character array 
  
  public static char[] int2MyString(int n) {
    
      // Initialize a new character array that corresponds to the length of 
      // integer n
      
      char [] array = new char [(int)(java.lang.Math.log10(n) + 1)];
      
      for (int i = 0; i < array.length; i ++) {
          
          // Assign each spot of the array to a character of n 
          array[array.length - 1 - i] = (char)('0' + (n % 10));
          n /= 10;
      }
      
      return array;
  }
  
  // Prints the character array in proper format
  
  public static void printCharArray(char[] A) {
    for(int i = 0; i < A.length; ++i) {
      System.out.print(A[i]);
    }
    System.out.println(); 
  }
  
  
   public static void main(String[] args) {
      
      System.out.println("\nGrading program for MyStringArray library\n");
      int testNum = 0; 
      
      System.out.println("Test " + (++testNum) + ": Should be:\n8"); 
      char[] test = "CS112 A1".toCharArray(); 
      System.out.println(length(test)); 
      System.out.println(); 
      
      System.out.println("Test " + (++testNum) + ": Should be:\nC"); 
      System.out.println(charAt(test,0)); 
      System.out.println(); 
            
      System.out.println("Test " + (++testNum) + ": Should be:\n1"); 
      System.out.println(charAt(test,7)); 
      System.out.println(); 
                  
      System.out.println("Test " + (++testNum) + ": Should be:\n#"); 
      System.out.println(charAt(test,9)); 
      System.out.println();
      
      System.out.println("Test " + (++testNum) + ": Should be:\nCS112"); 
      System.out.println(subString(test,0,5)); 
      System.out.println(); 
            
      System.out.println("Test " + (++testNum) + ": Should be:\n12 A1"); 
      System.out.println(subString(test,3,8)); 
      System.out.println(); 
                  
      System.out.println("Test " + (++testNum) + ": Should be:\nNaS"); 
      System.out.println(subString(test,-1,4)); 
      System.out.println(); 
                  
      System.out.println("Test " + (++testNum) + ": Should be:\nNaS"); 
      System.out.println(subString(test,1,9)); 
      System.out.println();  
                        
      System.out.println("Test " + (++testNum) + ": Should be:\ncs112 a1"); 
      System.out.println(toLowerCase(test)); 
      System.out.println();
                              
      System.out.println("Test " + (++testNum) + ": Should be:\nCS112 A1"); 
      System.out.println(test); 
      System.out.println();
                        
      System.out.println("Test " + (++testNum) + ": Should be:\nCS112 A1CS112 A1"); 
      System.out.println(concatenate(test,test)); 
      System.out.println();
     
      System.out.println("Test " + (++testNum) + ": Should be:\n234"); 
      test = "234".toCharArray(); 
      System.out.println(intValueOf(test)); 
      System.out.println(); 
      
      System.out.println("Test " + (++testNum) + ": Should be:\n-234"); 
      test = "-234".toCharArray(); 
      System.out.println(intValueOf(test)); 
      System.out.println(); 
      
      System.out.println("Test " + (++testNum) + ": Should be:\n-2147483648"); 
      test = "234.4".toCharArray(); 
      System.out.println(intValueOf(test)); 
      System.out.println(); 
      
      System.out.println("Test " + (++testNum) + ": Should be:\n-2147483648"); 
      test = "23a4".toCharArray(); 
      System.out.println(intValueOf(test)); 
      System.out.println();
      
      System.out.println("Test " + (++testNum) + ": Should be:\n3.141592"); 
      test = "3.141592".toCharArray(); 
      System.out.println(doubleValueOf(test)); 
      System.out.println();   
      
      System.out.println("Test " + (++testNum) + ": Should be:\n-3.141592"); 
      test = "-3.141592".toCharArray(); 
      System.out.println(doubleValueOf(test)); 
      System.out.println(); 
            
      System.out.println("Test " + (++testNum) + ": Should be:\n10.0"); 
      test = "10.".toCharArray(); 
      System.out.println(doubleValueOf(test)); 
      System.out.println(); 
      
      System.out.println("Test " + (++testNum) + ": Should be:\n0.5"); 
      test = ".5".toCharArray(); 
      System.out.println(doubleValueOf(test)); 
      System.out.println();
            
      System.out.println("Test " + (++testNum) + ": Should be:\n0.0"); 
      test = ".".toCharArray(); 
      System.out.println(doubleValueOf(test)); 
      System.out.println();
      
      System.out.println("Test " + (++testNum) + ": Should be:\n234.0"); 
      test = "234".toCharArray(); 
      System.out.println(doubleValueOf(test)); 
      System.out.println();
      
      System.out.println("Test " + (++testNum) + ": Should be:\nNaN"); 
      test = "3.141.592".toCharArray(); 
      System.out.println(doubleValueOf(test)); 
      System.out.println(); 
      
      System.out.println("Test " + (++testNum) + ": Should be:\nNaN"); 
      test = "3.141a592".toCharArray(); 
      System.out.println(doubleValueOf(test)); 
      System.out.println(); 
      
      int n = 12345; 
      System.out.println("Test " + (++testNum) + ": Should be:\n12345"); 
      printCharArray( int2MyString(n) ); 
      System.out.println(); 
      
      n = -45; 
      System.out.println("Test " + (++testNum) + ": Should be:\n-45"); 
      printCharArray( int2MyString(n) ); 
      System.out.println(); 
      

   }
  
}
