/* File: Dictionary.java
 * Author: Sameena Bajwa
 * Date: 2/26/2016
 * Purpose: This is a template for HW06, Problem B.2 for CS 112. 
 */

import java.util.Arrays;

public class Dictionary { 
    
    private int LENGTH = 10;
    
    public int size = 0;
    
    // array of length LENGTH to store the pairs
    
    private Pair[] A = new Pair[LENGTH];
    private int next = 0;
    
    // basic definition of the Pair class; this is an "inner class" inside class Dictionary
    
    private class Pair {
        String key;
        String[] value;
        
        public Pair() { }              // default constructor, key and value will be nulls
        
        public Pair(String k, String[] v) {
            key = k;
            value = v;
        }
        
        public String toString() { 
                
            String entry = key + ":" + Arrays.toString(value);
            return entry;
        }
        
    }
    
    // insert key k and array val into array A in ascending order
    
    public void insertStudent(String k, String[] val) {
        
        int location = 0;
        
        if (memberArray(k, A) == false) {
            
            for(int i = 0; i < A.length; i ++) {
                
                compare = A[k].comparesTo(A[i].ket);
                
                if (compare > 0) {
                    
                    for (int j = 0; j < (A.length - next); j ++) {
                        
                        A[j] = A[j + 1];
                        if ((j + 1) > A.length) {
                            resize();
                        }
                    }
                  
                    A[k] = new Pair(k, val);
                }
                
        }
    }
    }
        
    
    // returns true if string k is in the array C, false otherwise
    
    private boolean memberArray(String k, String[] C) {
        
        for (int i = 0; i < C.length(); i  ++) {
            
            if (C[i].key == k) {
                
                return true;
            }
        }
        
        return false
            
    }
    
    // this is used in the unit test
    
    private void printDictionary() {
        for(int i = 0; i < next; ++i)
            System.out.println(i + ": " + A[i]);
    }
    
