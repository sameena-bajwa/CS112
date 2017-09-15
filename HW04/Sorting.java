/* File: Sorting.java
 * Author: Sameena  Bajwa
 * Date: 2/15/16
 * Purpose: Create a class "Sorting," which holds a library of sorting methods
 */

public class Sorting {
    
    // variable to count number of comparisons per sorting method
    public  static int counter = 0;
    
    public static void main (String[] args) {
        
        // new lists to be used for each sorting method
        
        int[] A = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] B = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] C = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] D = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        
        // test selection sort
        
        System.out.println("A before: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]");
        counter= 0;
        selectionSort(A);
        System.out.println("A after: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
        System.out.println("\nselectionSort(A) took " + counter + " comparisons.\n");
        
        // test insertion sort
        
        System.out.println("B before: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]");
        counter= 0;
        insertionSort(B);
        System.out.println("B after: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
        System.out.println("\ninsertionSort(B) took " + counter + " comparisons.\n");
       
        // test merge sort
        
        System.out.println("C before: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]");
        counter= 0;
        mergeSort(C);
        System.out.println("C after: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
        System.out.println("\nmergeSort(C) took " + counter + " comparisons.\n");
        
        // test quick sort
        
        System.out.println("D before: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]");
        counter= 0;
        quickSort(D);
        System.out.println("D after: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]");
        System.out.println("\nquickSort(D) took " + counter + " comparisons.");
        
    }
        
    
    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // insertion sort from Algorithms, Sedgewick and Wayne, p.251
    
    public static void insertionSort(int[] A) {
        int N = A.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(A[j], A[j-1]); j--) {
                swap(A, j, j-1);
            }
        }
    }

    // insertion sort from Algorithms, Sedgewick and Wayne, p.249
    
    public static void selectionSort( int[] A) {
        int N = A.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(A[j], A[min])) min = j;
            }
            swap(A, i, min);
        
        }
    
    }

    // Mergesort from Sedgewick
    
    public static void mergeSort(int[] A) {
        int[] aux = new int[A.length];
        msHelper(A, aux, 0, A.length-1);
    }

    // stably merge A[lo .. mid] with A[mid+1 ..hi] using aux[lo .. hi]
    
    public static void merge(int[] A, int[] aux, int lo, int mid, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = A[k]; 
        }
    
        // merge back to A[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              A[k] = aux[j++];   // this copying is unnecessary
            else if (j > hi)               A[k] = aux[i++];
            else if (less(aux[j], aux[i])) A[k] = aux[j++];
            else                           A[k] = aux[i++];
        }
    }

    // mergesort A[lo..hi] using auxiliary array aux[lo..hi]
    public static void msHelper(int[] A, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        msHelper(A, aux, lo, mid);
        msHelper(A, aux, mid + 1, hi);
        merge(A, aux, lo, mid, hi);
    }


    // Quicksort from Sedgewick
    public static void quickSort(int[] A) {
        qsHelper(A, 0, A.length - 1);
    }

    // quicksort the subarray from A[lo] to A[hi]
    public static void qsHelper(int[] A, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(A, lo, hi);
        qsHelper(A, lo, j-1);
        qsHelper(A, j+1, hi);
    }

    // partition the subarray A[lo..hi] so that A[lo..j-1] <= A[j] <= A[j+1..hi]
    // and return the index j.
    public static int partition(int[] A, int lo, int hi) {
        int i = lo+1;
        int j = hi;
        int v = A[lo];
        while (i <= j) {
            while( i < A.length && less(A[i], v) )
                ++i;
            while( less(v, A[j]) )
                --j;
            if(i > j)
                break;
            else {
                swap(A,i,j);
                ++i;
                --j;
            }
    }
    
        // put partitioning item v at A[j]
        swap(A, lo, j);
    
        // now, A[lo .. j-1] <= A[j] <= A[j+1 .. hi]
        return j;
    }

    public static boolean less(int v, int w) {
        ++counter;
        return v < w;
    }
}