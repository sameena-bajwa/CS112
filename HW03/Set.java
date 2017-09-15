/* File: Set.java
 * Date: 2/5/16
 * Author:  Sameena Bajwa (sbajwa@bu.edu)
 * Class: CS 112, Spring 2016
 * Purpose: 
 */


public class Set  {
    
    public static final int noElement = Integer.MAX_VALUE;
    public static final int SIZE = 20;
    
    // Return a set with no members, i.e., consisting of all Integer.MAX_VALUE values
    
    public static int[] getEmptySet() {
        int[] S = new int[SIZE];
        for(int i = 0; i < SIZE; ++i)
            S[i] = noElement;
        return S;
    }
    
    
    // Take an arbitrary array of integers, and return a set. The input array S
    // may have duplicate values, so you will have to remove the duplicates (hints below);
    // You will, of course, have to sort the values, so use Insertion Sort (from my slides). 
    
    public static int[] intArray2Set(int[] A) {
        
        // Initialize returned set to be of length SIZE
        int [] S = getEmptySet();
        
        removeDuplicates(A);
        
        for (int i = 0; i < A.length; i ++) { 
            
            S[i] = A[i];
        }
        
        return S;
    }
    
    //Take a set and return a String representation; you may simply print out
    // the list (in order) but of course do not print out the Integer.MAX_VALUE
    // values, and you should use curly braces and commas. 
    
    public static String setToString(int[] S)  {
        
        String s = "{";
        
        for (int i = 0; i < S.length; i ++) {
            
            // Do not include integer.MAX_VALUE elements in the returned string
            if (S[i] == noElement) {
                continue;
            }
 
            s += S[i] + ",";
        }
        
        s += "}";
        
        s = s.replace(",}", "}");

        return s;
  
    } 
    
    // Return the number of elements in the set
    
    public static int size(int[] S) {
        
        // Increment to count the size of the set 
        int count = 0;
        
        for (int i = 0; i < S.length; i ++) {
            
            if (S[i] != noElement) {
                
                count ++;
            }
        }
        
        return count;
    }
    
    // Return true if S is the empty set (has no members)
    
    public static boolean isEmpty(int[] S) {
        
        return ( S[0] == noElement );
    }
    
    // Return true if n is in the set and false otherwise
    
    public static boolean member(int n, int[] S) {
        
        for (int i = 0; i < S.length; i ++) {
            
            if (n == S[i]) {
                
                return true;
            }
        }
        return false;
    }
    
    // Returns true if S is a subset of T, that is, every member of S is a member of T.
    
    public static boolean subset(int[] S, int[] T) {
         
        for (int i = 0; i < S.length; i ++) {
            
            // If any character of S is not in T, return false
            
            if ((member(S[i],  T)) == false) {
                
                return false;
            }
        }
        
        return true;
        
    }

    // Returns to true if S is equal to T, false otherwise
    
    public static boolean equal(int[] S, int[] T) {
        
        if (S.length != T.length) {
            
            return false;
        }
        
        for (int i = 0; i < S.length; i ++) {
            
            // If any element of S is not in T, return false
            
            if (S[i] != T[i]) {
                
                return false;
            }
        }
        
        return true;
       
    }
    
    // Add number k to set S and return the outcome as a new set
    // if k is already in S, return the same value of S
    
    public static int[] add(int k, int[] S) {
        
        // Initialize new set that will be what k is added to
        
        int [] addSet = new int[SIZE + 1];
        
        // Checks to see if k is already in S
        
        if ((member(k, S)) == true) {
            
            return S;
        }
        
        // k is set to the last value of the new set
        
        addSet[addSet.length - 1] = k;
        
        for (int i = 0; i < addSet.length; i ++) {
            
            if (addSet[i] != k) {
                addSet[i] = noElement;
            }
        }
        
        return addSet;
        
        }

    // Remove k from set S and return the outcome as a new set,
    // if k is already in set S, return the original S
    
    public static int[] remove(int k, int[] S) {
        
        // Initialize a new set that will be set S without k 
        
        int [] removeSet = new int[SIZE];
        
        if ((member (k, S)) == false) {
            
            return S;
        }
        
        for (int i = 0; i < S.length; i ++) {
            
            if (S[i] == k) {
                removeSet[i] = noElement;
            }
            else
                removeSet[i] = S[i];
        }
        
        removeDuplicates(removeSet);
        
        
        return removeSet;
    }
    
