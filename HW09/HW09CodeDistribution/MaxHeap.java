/* 
 * Author: Sameena Bajwa (sbajwa@bu.edu)
 * Date: April 16, 2016
 * Class: CS112
 * Purpose: Create a max heap to be used by MiniGoogle.java program
*/

// all code based off of lecture notes, modified to use articles instead of integers

public class MaxHeap {
    
    // constructor class for article nodes that will be inserted into the heap
    public static class ArticleNode {
        
        public Article datum;
        public ArticleNode next;
        public double similarity; 
        
        public ArticleNode (double similarity, Article datum) {
            
            this.similarity = similarity;
            this.datum = datum;
            this.next = null;
        }
    }
    
     private void resize() {
         
      ArticleNode [] B = new ArticleNode [A.length*2];
      
      for(int i = 0; i < A.length; ++i)
          
         B[i] = A[i];
      A = B; 
      
   }
    
   
    private final int SIZE = 10;                       // initial length of array
    private int next = 0;                              // limit of elements in array
    private ArticleNode[] A = new ArticleNode[SIZE];   // implements tree by storing elements in level order
    
    // methods to move up and down tree as array
    
    private int parent(int i) { return (i-1) / 2; }
    private int lchild(int i) { return 2 * i + 1; }
    private int rchild(int i) { return 2 * i + 2; }
    
    private boolean isLeaf(int i) { return (lchild(i) >= next); }
    private boolean isRoot(int i) { return i == 0; }
    
    // standard swap, using indices in array
    private void swap(int i, int j) {
        ArticleNode temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    // basic data structure methods
    
    public boolean isEmpty() {
        return (next == 0);
    }
    
    public int size() {
        return (next);
    }
    
    // insert an article an into array at next available location
    // according to its cosine similarity value
    
    public void insert(double similarity, Article a) {  
        
        if (size() == A.length)
            
            resize();
        
        A[next] = new ArticleNode (similarity, a); 
        
        int i = next;
        int p = parent(i); 
        
        while(!isRoot(i) && A[i].similarity > A[p].similarity) {
            swap(i,p);
            i = p;
            p = parent(i); 
        }
        
        ++next;
    }
    
    
    // Remove top (maximum) element, and replace with last element in level
    // order; fix any violations of heap property on a path downwards
    
    public Article getMax() {
        
        --next;
        swap(0,next);                // swap root with last element
        int i = 0;                   // i is location of new key as it moves down tree
        
        // while there is a maximum child and element out of order, swap with max child
        int mc = maxChild(i); 
        
        while(!isLeaf(i) && A[i].similarity < A[mc].similarity) { 
            swap(i,mc);
            i = mc; 
            mc = maxChild(i);
        }
        
        
        return A[next].datum;
    }
    
    // return index of maximum child of i or -1 if i is a leaf node (no children)
    
    public int maxChild(int i) {
        if(lchild(i) >= next)
            return -1;
        if(rchild(i) >= next)
            return lchild(i);
        else if(A[lchild(i)].similarity > A[rchild(i)].similarity)
            return lchild(i);
        else
            return rchild(i); 
    }
}
    
 