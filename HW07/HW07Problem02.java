 /* File: HW07Problem02.java
 * Author: Sameena Bajwa (sbajwa@bu.edu)
 * Date: 3/18/16
 * Purpose: This is a template for Problem 2 in HW 07 in CS 112, Spring 2016.  
 */

public class HW07Problem02 {
    
    // Definition of Node class is at end of file
    
    // Utility methods to print out the list
    
    private static Node r = null;
    
    public static void printList(Node p) {
        if(p == null) 
            System.out.println(".");
        else {
            System.out.print( p.item + " -> " );
            printList(p.next); 
        } 
    }
    
    public static void printList(String head, Node p) {
        System.out.print(head + " -> ");
        printList(p);
    }
    
    // Here is a sample of a method which will be tested below. 
    
    public static Node reverse(Node p) {
        return reverseHelper(p, null);
    }
    
    public static Node reverseHelper(Node p, Node q) {
        if(p == null)
            return q;
        else {
            Node temp = p.next;
            p.next = q;
            return reverseHelper(temp, p); 
        }
        
    }
    
    public static Node arrayToLinkedList (int[] A) {
        
        Node head = null;
        Node p = head;
        

        if (A.length == 0) {
            return head;
        }
        
        for (int i = (A.length - 1); i >= 0; --i) {
            
            head = new Node(A[i], p);
            p = head;
       
        }
        return head;        
    }
    
    public static Node deleteNth (Node head, int n) {
        
        int counter = 0;
        
        if (head == null) {
            
            return head;
        }
        
        else if (n == 1) {
            
            head = head.next;
            
            return head;
            
        }
        
        else {
            
            
            for (Node p = head.next, q = head; p != null; q = p, p = p.next) {
                
                if (counter == (n - 2)) {
                    
                    q.next = p.next;
                    return head;
                
                }
               
                counter ++;
                
            }
            
            return head;
        }
            
    }
    
    public static boolean equalLists(Node p, Node q) {
        
        for (; p != null && q != null; p = p.next, q = q.next) {
            
            if (p.item != q.item) {
                
                return false;
            }
        }
        
        return true;
    }
    
    public static Node everyOther(Node head) {
        
        int counter = 0;
        
        if (head == null) {
            
            return head;
        }
        
        else {
            
            
            for (Node p = head.next, q = head; p != null; q = p, p = p.next) {
                
                if ((counter % 2) == 0) {
                    q.next = p.next;
                    
                
                }
               
                counter ++;
                
            }
            
            
        }
        return head;
            
    }
        
        
    
    public static boolean equalListsRec(Node p, Node q) {
        
        if (p != null && q != null) {
            
            if (p.item != q.item ) {
                
                return false;
            }
            else {
                return equalListsRec(p.next, q.next);
            }
        }
        
        else if (p == null && q == null) {
            
            return true;
        }
        
        return false;
    }
    
    public static Node everyOtherRec(Node p) {
        
        if (p == null) {
            
            return p;
        }
        
        else if (p.next == null) {
            
            return p;
        }
        
        else {
            
            p.next = everyOtherRec(p.next.next);
            return p;
        }
    }
        
        
    
    public static Node splice(int n, Node p, Node q) {
        
        int counter = 0;
        
        if (p == null) {
            
            return q;
        }
        
        if (q == null) {
            
            return p;
        }
        
        
        return p;   
        
    }
     
    
    public static Node intersection (Node p, Node q) {
        

        if (p == null) {

            return q;
        }
        
        else if (q == null) {

            return r;
        }
        
        else if (p.item != q.item) {

            return intersection (p, q.next);
        }
        else{
            
            r = p;
            return intersection (p.next, q);
        }
    }
            
                

