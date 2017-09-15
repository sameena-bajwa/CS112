/* File: AverageCaseSorting.java
 * Author: Sameena Bajwa
 * Date: 2/15/16
 * Purpose: This program will be used to draw graphs for the average-case time 
 *    complexity of four different algorithms. 
 *    This uses the StdDraw "static container" library, which must be 
 *    in the same directory. 
 */

import java.util.Random;
import java.awt.Color; 

public class AverageCaseSorting {
    
    // use this to generate random lists through the Random library
    
    private static Random R = new Random(); 
    
    
    // your methods and fields go here
    
    // determine how many comparisons it takes to sort an average, random list
    // using selection sort which would then be graphed
    
    private static int runSelectionSort(int N) {
        
        int r = R.nextInt();
        int [] A = new int [N];
        
        for (int i = 0; i < A.length; i++ ) {
            
            A[i] = r;
        }
        Sorting.counter = 0;
        Sorting.selectionSort(A);
        return Sorting.counter;
        
    }
    
    // determine how many comparisons it takes to sort an average, random list
    // using merge sort which would then be graphed
    
    private static int runMergeSort(int N) {
        
        int r = R.nextInt();
        int [] A = new int [N];
        
        for (int i = 0; i < A.length; i ++) {
            
            A[i] = r;
        }
        
        Sorting.counter = 0;
        Sorting.mergeSort(A);
        return Sorting.counter;
    }
    
    // determine how many comparisons it takes to sort an average, random list
    // using quick sort which would then be graphed
    
    private static int runQuickSort(int N) {
        
        int r = R.nextInt();
        int [] A = new int [N];
        
        for (int i = 0; i < A.length; i ++) {
            
            A[i] = r;
        }
        
        Sorting.counter = 0;
        Sorting.quickSort(A);
        return Sorting.counter;
    }
    
    // determine how many comparisons it takes to sort an average, random list
    // using insertion sort which would then be graphed
    
    private static int runInsertionSort(int N) {
        
        int r = R.nextInt();
        int [] A = new int [N];
        
        for (int i = 0; i < A.length; i ++) {
            
            A[i] = r;
        }
        
        Sorting.counter = 0;
        Sorting.insertionSort(A);
        return Sorting.counter;
    }
        
     
    public static void main(String[] args) {
        
        int N = 100;
        
        double pointRadius = 0.005;
        double lineRadius = 0.001;
        
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, 10*N);
        StdDraw.setPenRadius(pointRadius);
        StdDraw.setPenColor(Color.black);       // Google "Java Color"; the first link gives all the colors
        StdDraw.line(0,0,0,10*N); 
        StdDraw.text(N,20,"" + N);
        StdDraw.line(0,0,N,0);
        StdDraw.text(5,10*N,"" + 10*N);
        
        double prevX, prevY;
        
        StdDraw.setPenColor(Color.black );
        StdDraw.text(15,500,"N^2");       // draw label String at point (15,500)
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            // here is where you put the function you are graphing
            // for example this graphs i^2 (squared)
            int y = i*i;                         
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        StdDraw.setPenColor(Color.gray);
        StdDraw.text(80,30,"N");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = i;                           // this will graph i    (linear)
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        
        
        StdDraw.setPenColor(Color.orange);
        StdDraw.text(45, 500,"Selection Sort");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = runSelectionSort( i );     // this will graph selection sort
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        StdDraw.setPenColor(Color.green);
        StdDraw.text(80,200,"Merge Sort");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = runMergeSort( i );     // this will graph merge sort
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        StdDraw.setPenColor(Color.red);
        StdDraw.text(85,350,"Quick Sort");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = runQuickSort( i );     // this will graph quick sort
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        StdDraw.setPenColor(Color.blue);
        StdDraw.text(80, 60,"Insertion Sort");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = runInsertionSort( i );     // this will graph insertion sort
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        
    }
    
}