    // Returns a new set representing the union of S and T 
    
    public static int[] union(int[] S, int[] T) {
        
        // Initialize new set for the union of S and T
        
        int [] combinedArray = new int [SIZE + T.length];
        
        // Checks to see if either set is empty
        
        if (isEmpty(S) == true){
            
          return T;
        }
        
        if (isEmpty(T) == true){
            
          return S;
        }

        // Iterate through both arrays S and T, adding each element to the
        // new combinedArray
        
        for (int i = 0; i < S.length; i++){
            
          if (S[i] != noElement) {
              
            combinedArray[i] = S[i];
            
          }
        }

        for (int i = 0; i < T.length; i++){
            
          if (T[i] != noElement) {
              
            combinedArray[S.length+i] = T[i];
            
          }
        }
        
        // Remove duplicates and sort the combinedArray
        
        removeDuplicates(combinedArray);
        
        sort(combinedArray);
        
        return combinedArray;
    }

    
    // Returns the intersection of S and T in a new set
    
    public static int[] intersection(int[] S, int[] T) {
        
        // Initialize a new set U, which will be a copy of set S
        
        int [] U = new int [SIZE];
        
        U = copy(S);
        
        // If an element of U (aka S) is not found in T, replace it with the 
        // constant integer.MAX_VALUE
        
        for (int i = 0; i < SIZE; i ++) {
            
            if ((member(U[i], T)) == false) {
                
                U[i] = noElement;
            }
        }
        
        return U;
    }
    
    // Returns the set difference S / T, which is all the members of S
    // which are not members of T        
    
    public static int[] setdifference(int[] S, int[] T) {
        
        // Initialize new array U, which is a copy of S
        
        int [] U = new int [SIZE];
        U = copy(S);
        
        // If any element of U (aka S) is also an element of T, replace that 
        // element with the integer.MAX_VALUE
        
        for (int i = 0; i < SIZE; i ++) {
            
            if ((member(U[i], T)) == true) {
                
                U[i] = noElement;
            }
        }
        
        return U;
    }
    
    // Sorting methods, using Insertion Sort
    
    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    public static void sort(int[] A) {  
        for (int i = 1; i < A.length; i++) { 
            for (int j = i; j > 0 && (A[j]  < A[j-1]); j--) { 
                swap(A, j, j-1); 
            } 
        }
    }
    
    // just make an exact copy of a set array
    
    public static int[] copy(int[] S) {
        int[] C = new int[SIZE];
        for(int i = 0; i < SIZE; ++i)
            C[i] = S[i];
        return C;
    }
    
    // remove duplicates from array of ints
    
    public static int[] removeDuplicates(int[] S) {
        
        
        sort(S);
 
        for (int i = 1; i < S.length; i++) {
            
            if (S[i] == S[i-1]) {
                
                S[i - 1] = noElement;
            }
        }
        
        sort(S);
        
        
        return S;
 
    }
    

