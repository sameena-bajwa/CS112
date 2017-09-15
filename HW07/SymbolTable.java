/* File: SymbolTable.java
 * Author: Sameena Bajwa (sbajwa@bu.edu)
 * Date: 3/18/16
 * Purpose: Recursive methods for working through a symbol table
 */

public class SymbolTable<Value> {
  
  
  // basic definition of the Node class; this is an "inner class" inside class SymbolTable
  
  private class Node {                 // Node class for LLs
    String variable;
    Value value;
    Node next;
    
    public Node() {}
    
    public Node(String k, Value v) {
      variable = k; value = v; next = null;
    }
    
    public Node(String k, Value v, Node p) {
      variable = k; value = v; next = p;
    }
  }
  
  // pointer to list of bindings for symbol table
  
  private Node head = null; 
  
  private int size;
  
  private String min = "";
  
  private String max = "";
  
  public String toString () {
      
      return toString(head);
      
  }
  
  private String toString(Node p) {
      
      if (p == null) {
          
          return "";
      }
      
      if (p.next == null) {
          
          return "(" + p.variable.toString() + "," + p.value.toString() + ")";
      }
      return "(" + p.variable.toString() + "," + p.value.toString() + ") : " + toString(p.next);
  }
  
  public void put (String var, Value val) {
      
      head = putHelper (var, val, head); 
  }
  
  private Node putHelper(String var, Value val, Node p ) {

      
      if(p == null) {
          
        p = new Node (var, val);
        return  p;
        
      }
    
    else if (var == p.variable) {
        
        p.value = val;
        return p;
    }

    else if ( (var.compareTo(p.variable)) < 0) {
        
        Node q = new Node (var, val);
        q.next = p;
        return q;
    }
    
    else {

        p.next = putHelper (var, val, p.next);
        return p;
    }
}
  
  public int size() {

      size = sizeHelper (head);
      return size;
  }
  
  private int sizeHelper (Node p) {
      
      if (p == null) 
          return size;
      else {
          size = 1 + sizeHelper(p.next);
          return size;
      }
   
  }
  
  public boolean isEmpty() {
      
      if (size == 0) {
          
          return true;
      }
      
      return false; 
  }
  
  public Value get(String var) throws KeyNotFoundException {
      
      if ( getHelper (var, head) == null) 
          throw new KeyNotFoundException();
      
      return getHelper(var, head);
      
  }
  
  private Value getHelper(String var, Node p) {
      
      if (p == null) 
          return null;
      
      else if (var == p.variable) 
          
          return p.value;
      else 
          return getHelper(var, p.next);
  }
  
  public boolean contains (String var) {
      

      return containsHelper (var, head);
  }
  
  private boolean containsHelper (String key, Node p) {
      
      if (p == null)
          
          return false;
      
      else if (key == p.key)
         
          return true;
      
      else 
          
          return containsHelper (key, p.next);
  }
  
  public void delete (String var) {
      
      head = deleteHelper (var, head);
  }
  
  private Node deleteHelper (String var, Node p) {
      
      if (p == null)
          
          return p;
      
      else if (p.variable == var) {
          size --;
          return p.next;
      }
      
      else {
          
          p.next = deleteHelper (var, p.next);
          return p;
      }
  }
  
  public String min() throws KeyNotFoundException {
      
      if (minHelper(head) == null) {
          
          throw new KeyNotFoundException();
      }
  
      return minHelper(head);
  }
  
  private String minHelper(Node p) {
      
      if (p == null)
          
          return null;
      
      else 
          return p.variable;
  }
  
  public String max() throws KeyNotFoundException {
      
      if (maxHelper ( head) == null) {
          
          throw new KeyNotFoundException();
      }
      
      return maxHelper ( head);
  }
  
  private String maxHelper (Node p) {
      
      
      if (p == null) 
          
          return null;
      
      else if (p.next != null)
          
          return maxHelper (p.next);
      
      else 
          
          return p.variable;
  }
  
  public String floor (String var) throws KeyNotFoundException {
      
      if (floorHelper (var, head) == null) {
          
          throw new KeyNotFoundException();
      }
      
      return floorHelper (var, head);
  }
  
  private String floorHelper (String var, Node p) {

      
      // if the table is empty or the variable is smaller than the smallest entry, 
      //throw to exception
      
      if (p == null || rank (var) < rank (minHelper(p))) {
          
          return null;
      }
      
      // if variable is in the table, return the variable 
      
      else if (var == p.variable) {
          
          return var;
      }    
      
      else if (rank (p.variable) < rank (var)) {
          
          min = p.variable;
          return floorHelper (var, p.next);
      }
      
      return min;
     
      
  }
  