    public static void main(String[] args) {
        
        Dictionary D = new Dictionary(); 
        
        // Insert three (key,value) pairs and test basic methods
        
        String[] A = { "CS111", "CS131", "WR99", "EC101" };
        String[] B = { "CS111", "MA123", "WR100", "SO100" };
        String[] C = { "CS111", "MA294", "WR150", "CL212" };
        String[] E = { "CS350", "CS235", "EC101", "MU101" };
        String[] F = { "CS111", "MA124", "BI108", "SO105" };
        String[] G = { "CS591", "MA442", "EN212", "EC101" };
 
        // uncomment one test at a time as you develop the corresponding methods above
        
        /*
        D.insertStudent("Christie,Chris",A); 
        D.insertStudent("Carson,Ben", B);
        D.insertStudent("Trump,Donald", C);
        D.insertStudent("Kasich,John",E); 
        D.insertStudent("Cruz,Ted", F);
        D.insertStudent("Bush,Jeb", G);
        
        System.out.println("\n[1] Should print out:"); 
        System.out.println("0: Bush,Jeb: [CS591,MA442,EN212,EC101]");
        System.out.println("1: Carson,Ben: [CS111,MA123,WR100,SO100]");
        System.out.println("2: Christie,Chris: [CS111,CS131,WR99,EC101]");
        System.out.println("3: Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("4: Kasich,John: [CS350,CS235,EC101,MU101]");
        System.out.println("5: Trump,Donald: [CS111,MA294,WR150,CL212]\n");
        
        D.printDictionary();  
        
        /*
        System.out.println("\n[2] Should print out:\n6"); 
        System.out.println(D.size()); 
        
        System.out.println("\n[3] Should print out:\nfalse"); 
        System.out.println(D.isEmpty()); 
        
        System.out.println("\n[4] Should print out:\ntrue"); 
        System.out.println(D.member("Cruz,Ted")); 
        
        System.out.println("\n[5] Should print out:\nfalse"); 
        System.out.println(D.member("Jindal,Bobby")); 
        
        D.deleteStudent("Bush,Jeb");
        D.deleteStudent("Christie,Chris");
        System.out.println("\n[6] Should print out:\nfalse  false"); 
        System.out.println(D.member("Bush,Jeb") + "  " + D.member("Christie,Chris")); 
        
        System.out.println("\n[7] Should print out:\n[CS111, MA123, WR100, SO100]"); 
        String name = "Carson,Ben";
        try {
            System.out.println(Arrays.toString(D.lookupClasses(name))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: " + name);
        }
        
        name = "Jindal,Bobby";
        System.out.println("\n[8] Should print out:");
        System.err.println("Key not found: " + name); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses(name))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: " + name);
        }
        
        System.out.println("\n[9] Should print out:\n[CS111, WR100, SO100]");  
        D.dropClass("Carson,Ben", "MA123");
        D.dropClass("Carson,Ben", "EC102"); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses("Carson,Ben"))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: Carson,Ben");
        } 
        
        System.out.println("\n[10] Should print out:\n[CS111, MA294, WR150, CL212, CS591]");  
        D.addClass("Trump,Donald", "CS591");
        D.addClass("Trump,Donald", "WR150"); 
        try {
            System.out.println(Arrays.toString(D.lookupClasses("Trump,Donald"))); 
        }
        catch(KeyNotFoundException e) {
            System.err.println("Key not found: Carson,Ben");
        } 
        
        System.out.println("\n[11] Should print out:\nfalse  true"); 
        D.dropClass("Walker,Scott","PH150");
        System.out.print(D.member("Walker,Scott") + "  " );
        D.addClass("Walker,Scott","PH110"); 
        System.out.println(D.member("Walker,Scott"));   
        
        System.out.println("\n[12] Should print out:\ntrue"); 
        System.out.println(D.enrolled("Trump,Donald", "CS591"));  
        
        System.out.println("\n[13] Should print out:\nfalse"); 
        System.out.println(D.enrolled("Trump,Donald", "CS101"));        
        
        // testing iterators       
        
        System.out.println("\n[14] Should print out:");
        System.out.println("0: Carson,Ben: [CS111,WR100,SO100]");
        System.out.println("1: Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("2: Kasich,John: [CS350,CS235,EC101,MU101]");
        System.out.println("3: Trump,Donald: [CS210,MA294,WR150,CL212,CS591]");
        System.out.println("4: Walker,Scott: [PH110]\n");
        D.printDictionary(); 
        
        System.out.println("\n[15] Should print out same but without index numbers:");
        D.initPairIterator(); 
        
        while(D.hasNextPair()) {
            System.out.println(D.nextPair());
        }
        System.out.println("\n[16] Should print out:\nCarson,Ben:  [CS111,WR100,SO100]");
        D.initPairIterator(); 
        System.out.println(D.nextPair());
        
        System.out.println("\n[17] Should print out:");  
        D.initStudentIterator("CS111");
        System.out.println("Carson,Ben: [CS111,WR100,SO100]");
        System.out.println("Cruz,Ted: [CS111,MA124,BI108,SO105]");
        System.out.println("Trump,Donald: [CS111,MA294,WR150,CL212,CS591]\n");
        
        while(D.hasNextStudent()) {
            System.out.println(D.nextStudent());
        }
        
        System.out.println("\n[18] Should print out:\nTrump,Donald:[CS111,MA294,WR150,CL212,CS591]");
        D.initStudentIterator("CL212"); 
        
        while(D.hasNextStudent()) {
            System.out.println(D.nextStudent());
        } 
        
        System.out.println("\n[19] Testing resizing; should print out a dictionary with 14 pairs.\n"); 
        D.insertStudent("Clinton, Hillary",A); 
        D.insertStudent("Sanders,Bernie", B);
        D.insertStudent("Lincoln,Abraham", C);
        D.insertStudent("Kennedy,John",E); 
        D.insertStudent("Bush,George", F);
        D.insertStudent("Reagan,Ronald", G);
        D.insertStudent("Nixon,Dick",A); 
        D.insertStudent("Carter,Jimmy", B);
        D.insertStudent("Johnson,Lyndon", C);
 
        D.printDictionary(); 
 */       
    }
    
}



class KeyNotFoundException extends Exception {}


