/* File: BigMath.java
 * Date: 2/11/16
 * Author:  Sameena Bajwa
 * Class: CS 112, Spring 2016
 * Homework: HW 04, Problem B2
 * Purpose: This is a template for the BigMath class.   
 */

public class BigMath  { 
    
    public static final int SIZE = 50;  
    
    // compares the value of two big integers
    
    public static int compare(BigInt N, BigInt M) {
        
        // return 1 if N is longer than M
        
        if (N.length() > M.length()) {
            
            return 1;
            
        }
        
        // return -1 if M is longer than N
        
        else if (N.length() < M.length()) {
            
            return -1;
            
        }
        
        // if they are the same size, iterate through the number to find which 
        // is a larger value
        
        else if (N.length() == M.length()) {
            
            
            for (int i = 0; i < N.length(); i ++ ) {
                
                if (N.digitAt(i) > M.digitAt(i)) {
                    
                    return 1;
                }
                
                else if (N.digitAt(i) > M.digitAt(i)) {
                    
                    return -1;
                }
            }
            
        }  
        
        // return 0 if the two numbers are equal 
        
        return 0;
                
    }
    
    // return true if N and M are equal, false otherwise 
    
    public static boolean equals(BigInt N, BigInt M) {
        
        if (compare(N, M) == 0) {
            
            return true;
        }
        
        return false;
    }
    
    // return the sum of two big integers N and M
    
    public static BigInt add(BigInt N, BigInt M) {
        
        // stacks to hold digits of N and M
        IntStack SA = new IntStack();   
        IntStack SB = new IntStack();
        
        // stack to hold digits of the sum of N and M
        IntStack SS = new IntStack();
        
        int carry = 0;
        
        // push digits of N onto stack SA
        
        for (int i = 0; i < N.length(); i ++) {
            
            SA.push(N.digitAt(i));
        }
        
        // push digits of M onto stack SB
        
        for (int j = 0; j < M.length(); j ++) {
            
            SB.push(M.digitAt(j));
        }
        
        
        // if both integers are not yet empty, add each digit 
        
        while ((SA.isEmpty() == false) && (SB.isEmpty() == false)) {
            
            // pop values from S and T
            
            int saTop = SA.pop();
            int sbTop = SB.pop();
            
            int result = carry + saTop + sbTop;
            
            // if the top two values of the stacks and the carry value is 
            // greater than 10, change value of carry
            
            if (result >= 10) {
                
                carry = 1;
            }
            
            else {
                
                carry = 0;
            }
            
            // push the digits that were just added onto the stack
            
            SS.push(result % 10);
        }
        
        // if SA is empty and SB is not, only add using values from M
        
        while ((SA.isEmpty() == true) && (SB.isEmpty() == false)) {

                int sbTop = SB.pop();
                int result = carry + sbTop;
                
                if (result >= 10) {
                    carry =1;
                }
                else{
                    carry = 0;
                }
                
                SS.push(result % 10);
            }
        
        // if SB is empty and SA is not, only add values from N
        
        while ((SA.isEmpty() == false) && (SB.isEmpty() == true)) {
            
                int saTop = SA.pop();
                int result = carry + saTop;
                
                if (result >= 10) {
                    carry = 1;
                }
                
                else 
                    carry = 0;
                
                
                SS.push(result % 10);
            }
        
        // if carry still holds a value of 1 after both stacks are empty, push 1 
        // onto SS
        
        if (carry == 1) { 
            
            SS.push(1);
        }
        
        // pop values from SS into an array
        
        int [] newArray = new int [SS.size()];
        
        for (int f = 0; f < newArray.length; f ++) {
            
            newArray[f] = SS.pop();
            
        }
        
        // big integer that will hold the values from the new array
        
        BigInt bigIntArray = new BigInt();
        bigIntArray.putValue(newArray);
        
        return bigIntArray;

        
    }
    
    // multiply a big integer N by an integer m
    
    private static BigInt multByInt(BigInt N, int m) {
        
        // stacks to hold values of N and the leftmost digit of the product
        
        IntStack SA = new IntStack();   
        IntStack SS = new IntStack();
        
        int carry = 0;
        
        // push values of N into int stack SA
        
        for (int i = 0; i < N.length(); i ++) {
            
            SA.push(N.digitAt(i));
        }
        
        // SA is not empty, proceed with the multiplication process
        while (SA.isEmpty() == false) {
            
            // pop digit from SA to multiply by m and add carry
            
            int saTop = SA.pop();
            int result = ( saTop * m) + carry;
            
            // if result is larger than 10, get the carry value by finding 
            // rightmost digit
            
            if (result >= 10) {
                
                carry = result / 10;
            }
            else 
                carry = 0;
            
            // push leftmost digit of result onto the stack
            
            SS.push(result % 10);
        }
        
        if (carry > 0) {
            
            SS.push(carry);
        }
        
        // pop values of SS into a new array
        
        int [] newArray = new int [SS.size()];
        
        for (int f = 0; f < newArray.length; f ++) {
            
            newArray[f] = SS.pop();
            
        }
        
        // big integer that will hold values of new array
        
        BigInt bigIntArray = new BigInt();
        bigIntArray.putValue(newArray);
        
        return bigIntArray;

    }
    
