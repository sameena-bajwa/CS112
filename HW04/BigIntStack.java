/* File: BigIntStack.java
 * Date: 2/11/16
 * Author:  Sameena Bajwa 
 * Class: CS 112, Spring 2016
 * Purpose: This is part of the code distribution for HW 04, Problem B.2; this class is an Abstract Data
 *      Type for a stack, a data structure in which you can push integers on one end, pop them
 *      of the same end, and check if the stack is empty. You may not touch anything that is 
 *      not at the top of the stack. Think of a pile of plates, and you can't do anything but 
 *      touch the top plate!
 * 
 * Warning: No error checking is done for overflow or underflow!
 */

public class BigIntStack  { 
    
    // default size, can be changed by the constructor
    
    private int SIZE = BigMath.SIZE;            
    
    private BigInt [] A = new BigInt[SIZE];
    
    private int next = 0;           
   
    
    public BigIntStack() {
        
    }
    
    public BigIntStack(int n) {
        
        SIZE = n;
        
        A = new BigInt[SIZE];
     
    }

    
    public int size() {
        return next; 
    }
    
    public void push(BigInt n) {
        
        A[next] = n;
        ++ next;
    }
   
    
    public BigInt pop() {
        --next;
        return A[next];
    }

    public BigInt top() {
        
        return A[next - 1];
    }
    
 
    public boolean isEmpty() {
        return (next == 0);
    }
   
    
    public String toString() {
        String s = "| ";
        
        for(int i = 0; i < next; ++i) {
            s += (A[i] + " ");
        }
        return s;    
    }
    
    // Unit Tests
    
    public static void main(String [] args) {
        
        System.out.println("\nUnit Test for BigIntStack Class\n");
        
        BigIntStack One = new BigIntStack();;
        
        BigInt A = new BigInt ("999999999999999999");
        BigInt B = new BigInt ("44444444444444");
        BigInt C = new BigInt ("11111111111111111111111");
        
        System.out.print("Values for Unit Test:\n");
        System.out.println("A = " + A.toString() + "\nB = " + B.toString() + "\nC = " + C.toString());
        

        One.push(A);
        One.push(B);
        One.push(C);
        
        System.out.print("\nTest 1 -- Printing stack: Should be: \n|999999999999999999 44444444444444 11111111111111111111111\n|");
        System.out.println( One.toString() );
        
        System.out.print("\nTest 2 -- Is Stack Empty: Should be:\n|false\n|");
        System.out.println( One.isEmpty()); 
        
        System.out.print("\nTest 3 -- Examining top element: Should be:\n11111111111111111111111\n");
        System.out.println( One.top());
        
        BigInt n = One.pop();
        BigInt m = One.pop();
        
        System.out.print("\nTest 4: Should be:\n| 999999999999999999  n = 11111111111111111111111 m = 44444444444444\n");
        System.out.println( One.toString() + " n = " + n + " m = " + m);
        
        
        
    }    
    
}