  public String ceiling (String var) throws KeyNotFoundException {
      
      if (ceilingHelper (var, head) == null)
          
          throw new KeyNotFoundException();
      
      return ceilingHelper (var, head);
  }
  
  private String ceilingHelper (String var, Node p) {
      
      // if the table is empty or if the variable is larger than the largest entry,
      // throw to exception
      
      if (p == null || rank (var) > rank (maxHelper(p))) {
          
          return null;
      }
      
      else if (var == p.variable)
          
          return var;
      
      else if (rank (p.variable) < rank (var)) {
          
          return ceilingHelper (var, p.next);
          
      }
      
      else {
         
          max = p.variable;
          return max;
      }
      
      
  }
  
  
  
  public int rank (String var) {
      
      return rankHelper (var, head);
  }
  
  private int rankHelper (String var, Node p) {
      
      int rank = 0;
      int rankComparison = var.compareTo(p.variable);
      
      if (p.next == null || rankComparison < 0) {
          
          return rank;
      }
      
      else if (rankComparison > 0) {
          
          rank = 1 + rankHelper (var, p.next);
          return rank;
      }
      
      return rank;
  }
  
  public String select (int r) throws KeyNotFoundException {
      
      if (selectHelper (r, head) == null) {
          
          throw new KeyNotFoundException();
      }
      
      return selectHelper (r, head);
  }
  
  private String selectHelper (int r, Node p) {
      
      if (p == null || r < 0) {
          
          return null;
      }
      
      else if ( rank(p.variable) != r) {
          
          return selectHelper (r, p.next);
      }
      
      else 
          return p.variable;
  }
  
  public int size (String lo, String hi) {
      
      return sizeCompHelper (lo, hi, head);

  }
  
  private int sizeCompHelper (String lo, String hi, Node p) {
      
      int sizeComp = 0;
      
      int rankLo = rank(lo);
      
      int rankHi = rank(hi);
      
      if (p == null) 
          
          return sizeComp;
      
      else if ((rank (p.variable) >= rankLo) && (rank (p.variable) <= rankHi)) {

          sizeComp = 1 + sizeCompHelper (lo, hi, p.next);
          return sizeComp;
      }
      
      else {
          
          return sizeCompHelper (lo, hi, p.next);  
      }    
  }
  
  public void deleteMin () {
      
      head = deleteMinHelper (head);
  }
  
  private Node deleteMinHelper (Node p) {
      
      if (p == null) 
          
          return null;
      
      p = p.next;
      size -= 2;
      return p;
  }
  
  public void deleteMax () {
     
      head = deleteMaxHelper (head);
  }
  
  private Node deleteMaxHelper(Node p) {
      
      if (p == null) 
          
          return null;
      
      else if (p.next.next == null) {
          
          size -= 2;
          return p;
      }
      
      return deleteMaxHelper (p.next);
          
          
          
          
  }
          
      

