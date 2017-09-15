/* File: BigInt.java
 * Date: 2/15/16
 * Author:  Sameena Bajwa
 * Class: CS 112, Spring 2016
 * Homework: HW 04, Problem B.2
 * Purpose:  
 */



public class BigInt  {  
    
    private int[] A = new int[BigMath.SIZE]; 
    
    // These constructors will build a new instance of this class
    // and return it, see the Unit Test below for how to use them. 
    
    public BigInt() {           
    }

    // builds a big integer that is represented by the string S
    
    public BigInt(String s) {  
        
        putValue(s);
    }
    
    // builds a big integer that is represented by the array B
    
    public BigInt(int[] B) {
        
        putValue(B);
    }
    
    // builds a big integer that is represented by the integer n
    
    public BigInt(int n) {
        
        putValue(n);
    }
    
    // returns number of integers in the big integer, excluding all leading zeros
    
    public int length() {
        
        // initialize beginning length to be the size of the big integer, including
        // all leading zeroes
        
        int length = BigMath.SIZE;
        
        for (int i = 0; i < BigMath.SIZE; i ++) {
            
            // decrease length by 1 if there is a leading zero
            if (A[i] == 0) {
                
                length --;
            }
            
            else
                break;
            
            // big integer 0 will have a length of 1
            
            if (length == 0) {
               
               length = 1;
            }
        }
        
        return length;
    }
    
    // big integer holds the value that is represented by the string S
    
    public void putValue(String s) {
        
        // variable to mark where the leading zeros end and where the big integer
        // begins
        
        int digitsBegin = BigMath.SIZE - s.length();
        
        // iterate through the digits of the big integer, without leading zeroes
        
        for (int i = digitsBegin; i < BigMath.SIZE; i ++) {
            
            // place every integer in S into big integer
            
            int nextDigit = i - digitsBegin;
            
            A[i] = s.charAt(nextDigit) - '0';
            
        }

    }
    
    // create big integer's value from an integer array B
    
    public void putValue(int[] B) {
        
        // variable to mark where the leading zeroes end and big integer digits
        // begin
        
        int digitsBegin = BigMath.SIZE - B.length;
        
        for (int i = digitsBegin; i < BigMath.SIZE; i ++) {
            
            // place every digit from B into the big integer
            
            int nextDigit = i - digitsBegin;
            
            A[i] = B[nextDigit];
            
        }

    }
    
    // convert integer n into an array of digits that represent the big integer
    
    public void putValue(int n) {
        
        // increment in while loop
        int counter = 1;
        
        while (n > 0) {
            
            // variable to decide position of BigInt to be modified during 
            // an iteration
            
            int arrayInts = BigMath.SIZE - counter;
            
            // use modulus division to find rightmost digit
            int remainder = n % 10;
            
            A[arrayInts] = remainder;
            
            // decrease the length of n by one digit's place for the next loop
            n /= 10;
            
            counter++;
        }
        

    }
    
    // return digit at slot i 
    
    public int digitAt(int i) {
        
        // count amount of zeroes up until the first digit is found
        
        int amountZero = 0;
        
        for (int j = 0; j < BigMath.SIZE; j++) {
            
            // break loop once first digit is found
            if (A[j] != 0)
                
                break;
            
            amountZero ++;
            
        } 
        
        // If entire BigInt is zeroes, the amount of elading zeroes is 49
        
        if (amountZero == 50) {
            
            amountZero = 49;
        }
        
        
        // location i, once leading zeroes are ignored 
        return A[amountZero + i];
        
        }
    
    // insert a new digit n into the big interger at location i 
    
    public void putDigitAt(int i,int n) {
        
        // count amount of leading zeroes until first digit is found
        
        int amountZero = 0;
        
        for (int j = 0; j < BigMath.SIZE; j++) {
            
            if (A[j] != 0) {
                break;
            }
            
            amountZero ++; 
        }
        
        // place n into the location found once leading zeroes are ignored
        
        A[amountZero + i] = n;
        
    }
    
    // return string represetnation of the big integer
    