    public static BigInt mult(BigInt N, BigInt M) {
        
        // Stack for M to be pushed onto
        IntStack S = new IntStack();
        
        // Empty stack to hold big ints
        BigIntStack P = new BigIntStack();
        
        // BigInt to hold product of N and M
        BigInt product = new BigInt();
        
        // BigInt to hold result that must go into the big int stack
        BigInt result = new BigInt();
        
        // push values of M into stack S
        for (int i = 0; i < M.length(); i ++) {
            
            S.push(M.digitAt(i));
        }
        
        while (S.isEmpty() == false) {
            
            int sTop = S.pop();
            
            // product of N and the first digit of M, using multbyInt()
            
            product = multByInt(N, sTop);
            
            // push product onto stack P 
            P.push(product);
            
            
            N = multByInt(N, 10);
        }
        
        
        // iterate through stack P 
        
        for (int f = 0; f < P.size(); f ++) {
            
            // override result value with the sum of result and pop value
            result = add (result, P.pop());
            
        }
        
        return result;     
    }
    
    
    public static void main(String [] args) {
        
        System.out.println("\nUnit Test for BigMath Class");
        
        BigInt A = new BigInt("9999");
        
        BigInt B = new BigInt(1);
        
        int[] c = {1,8,2,7};
        BigInt C = new BigInt(c);
        
        BigInt D = new BigInt(234);
        BigInt E = new BigInt(235);
        BigInt F = new BigInt(9999);
        BigInt Z = new BigInt(0);
        
        System.out.println("\nTest 1: Should be:\n4 9999");
        System.out.println( A.length() + " " + A );
        
        System.out.println("\nTest 2: Should be:\n1 1");
        System.out.println( B.length() + " " + B );
        
        System.out.println("\nTest 3: Should be:\n4 1827");
        System.out.println( C.length() + " " + C );
        
        System.out.println("\nTest 4: Should be:\n3 234");
        System.out.println( D.length() + " " + D );
        
        System.out.println("\nTest 5: Should be:\n-1");
        System.out.println( compare(D,E) );
        
        System.out.println("\nTest 6: Should be:\n1");
        System.out.println( compare(E,D) );
        
        System.out.println("\nTest 7: Should be:\n1");
        System.out.println( compare(C,D) );
        
        System.out.println("\nTest 8: Should be:\n-1");
        System.out.println( compare(D,C) );
        
        System.out.println("\nTest 9: Should be:\n1");
        System.out.println( compare(A,Z) );
        
        System.out.println("\nTest 10: Should be:\n-1");
        System.out.println( compare(Z,A) );
        
        BigInt G = new BigInt(9999);        
        System.out.println("\nTest 11: Should be:\n0 true");
        System.out.println( compare(A,G) + " " + equals(A,G) );
        
        System.out.println("\nTest 12: Should be:\n-1 false");
        System.out.println( compare(E,F) + " " + equals(F,E) );
        
        System.out.println("\nTest 13: Should be:\n2");
        System.out.println( add(B,B) );
        
        System.out.println("\nTest 14: Should be:\n1827");
        System.out.println( add(C,Z) );
        
        System.out.println("\nTest 15: Should be:\n1827");
        System.out.println( add(Z,C) );
        
        System.out.println("\nTest 16: Should be:\n0");
        System.out.println( add(Z,Z) );
        
        System.out.println("\nTest 17: Should be:\n10000");
        System.out.println( add(A,B) );
        
        System.out.println("\nTest 18: Should be:\n10000");
        System.out.println( add(B,A) );
        
        System.out.println("\nTest 19: Should be:\n2061");
        System.out.println( add(C,D) );
        
        System.out.println("\nTest 20: Should be:\n2061");
        System.out.println( add(D,C) );
        
        System.out.println("\nTest 21: Should be:\n469");
        System.out.println( add(E,D) );
        
        System.out.println("\nTest 22: Should be:\n469");
        System.out.println( add(D,E) );  
        
        System.out.println("\nTest 23: Should be:\n1827");
        System.out.println( multByInt(C,1) ); 
        
        System.out.println("\nTest 24: Should be:\n3654");
        System.out.println( multByInt(C,2) );
        
        System.out.println("\nTest 25: Should be:\n0");
        System.out.println( multByInt(Z,8) );
        
        System.out.println("\nTest 26: Should be:\n99990");
        System.out.println( multByInt(A,10) );
        
        System.out.println("\nTest 27: Should be:\n4");
        BigInt Two = new BigInt(2); 
        System.out.println( mult(Two,Two) );
        
        System.out.println("\nTest 28: Should be:\n468  468");
        System.out.println( mult(D,Two) + "  " + mult(Two,D));
        
        System.out.println("\nTest 29: Should be:\n54990  54990");
        System.out.println( mult(D,E) + "  " + mult(E,D));
        
        System.out.println("\nTest 30: Should be:\n2339766  2339766");
        System.out.println( mult(D,A) + "  " + mult(A,D));
        
        System.out.println("\nTest 31: Should be:\n1013459064417062778220931703313214582990003217000");
        BigInt T = mult(A, mult( B, mult( C, mult( D, mult ( E, F ) ) ) ) ); 
        System.out.println( mult( T, mult( T, T ) ) );
    }
    
    
}


