/*
 * ArticleTable.java
 * 
 * Hash table that holds all of the articles
 *
 * Author: Sameena Bajwa (sbajwa@bu.edu)
 * Date: April 10, 2016
 */

import java.util.*;

public class ArticleTable {
    
    // taken from Minipedia.java code
    
    private static Article[] getArticleList(DatabaseIterator db) {
        
        // count how many articles are in the directory
        int count = db.getNumArticles(); 
        
        // now create array
        Article[] list = new Article[count];
        for(int i = 0; i < count; ++i)
            list[i] = db.next();
        
        return list; 
    }
    
     // taken from Minipedia.java code
    
    private static DatabaseIterator setupDatabase(String path) {
        return new DatabaseIterator(path);
    }
    
    // constructors for article table
    
   public static class Node {
      public String key;
      public Article datum;
      public Node next;
      
      // global pointer for iterators
      public Node next2;
      
      public String title;
      
      public Node(Article datum, Node next) {
          
          this.datum = datum;
          this.next = next;
          this.next2 = next;

      }
      
      public Node(String title, Node next ) {
          
          this.title = title;
          this.next = next;
          this.next2 = next;
      }
      
      
      public Node (Article datum ) {
          
          this (datum, null);
          this.next2 = null;
      }
              
      
   }
   
   // number of articles
   
    private static int N = 0;
    
    public Node x = null;
   
   private static Node head = null;
   
   // size of the table
   
   private static final int SIZE = 2503;
   
   private  static Node [] T = new Node [SIZE];
   
   
   public void initialize (Article [] A) {
       
       for (int i = 0; i < A.length; ++i) {
           
           insert (A[i]);
       }
   }
           
   // code based off website given in assignment, hash function for strings
   
   public static int hash(String key) {
       
       char ch [] = key.toCharArray();
       int keyLength = key.length();
       
       int i;
       int sum;
       
       for (i = 0, sum = 0; i < keyLength; ++i) 
           
           sum += ch[i];
       return sum % SIZE;
   }
   
   // code based off lecture notes; insert article into table
   
   public void insert (Article a) {
       
       String title = a.getTitle();  
       
       if (N == T.length) {
           
           System.out.println("Cannot insert into full table  -- insert(" 
                                  + title +  ") not performed!");
       }
                                  
       // if the article title is already in the hash table, do nothing
           
       if (member(title) == true) {
           
           System.out.println ("Article found in database");
           return;
       }
      
       // call to the helper function to insert into the bucket that the "hashed"
       // title corresponds to
       
       T[hash(title)] = insert (a , T[hash(title)]);
       
       head = new Node (a, head);
   }
  
   // insert helper function 

   private  Node insert(Article a, Node p) {
     
       if (p == null) {
            ++ N;
            return new Node (a);
       }
       
       else {
            
           p.next = insert (a , p.next);
           return p;

       }
       
   }
   
   // look up the article title to see if it is already present in the hash table
   
   public Article lookup (String title) {
       
       // call to helper function 
       
       Node p =  lookup (title, T[hash(title)]);
       
       if (p == null)
           
           return null;
       
       else
           return p.datum;
   }
       
 
   
   private Node lookup (String k, Node p) {
       
       if (p == null)
           
           return null;
       
       // compare the key to the title in the node that is currently 
       // being searched through
       
       else if (k.compareTo(p.datum.getTitle()) == 0) 
           
           return p;
       else
           
           return lookup (k, p.next);
   }
   
   public boolean member (String title) {
       
       if (lookup (title) == null) 
           
           return false;
       
       else
           return true;
   }
   
   
   public void delete (String title) {
       
       T[hash(title)] = delete (title, T[hash(title)]);
       
       head = delete (title, head);
   }
   
   private Node delete (String k, Node p) {
       
       if (p == null) {

           return p;
       }
       
       // compare if the title being deleted equals the title of the node  
       // being searched 
       
       else if (p.datum.getTitle().compareTo(k) == 0) {
           
           return p.next;
       }
       
       else {

           p.next = delete (k, p.next);
           --N;
           return p;
       }
   }
   
   // point global pointer back to head 
   
   public void reset() {
       
       x = head;
   }
   

   
   // hash table has more articles if the global pointer is pointing to something
   // based off of lecture notes
   
   public boolean hasNext () {
       
       if (x.next2 == null)
           
           return false;
       
       else
           
           return true;
   }
   
   public Article next() {
       
       Article temp = x.datum;
       
       x = x.next;
       
       
       return temp;

   }
   
   // code based off of lecture notes
   
   public int size() {
       
       return N;
   }
   
       
   public static void main (String [] args) {
       
       String dbPath = "articles/";
       
       DatabaseIterator db = setupDatabase(dbPath);
       
       ArticleTable X = new ArticleTable();
       
       Article [] Y = getArticleList(db);
       
       X.initialize(Y);
       
       System.out.println ("The amount of articles currently in the table: " + N);
       System.out.println();
       
       // testing insert on articles already in the table
       
       System.out.println ("Attemping to insert '" + Y[100].getTitle() + "': should say 'Article found in database'");
       X.insert(Y[100]);
       
       System.out.println ("\nAttemping to insert '" + Y[350].getTitle() + "': should say 'Article found in database'");
       X.insert(Y[350]);
       
       // create a new article to be tested
       
       String testTitle = "Testing";
       String testBody = "This is a test to see if the insert function can properly place a new article " + 
           "into this hash table.";
       
       Article test = new Article (testTitle, testBody);
       
       // test insert by inserting article test into hash table
       
       System.out.println ("Insert article " + test.getTitle() +  "\n");
       X.insert(test);
       
       // test size and variable N
       
       System.out.println ("Testing size of table...\nShould be: 1008");
       System.out.println(X.size());
       System.out.println();
       
       // test look up by looking up the item under the given article
       
       System.out.print((X.lookup(test.getTitle())));
       System.out.println ("Testing look up... \nShould print out: " + testBody);
       
       // test member function
       
       System.out.println ("\nTesting membership of article 'test'...");
       System.out.println ("Should return: true");
       System.out.println(X.member(testTitle));
       System.out.println();
       
       // test delete method by removing new article
       
       System.out.println ("Delete article '" + test.getTitle() + "'");
       X.delete(test.getTitle());
       System.out.println();
       
       // test size and variable N
       
       System.out.println ("Testing size of table...\nShould be: 1007");
       System.out.println(X.size());
       System.out.println();
       
       // test look up of a deleted article
       
       System.out.println("Testing look up...\nShould return null");
       System.out.println(X.lookup(test.getTitle()));
       System.out.println();
       
       // test membership 
       
       System.out.println ("Testing membership of article 'test'...\nShould return: false");
       System.out.println (X.member(test.getTitle()));
       System.out.println();
       
       // testing iterators by printing master list
       
       //code given in assignment description
       
//       X.reset();
//       while (X.hasNext()) {
//           
//           Article a = X.next();
//           System.out.println(a.getTitle());
       }
       

       
   }




