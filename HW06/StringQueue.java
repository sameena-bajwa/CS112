/* Filename: StringQueue.java
 * Date: 2/28/16
 * Author: Sameena Bajwa
 * Class: CS112, Spring 2016
 */

public class StringQueue {
    
    private Node head = null; 
    
    private int size = 0;  
            
    public class Node { 
        public String item; 
        public Node next; 
        
        // constructors 
        public Node() { 
            item = ""; 
            next = null; 
        } 
        
        public Node(String n) { 
            item = n; 
            next = null; 
        } 
        
        public Node(String n, Node p) { 
            item = n; 
            next = p;         
        } 
    }
    
    public String dequeue() throws QueueUnderflowException {
        
        if (isEmpty() == true) {
            
            throw new QueueUnderflowException();
        }
        
        String temp = head.item;
        head = head.next;
        --size;
        return temp;
    }
        
    
    // pushes item n onto the linked list
    
    public void enqueue (String n) {
        
        Node p = new Node (n, head);
        head = p;
        size ++;
    }

    
    // returns size of linked list
    
    public int size() {
        
        return size;
    }
    
    // returns true if linked list is empty, false otherwise

    public boolean isEmpty() {
        
        if (size == 0) {
            
            return true;
        }
        
        return false;
    }
    
    
    
    // Recursive implementation of toString
    
    // wrapper method: just calls recursive helper
    public String toString() {
        
        return "" +  toStringAux(head);
    }
    
    // recursive helper method
    private String toStringAux(Node p) {
        
        if(p == null) {
            
            return "";
            
        }
        else {
            
            return toStringAux(p.next) + " " + p.item ;
        }
    }
    
  
    
    public static void main(String[] args) {
        
        StringQueue S = new StringQueue(); 
        
        System.out.println("\n[1] First test toString on empty StringQueue... Should print out:"); 
        System.out.println(""); 
        System.out.println(S); 
        
      
            
        System.out.println("\n[2] Test size and isEmpty... Should print out:\n0  true"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
       
        System.out.println("\n[3] Enqueue 10 strings... Should print out:\n !"  
                              + " President for run to looney be to have You'd"); 
        S.enqueue("!");
        S.enqueue("President");
        S.enqueue("for");
        S.enqueue("run");
        S.enqueue("to");
        S.enqueue("looney");
        S.enqueue("be");
        S.enqueue("to");
        S.enqueue("have");
        S.enqueue("You'd");
        System.out.println(S);
        
        
        System.out.println("\n[4] Test size and isEmpty... Should print out:\n10  false"); 
        System.out.println(S.size() + "  " + S.isEmpty()); 
        
        
        
        System.out.println("\n[5] Dequeue and print... Should print out:\n"
                               + "You'd have to be looney to run for President !");
        while(!S.isEmpty()) {
            try {
                String s = S.dequeue(); 
                System.out.print(s+" ");
            }
            catch(QueueUnderflowException e) {
                System.err.println("Queue Underflow!");
            }
        }
        System.out.println(); 
        
        
        System.out.println("\n[6] Testing queue underflow with dequeue... Should print out:");
        System.err.println("Queue Underflow!");
        try {
            
            System.out.print(S.dequeue());
        }
        catch(QueueUnderflowException e) {
            System.err.println("Queue Underflow!");
        }  
        
        
        System.out.println("\n[7] Just for fun... Should print out a long String!");
        try {
            S.enqueue("looney! ");
            S.enqueue("a ");
            S.enqueue("is ");
            S.enqueue("Trump ");
            S.enqueue("So ");
            
            String s = "";
            while(!S.isEmpty())
                s += S.dequeue();
            
            for(int i = 0; i < 5; ++i)
                S.enqueue(s);
            S.enqueue("\n");
            
            String t = "";
            while(!S.isEmpty())
                t += S.dequeue();
            
            for(int i = 0; i < 5; ++i)
                S.enqueue(t);
            
            while(!S.isEmpty())
                System.out.println(S.dequeue());
            
        }
        catch(QueueUnderflowException e) {
            System.err.println("Queue Underflow!");
        }
    
     
    }
}

class QueueUnderflowException extends Exception {}