    public static void main(String [] args) {
        
        System.out.println("\nTests for Set\n");
        
        int[] A = {2,4,6,4,8,6,0,2,4,6,8};
        int[] B = {1,3,5,3,7,9,1};
        int[] C = {2,4,8};
        int[] D = {3,5,4,6};
        
        
        int[] SA = intArray2Set(A); 
        int[] SB = intArray2Set(B);
        int[] SC = intArray2Set(C);
        int[] SD = intArray2Set(D);
        int[] SE = getEmptySet();
        
        
        System.out.println("Test 1: Should be:\n{0,2,4,6,8}"); 
        System.out.println(setToString(SA)); 
        System.out.println(); 
        
        System.out.println("Test 2: Should be:\n{1,3,5,7,9}"); 
        System.out.println(setToString(SB));; 
        System.out.println(); 
        
        System.out.println("Test 3: Should be:\n{2,4,8}"); 
        System.out.println(setToString(SC)); 
        System.out.println(); 
        
        System.out.println("Test 4: Should be:\n{3,4,5,6}"); 
        System.out.println(setToString(SD)); 
        System.out.println(); 
        
        System.out.println("Test 5: Should be:\n{}"); 
        System.out.println(setToString(SE)); 
        System.out.println(); 
        
        System.out.println("Test 6: Should be:\n0"); 
        System.out.println(size(SE)); 
        System.out.println();          
        
        System.out.println("Test 7: Should be:\n5"); 
        System.out.println(size(SA)); 
        System.out.println(); 
        
        System.out.println("Test 8: Should be:\ntrue"); 
        System.out.println(isEmpty(SE)); 
        System.out.println();            
        
        System.out.println("Test 9: Should be:\nfalse"); 
        System.out.println(isEmpty(SB)); 
        System.out.println();          
        
        System.out.println("Test 10: Should be:\nfalse"); 
        System.out.println(member(3, SA)); 
        System.out.println(); 
        
        System.out.println("Test 11: Should be:\ntrue"); 
        System.out.println(member(3, SB)); 
        System.out.println(); 
        
        System.out.println("Test 12: Should be:\nfalse"); 
        System.out.println(member(3, SE)); 
        System.out.println();            
        
        System.out.println("Test 13: Should be:\nfalse"); 
        System.out.println(subset(SB,SA)); 
        System.out.println();          
        
        System.out.println("Test 14: Should be:\ntrue"); 
        System.out.println(subset(SC,SA)); 
        System.out.println(); 
        
        System.out.println("Test 15: Should be:\ntrue"); 
        System.out.println(subset(SE,SA)); 
        System.out.println();
        
        System.out.println("Test 16: Should be:\ntrue"); 
        System.out.println(equal(SC,SC)); 
        System.out.println(); 
        
        System.out.println("Test 17: Should be:\nfalse"); 
        System.out.println(equal(SB,SA)); 
        System.out.println();
        
        System.out.println("Test 18: Should be:\ntrue"); 
        System.out.println(equal(SE,SE)); 
        System.out.println();
        
        System.out.println("Test 19: Should be:\n{0,2,4,6,8}"); 
        System.out.println(setToString(add(4,SA))); 
        System.out.println();       
        
        System.out.println("Test 20: Should be:\n{4}"); 
        System.out.println(setToString(add(4,SE))); 
        System.out.println();
        
        System.out.println("Test 21: Should be:\n{0,2,6,8}"); 
        System.out.println(setToString(remove(4,SA))); 
        System.out.println();       
        
        System.out.println("Test 22: Should be:\n{1,3,5,7,9}"); 
        System.out.println(setToString(remove(4,SB))); 
        System.out.println();
        
        System.out.println("Test 23: Should be:\n{}"); 
        System.out.println(setToString(remove(4,SE))); 
        System.out.println();
        
        System.out.println("Test 24: Should be:\n{0,2,4,6,8}"); 
        System.out.println(setToString(union(SC,SA))); 
        System.out.println();
        
        System.out.println("Test 25: Should be:\n{0,1,2,3,4,5,6,7,8,9}"); 
        System.out.println(setToString(union(SA,SB))); 
        System.out.println();
        
        System.out.println("Test 26: Should be:\n{3,4,5,6}"); 
        System.out.println(setToString(union(SD,SE))); 
        System.out.println();
        
        System.out.println("Test 27: Should be:\n{0,2,4,6,8}"); 
        System.out.println(setToString(union(SC,SA))); 
        System.out.println();
        
        System.out.println("Test 28: Should be:\n{}"); 
        System.out.println(setToString(intersection(SA,SB))); 
        System.out.println();
        
        System.out.println("Test 29: Should be:\n{4}"); 
        System.out.println(setToString(intersection(SC,SD))); 
        System.out.println();  
        
        System.out.println("Test 30: Should be:\n{2,4,8}"); 
        System.out.println(setToString(intersection(SC,SA))); 
        System.out.println(); 
        
        System.out.println("Test 31: Should be:\n{0,6}"); 
        System.out.println(setToString(setdifference(SA,SC))); 
        System.out.println();
        
        System.out.println("Test 32: Should be:\n{2,8}"); 
        System.out.println(setToString(setdifference(SC,SD))); 
        System.out.println();  
        
        System.out.println("Test 33: Should be:\n{1,3,5,7,9}"); 
        System.out.println(setToString(setdifference(SB,SE))); 
        System.out.println(); 
        
        System.out.println("Test 34: Should be:\n{}"); 
        System.out.println(setToString(setdifference(SB,SB))); 
        System.out.println();
    }
    
    
}