/* File: IntDeque.java
 * Date: 2/19/2016
 * Author:  Sameena Bajwa
 * Class: CS 112, Spring 2016
 * Purpose: Create an IntDeque abstract data type using the circular-buffer
 * technique
 */

public class IntDeque {
    
    private final int SIZE = 10;
    
    private int [] A = new int [SIZE];
    
    private int length = SIZE;
    
    private int front = 0;
    
    private int next = 0;
    
    private int size = 0;
    
    // Insert a new int at the front of the queue
    
    public void enqueueFront(int k) {
        
        if (next >= SIZE) {
            resize();
        }
        
        front = previousSlot(front);
        ++ size;
        
        // push new into one step to the right
        A[front] = k;
        
    }
    
    // Remove int at the front of the queue and return it
    
    public int dequeueFront() throws QueueUnderflowException {
        
        // if the queue is empty, throw to the underflow exception
        if (isEmpty() == true) {
            
            throw new QueueUnderflowException();
        }
        
        ++ front;
        -- size;
        
        return A[front - 1];
        
    }
    
    //Insert a new int at the rear of the queue
    
    public void enqueueRear(int k) {
        
        if (next >= SIZE) 
            resize();
        
        ++size;
        // push int k to the end of the queue
        A[next ++] = k;
 
    }
    
    public void resize() {
        
        final int RESIZE = SIZE * 2;
        int [] B = new int [RESIZE];
        
        //front variable that will be incremented throughout the loop
        
        int incrementFront = front;
        
        for (int i = front; i < RESIZE; i ++) {
            if (i < SIZE) {
                B[i] = A[incrementFront];
                incrementFront = nextSlot(incrementFront);
            }
            else {
                B[i] = 0;
            }
            
        }
        
        next = next % 10;
        
        
        A = B;   
        
    }
    
    // Call next slot of the array
    
    public int nextSlot(int k) {
        
        return ((k + 1) % 10);
    }
    
    // Call previous slot of the array 
    
    public int previousSlot(int k) {
        
        if (k == 0) {
            return ( 9 % 10);
        }
        return ((k - 1) % 10);
    }
        
    // Remove the int at the rear of the queue and return it
    
    public int dequeueRear() throws QueueUnderflowException {
        
        // if the queue is empty, throw to the underflow exception
        if (isEmpty() == true) {
            
            throw new QueueUnderflowException();
        }
        
        -- size;
        next = previousSlot(next);
        
        return A[next];
    }
    
    // Return number of ints being stored in the queue
    
    public int size() {
        
        return size;
    }
    
    // Return true if the queue is empty, false otherwise
    
    public boolean isEmpty() {
        
        if (size == 0) {
            
            return true;
        }
        
        return false;
    }
    
    // Return string representation of the deque
    
    public String toString() {
        String s = "[";
        
        // modify front as necessary when it increments to over 10
        
        if (front > 10) {
            front = front % 10;
        }
        
        
        // if size increment grows to be over 10 during a wrap around, adjust
        // size and front accordingly
        
        if (size > 10) {
            
            front = size % 10;
            
            size = 10;
        }
        
        
        for(int i = SIZE  - 1; i >= 0; --i) {
            
            // if the element is the first element, do not add a comma afterwards
            
            if (i == 0) {
                
                s += A[i];
            }
            else {
                s += A[i] + ", ";
            }
        }
        
        
        s += "]  length: " + length + "  size: " + size() + "  front: " + front + "  next: " + next;

        return s; 
    }
        
    
    // Unit Test (you must complete this with additonal tests as indicated)
    
    public static void main(String[] args) {
        
        try {
            IntDeque D = new IntDeque(); 
            
            System.out.println("\n[1] First test toString on empty dequeue... Should print out:"); 
            System.out.println("[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]  length: 10  size: 0  front: 0  next: 0"); 
            System.out.println(D); 
            
            System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
            System.out.println(D.size() + "  " + D.isEmpty()); 
            
            System.out.println("\n[3] Test enqueueRear.... Should print out:"
                                   + "\n[0, 0, 0, 0, 0, 0, 4, 3, 2, 1]  length: 10  size: 4  front: 0  next: 4");
            D.enqueueRear(1); 
            D.enqueueRear(2);
            D.enqueueRear(3); 
            D.enqueueRear(4);
            
            System.out.println(D); 
            
            System.out.println("\n[4] Test size and isEmpty again... Should print out:\n4  false"); 
            System.out.println(D.size() + "  " + D.isEmpty()); 
            
            System.out.println("\n[5] Test dequeueFront.... Should print out:"
                                   + "\nn = 1  m = 3");
            int n = D.dequeueFront(); 
            D.dequeueFront();  
            int m = D.dequeueFront(); 
            System.out.println("n = " + n + "  m = " + m); 
            
            System.out.println("\n[6] And should print out:"
                                   + "\n[0, 0, 0, 0, 0, 0, 4, 3, 2, 1]  length: 10  size: 1  front: 3  next: 4");
            System.out.println(D); 
            
            
            System.out.println("\n[7] Test wrap-around of enqueueRear .... Should print out:"
                                   + "\n[10, 9, 8, 7, 6, 5, 4, 13, 12, 11]  length: 10  size: 10  front: 3  next: 3");
            
            for(int i = 5; i < 14; ++i)
                D.enqueueRear(i);
            
            System.out.println(D); 
            
            System.out.println("\n[8] Test wrap-around of dequeueFront .... Should print out:"
                                   + "\n[10, 9, 8, 7, 6, 5, 4, 13, 12, 11]  length: 10  size: 0  front: 3  next: 3  m = 13");
            
            for(int i = 0; i < 9; ++i) {
                D.dequeueFront();
            }
            
            System.out.println(D + "  m = " + D.dequeueFront() ); 
        
        }
        
        
        
      
        catch (QueueUnderflowException e) {
            
            // when the exception is caught, print that the stack underflew
            
            System.out.println("Stack Underflew!");
    }
}

}