/* File: AverageCaseBSTs.java
 * Author: Sameena Bajwa
 * Date: 3/28/16
 * Purpose: This is a example of how to graph functions using StdDraw.java
 *    This uses the StdDraw "static container" library, which must be 
 *    in the same directory. 
 * What is the theta estimate? O(log(N))
 * What is C? 3.25
 */

import java.util.Random;
import java.awt.Color; 


public class AverageCaseBSTs {
    
     //your methods and fields go here


    public static class Node {
        int key;
        Node left;
        Node right;
        
        public Node(int k) {
            left = null;
            right = null;
            key = k;
        }
        
        public Node(int k, Node left, Node right) {
            key = k;
            this.left = left;
            this.right = right;
            
        }
    }
    
// Height of a binary tree is number of links in longest path, note that
// empty tree has height -1.
    
    public static int height(Node t) {
        if (t == null)
            return -1;
        else 
            return 1 + Math.max( height(t.left), height(t.right) ); 
    }
    
    
    public static boolean member(Node t, int key) {
        if (t == null)
            return false;
        else if (key < t.key) {
            return member(t.left, key);
        } else if (key > t.key) {
            return member(t.right, key);
        } else
            return true;
    }
    
// Recursively insert into tree, same as put(....) from the textbook
    
    public static Node insert(Node t, int key) {
        if (t == null)
            return new Node(key);
        else if (key < t.key) {
            t.left = insert(t.left, key);
            return t;
        } else if (key > t.key) {
            t.right = insert(t.right, key);
            return t;
        } else
            return t;
    }

    
    // used method given in lab
    
    private static int runBSTInsertion(int N) {
        
          int sum = 0; 

          for(int i = 0; i < 1000; ++i) {
              
              Node B = null;

             int[] A = genRandomArray(N); 
             
             for (int j = 0; j < A.length; ++j) {
                 
                 B = insert(B, A[j]);
             }
             
             sum += height(B); 
           } 
           return (int)Math.round(sum / 1000); 
    }
    
    // used method given in lab
    
    private static int[] genRandomArray(int size) {
        Random r = new Random();
        
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++)
            arr[i] = r.nextInt();
        
        return arr;
    }
        
    
    public static void main(String[] args) {
        
        int N = 500;
        
        double pointRadius = 0.005;
        double lineRadius = 0.001;
        
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N / 10.0);
        StdDraw.setPenRadius(pointRadius);
        StdDraw.setPenColor(Color.black);       // Google "Java Color"; the first link gives all the colors
        StdDraw.line(0,0,0,10*N); 
        StdDraw.text(N,20,"" + N);
        StdDraw.line(0,0,N,0);
        StdDraw.text(5,10*N,"" + 10*N);
        
        double prevX, prevY;
        
        StdDraw.setPenColor(Color.blue);
        StdDraw.text(400, 20,"BST Insertion");       
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            
            int y = runBSTInsertion (i);                         
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        StdDraw.setPenColor(Color.green);
        StdDraw.text(50,40,"N");
        prevX = 0; prevY = 0; 
        for (int i = 1; i <= N; i++) {
            int y = i;                           // this will graph i    (linear)
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
        StdDraw.setPenColor(Color.red);
        StdDraw.text(400,15,"C * log(N) + D");
        prevX = 0; prevY = 0; 
        double C = 3.25;
        int D = -1;
        for (int i = 1; i <= N; i++) {
            double y = C * (int) Math.log(i) + D;         // this will graph C * log(N) + D (logarithmic)
            StdDraw.setPenRadius(pointRadius);
            StdDraw.point(i, y);
            StdDraw.setPenRadius(lineRadius);
            StdDraw.line(prevX, prevY, i, y);
            prevX = i; prevY = y;
        }
        
    
    }
    
}