  public static void main(String[] args) {
    
    
    SymbolTable<Integer> S = new SymbolTable<Integer>(); 
    
    
//   Use step-wise refinement to develop the methods one at a time as you get to them
//  *  in the following performance tests, and then move the "/*" down, to uncover more
//  *  and more tests.
//  *  Note: You will need to write the toString() method FIRST in order to run these
//  *        performance tests. 

  
    
    S.put("a",3); 
    S.put("c",1);
    S.put("b",1);
    
    
    System.out.println("\n[1] Should print out:\n(a,3) : (b,1) : (c,1) "); 
    System.out.println(S); 
    
    System.out.println("\n[2] Should print out:\n3"); 
    System.out.println(S.size()); 
    
    System.out.println("\n[3] Should print out:\nfalse"); 
    System.out.println(S.isEmpty()); 
    
    System.out.println("\n[4] Should print out:\n1");  
    String testKey = "c"; 
    try {
      System.out.println(S.get(testKey)); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key " + testKey + " does not exist!"); 
    }
    
    System.out.println("\n[5] Should print out:\nKey d does not exist!");  
    testKey = "d"; 
    try {
      System.out.println(S.get(testKey)); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key " + testKey + " does not exist!"); 
    }
    
    System.out.println("\n[6] Should print out:\n(a,3) : (b,1) : (c,4) "); 
    S.put("c",4);
    System.out.println(S);    
    
    System.out.println("\n[7] Should print out:\ntrue"); 
    System.out.println(S.contains("c"));  
    
    System.out.println("\n[8] Should print out:\ntrue"); 
    System.out.println(S.contains("a")); 
    
    System.out.println("\n[9] Should print out:\nfalse"); 
    System.out.println(S.contains("e"));  
    
    S.delete("a"); 
    S.delete("d"); 
    S.delete("c"); 
    System.out.println("\n[10] Should print out:\n(b,1)");     
    System.out.println(S); 
    
    System.out.println("\n[11] Should print out:\n0");  
    S.delete("b"); 
    System.out.println(S.size()); 
    
    System.out.println("\n[12] Should print out:\nnope! nope! nope! nope! nope!");  
    try{
      System.out.println(S.min()); 
    }
    catch(KeyNotFoundException e) {
      System.out.print("nope! "); 
    }
    
    try{
      System.out.println(S.max()); 
    }
    catch(KeyNotFoundException e) {
      System.out.print("nope! "); 
    }
    try{
      System.out.println(S.floor("a")); 
    }
    catch(KeyNotFoundException e) {
      System.out.print("nope! "); 
    }
    try{
      System.out.println(S.ceiling("a")); 
    }
    catch(KeyNotFoundException e) {
      System.out.print("nope! "); 
    }
    
    
    try{
      System.out.println(S.select(0)); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("nope! "); 
    }
    
    
    
    SymbolTable<String> T = new SymbolTable<String>(); 
    
    T.put("09:00:00","Chicago");
    T.put("09:00:03","Phoenix");
    T.put("09:00:13","Houston");
    T.put("09:00:59","Chicago"); 
    T.put("09:36:14","Seattle");
    T.put("09:37:44","Phoenix");
    T.put("09:10:25","Seattle");
    T.put("09:14:25","Phoenix");
    T.put("09:19:32","Chicago");
    T.put("09:19:46","Chicago");
    T.put("09:21:05","Chicago");
    T.put("09:22:43","Seattle");
    T.put("09:01:10","Houston");
    T.put("09:03:13","Chicago");
    T.put("09:10:11","Seattle");
    T.put("09:25:52","Chicago");
    T.put("09:22:54","Seattle");  
    T.put("09:35:21","Chicago");
    
    System.out.println("\n[13] Should print out:\n(09:00:00,Chicago) : (09:00:03,Phoenix) : (09:00:13,Houston) : (09:00:59,Chicago) : (09:01:10,Houston) : (09:03:13,Chicago) : (09:10:11,Seattle) : (09:10:25,Seattle) : (09:14:25,Phoenix) : (09:19:32,Chicago) : (09:19:46,Chicago) : (09:21:05,Chicago) : (09:22:43,Seattle) : (09:22:54,Seattle) : (09:25:52,Chicago) : (09:35:21,Chicago) : (09:36:14,Seattle) : (09:37:44,Phoenix)");      
    System.out.println("\n" + T);    
    
    
    try{
      System.out.println("\n[14] Should print out:\n09:00:00");
      System.out.println(T.min()); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:00:00 does not exist!"); 
    } 
    try{
      System.out.println("\n[15] Should print out:\n09:37:44");
      System.out.println(T.max()); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:37:44 does not exist!"); 
    } 
    
    try{
      System.out.println("\n[16] Should print out:\n09:03:13");
      System.out.println(T.floor("09:05:00")); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:05:00 does not exist!"); 
    } 
    
    try{
      System.out.println("\n[17] Should print out:\n09:35:21");
      System.out.println(T.floor("09:35:21")); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:35:21 does not exist!"); 
    }
    
    try{
      System.out.println("\n[18] Should print out:\n09:35:21");
      System.out.println(T.ceiling("09:30:00")); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:35:21 does not exist!"); 
    } 
    
    try{
      System.out.println("\n[19] Should print out:\n09:00:00");
      System.out.println(T.ceiling("09:00:00")); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:00:00 does not exist!"); 
    }
    
    try{
      System.out.println("\n[20] Should print out:\n09:10:25");
      System.out.println(T.select(7)); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Key 09:10:25 does not exist!"); 
    } 
    
    
    System.out.println("\n[21] Should print out:\n7");
    System.out.println(T.rank("09:10:25")); 
    
    System.out.println("\n[22] Should print out:\n15");
    System.out.println(T.rank("09:30:00"));     
    
    System.out.println("\n[23] Should print out:\n5");
    System.out.println(T.size("09:15:00", "09:25:00")); 
    
    try {
      System.out.println("\n[24] Should print out:\n18 18");
      System.out.println(T.size() + " " + T.size(T.min(), T.max())); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Symbol table empty!"); 
    }
    
    try {   
      System.out.println("\n[25] Should print out:\n09:00:03");
      T.deleteMin(); 
      System.out.println(T.min()); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Symbol table empty!");
    }
    
    try {   
      System.out.println("\n[26] Should print out:\n09:36:14");
      T.deleteMax(); 
      System.out.println(T.max()); 
    }
    catch(KeyNotFoundException e) {
      System.out.println("Symbol table empty!");
    }
    
    
    System.out.println("\n[27] Should print out:\n16");
    System.out.println(T.size()); 
    
    
  
    
  }
}


class KeyNotFoundException extends Exception {}