    public String toString() {
        
        String array = "";
        
        // count all leading zeroes 
        int counter = 0;
        
        for (int i = 0; i < BigMath.SIZE; i ++) {
            
            if (A[i] != 0) {
                break;
            }
            
            counter ++;
        }
        
        // iterate through array, once leading zeroes are ignored
        
        for (int j = counter; j < BigMath.SIZE; j ++) {
            
            // place elements from big integer into the string
            
            array += A[j];
            
        }
        
        // if array is still empty, array = 0
        
        if (array == "") {
            
            array = "0";
            
        }
        
        return array;
        
        }

           
    
    public static void main(String [] args) {
        
        System.out.println("\nUnit Test for BigInt Class");
        
        int[] a = {3,1,4,1,5,9,2,6,5,3,5,8,9,7,9,3,2,3,8,4,6,2,6,4,3,3,8};
        String b = "314159265358979323846264338327950288";
        int c = 314159265;
        
        BigInt A = new BigInt();
        A.putValue(a);
        
        System.out.println("\nTest 1: Should be:\n27");
        System.out.println( A.length() );
        
        System.out.println("\nTest 2: Should be:\n314159265358979323846264338");
        System.out.println( A );
        
        
        
        BigInt B = new BigInt();
        B.putValue(b);
        
        System.out.println("\nTest 3: Should be:\n36");
        System.out.println( B.length() );
        
        System.out.println("\nTest 4: Should be:\n314159265358979323846264338327950288");
        System.out.println( B );
        
        BigInt C = new BigInt();
        C.putValue(c);  
        
        System.out.println("\nTest 5: Should be:\n9");
        System.out.println( C.length() );
        
        System.out.println("\nTest 6: Should be:\n314159265");
        System.out.println( C );
        
        BigInt Z = new BigInt();       // will be zero
        
        // Special case: 0 will have length 1 and print out as a single digit
        
        System.out.println("\nTest 7: Should be:\n1");
        System.out.println( Z.length() );
        
        System.out.println("\nTest 8: Should be:\n0");
        System.out.println( Z );
        
        BigInt One = new BigInt();
        One.putValue(1);
        
        System.out.println("\nTest 9: Should be:\n1 1");
        System.out.println( One.length() + " " + One);
        
        // Even if string or array has leading zeros, you just put them in, and they get
        // treated like any other leading zeros when you check length and print out. 
        
        System.out.println("\nTest 10: Should be:\n1");
        BigInt D = new BigInt();
        D.putValue("000000004");
        System.out.println( D.length() ); 
        
        System.out.println("\nTest 11: Should be:\n4");
        System.out.println( D ); 
        
        System.out.println("\nTest 12: Should be:\n3 234");
        BigInt E = new BigInt();
        int[] e = {0,0,0,0,2,3,4};
        E.putValue(e);
        System.out.println( E.length() + " " + E ); 
        
        System.out.println("\nTest 13: Should be:\n3 4 5");
        System.out.println( C.digitAt(0) + " " + C.digitAt(2) + " " + C.digitAt( C.length() - 1 ) );
        
        System.out.println("\nTest 14: Should be:\n1000001");
        BigInt F = new BigInt("1000001");
        System.out.println( F ); 
        
        System.out.println("\nTest 15: Should be:\n12345");
        int[] g = {1,2,3,4,5};
        BigInt G = new BigInt(g);
        System.out.println( G ); 
        
        System.out.println("\nTest 16: Should be:\n54321");
        System.out.println( new BigInt(54321) ); 
        
        System.out.println("\nTest 17: Should be:\n984159265");
        C.putDigitAt(0,9);
        C.putDigitAt(1,8);
        System.out.println( C );
        
        System.out.println("\nTest 18: Should be:\n984100005");
        C.putDigitAt(4,0);
        C.putDigitAt(5,0);
        C.putDigitAt(6,0);
        C.putDigitAt(7,0);
        System.out.println( C );
        
        System.out.println("\nTest 19: Should be:\n100005");
        C.putDigitAt(0,0);
        C.putDigitAt(0,0);
        C.putDigitAt(0,0);
        System.out.println( C );
        
        System.out.println("\nTest 20: Should be:\n5");
        C.putDigitAt(0,0);
        System.out.println( C );     
    }      
}