    public static void main(String [] args) {
        Node A = new Node(3, new Node(6, new Node( 9, new Node(12, null ) ) ) );
        printList("A", A); 
        
        // Sample unit test for reverse
        
        System.out.println("\n[0] reverse(A):  should print out:\nA -> 12 -> 9 -> 6 -> 3 -> .");
        A = reverse(A);
        printList("A", A); 
        
        System.out.println("\n[1] arrayToLinkedList(B):  should print out:\nP -> 2 -> 5 -> 4 -> .");
        int[] B = { 2, 5, 4 };
        Node P = arrayToLinkedList(B);
        printList("P", P);
        
        System.out.println("\n[2] arrayToLinkedList(C):  should print out:\nQ -> .");
        int[] C = {};
        Node Q = arrayToLinkedList(C);
        printList("Q", Q); 
        
        System.out.println("\nn[3] deleteNth(D): should print out:\nD -> 2 -> 5 -> 6 -> 2 -> 9 -> .");
        Node D = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );
        
        printList("D", deleteNth(D, 3));
        
        System.out.println("\n[4] everyOther(DE): should print out:\nDE -> 2 -> 4 -> 2 -> .");
        Node DE = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );
        printList("DE", everyOther(DE));
        
        System.out.println("\nn[5] equalLists(E, F): should print out:\ntrue");
        Node E = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );
        Node F = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );
        System.out.println(equalLists(E, F));
        
        System.out.println("\nn[6] equalLists(G, H): should print out:\nfalse");
        Node G = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );
        Node H = new Node(3, new Node (4, null));
        System.out.println(equalLists(G, H));
        
        System.out.println("\n[10] equalListsRec(AA, BB): should print out:\ntrue");
        Node AA = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );
        Node BB = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );
        System.out.println(equalListsRec(AA, BB));
        
        System.out.println("\n[11] equalListsRec(CC,DD): should print out:\nfalse");
        Node CC = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );
        Node DD = new Node(3, new Node (4, null));
        System.out.println(equalListsRec(CC, DD)); 
        
        System.out.println("\n[12] everyOtherRec(FF): should print out:\nFF -> 2 -> 4 -> 2 -> . ");
        Node FF = new Node(2, new Node (5, new Node (4, new Node (6, new Node (2, new Node (9, null) ) ) ) ) );  
        printList("FF", everyOtherRec(FF));
        
        System.out.println("\n[13] splice(GG, HH): should print out:\nGG -> 1 -> 2 -> 3 -> .");
        Node HH = null;
        Node GG = new Node (1, new Node (2, new Node (3, null)));
        printList("GG", splice(3, GG, HH));
        
        System.out.println("\n[13] splice(II, JJ): should print out:\nJJ -> 1 -> 2 -> 3 -> .");
        Node II = null;
        Node JJ = new Node (1, new Node (2, new Node (3, null)));
        printList("JJ", splice(3, II, JJ));
        
        System.out.println("\n[14] intersection (AAB, BAA): should print out:\nR -> 1 -> 2 -> 4 -> 5 -> .");
        Node AAB = null;
        Node BAA = new Node (1, new Node (2, new Node (4, new Node (5, null))));
        Node R = intersection(AAB, BAA);
        printList("R", R);
        
        System.out.println("\n[15] intersection (ABA, BAB): should print out:\nS -> 2 -> 3 -> 4 -> 5 -> 6 -> .");
        Node ABA = new Node (2, new Node (3, new Node (4, new Node (5, new Node (6, null)))));
        Node BAB = null;
        Node S = intersection(ABA, BAB);
        printList("S", S);
        
        System.out.println("\n[16] intersection (AB, BA): should print out:\nT -> 2 -> 4 -> 5 -> .");
        Node AB = new Node (2, new Node (3, new Node (4, new Node (5, new Node (6, null)))));
        Node BA = new Node (1, new Node (2, new Node (4, new Node (5, null))));
        Node T = intersection(AB, BA);
        printList("T", T);
    }
    
    
}

// You can put another class in the same file, as long as you do not make it public;
// it will be visible to any other file in the same directory

class Node {
    int item;
    Node next;
    public Node(int it, Node n) {
        item = it; next = n;
    }
}