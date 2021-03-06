/*
 * Author: Sameena Bajwa (sbajwa@bu.edu)
 * Date: April 16, 2016
 * Class: CS112
 * Purpose: Create a program that will allow the user to search a "mini google"
 * with a search phrase, and in return get the topic articles in the database 
 * that best matches their search phrase
*/

import java.util.*;
import java.io.*;


public class MiniGoogle {
    
    // blacklist that must be used for comparison when preprocessing a string
    
    private static final String [] blackList = { "the", "of", "and", "a", "to", "in", "is", 
        "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", 
        "his", "they", "i", "at", "be", "this", "have", "from", "or", "one", 
        "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", 
        "your", "can", "said", "there", "use", "an", "each", "which", "she", 
        "do", "how", "their", "if", "will", "up", "other", "about", "out", "many", 
        "then", "them", "these", "so", "some", "her", "would", "make", "like", 
        "him", "into", "time", "has", "look", "two", "more", "write", "go", "see", 
        "number", "no", "way", "could", "people",  "my", "than", "first", "water", 
        "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", 
        "did", "get", "come", "made", "may", "part" }; 
  
    public static Article[] getArticleList(DatabaseIterator db) {
        
        // count how many articles are in the directory
        int count = db.getNumArticles(); 
        
        // now create array
        Article[] list = new Article[count];
        for(int i = 0; i < count; ++i)
            list[i] = db.next();
        
        return list; 
    }
    
    private static DatabaseIterator setupDatabase(String path) {
        return new DatabaseIterator(path);
    }
    
    private static void addArticle(Scanner s, ArticleTable D) {
        System.out.println();
        System.out.println("Add an article");
        System.out.println("==============");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        System.out.println("You may now enter the body of the article.");
        System.out.println("Press return two times when you are done.");
        
        String body = "";
        String line = "";
        do {
            line = s.nextLine();
            body += line + "\n";
        } while (!line.equals(""));
        
        D.insert(new Article(title, body));
    }
    
    
    private static void removeArticle(Scanner s, ArticleTable D) {
        System.out.println();
        System.out.println("Remove an article");
        System.out.println("=================");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        
        D.delete(title);
    }
    
    
    private static void titleSearch(Scanner s, ArticleTable D) {
        System.out.println();
        System.out.println("Search by article title");
        System.out.println("=======================");
        
        System.out.print("Enter article title: ");
        String title = s.nextLine();
        
        Article a = D.lookup(title);
        if(a != null)
            System.out.println(a);
        else {
            System.out.println("Article not found!"); 
            return; 
        }
        
        System.out.println("Press return when finished reading.");
        s.nextLine();
    }
    
    private static String preprocess (String s) {
        
        // new string that will hold value of s, but with all lowercase letters
        String newS = s.toLowerCase();
        
        //blank string to place processed characters of newS into
        String processedS = "";
        
        for (int i = 0; i < newS.length(); ++i) {
            
            char character = newS.charAt(i);
            
            if ((Character.isWhitespace(character) == true) || (Character.isDigit(character) == true) || (Character.isLetter(character) == true)) {
                
                processedS += character;
            }
            
            else {
                
                processedS += " ";
            }
        }
        
        return processedS;
    }
    
    private static boolean blacklisted (String s) {
        
        // return true if string is found while iterating through blacklist array
        
        for (int i = 0; i < blackList.length; ++ i) {
            
            if (s.compareTo(blackList[i]) == 0)
                
                return true;
        }
        
        return false;
    }
    
    private static double getCosineSimilarity (String s, String t) {
        
        // preprocess both strings 
        
        String newS = preprocess(s);
        String newT = preprocess(t);
        
        // create new term frequency table for each article processed
        TermFrequencyTable T = new TermFrequencyTable ();
        
        // use string tokenizer class to extract each of the terms and insert
        // them into a term frequency table
        
        StringTokenizer sToken = new StringTokenizer(newS);
        while(sToken.hasMoreTokens()) {
            
            String wordS = sToken.nextToken();
            
            if (blacklisted(wordS) == false) {
                
                // String s belongs to document number 0
                T.insert(wordS, 0);
            }
        }
        
        StringTokenizer tToken = new StringTokenizer(newT);
        
        while (tToken.hasMoreTokens()) {
            
            String wordT = tToken.nextToken();
            
            if (blacklisted(wordT) == false) {
                
                
                // String t belongs to document number 1
                T.insert(wordT, 1);
            }
        }
        
        return T.cosineSimilarity();
    }
    
    public static String phraseSearch (String phrase, ArticleTable T) {

        
        int amount = 0;
        
        MaxHeap M = new MaxHeap();
        
        // use ArticleTable iterators to find cosine similarity between the search
        // phrase and each article
        
        T.reset();
        while (T.hasNext()) {
            
            Article x = T.next();
            
            double similarity = getCosineSimilarity (phrase, x.getBody());
            
            // if cosine value is greater than 0.001, insert article into max heap
            if (similarity > 0.001) {
                
                amount ++;
                M.insert(similarity, x);
            }
        }
        
        if (amount == 0) {
            
            return ("No matching articles found!");
        }
        
        else {
            
            // call for the top three articles from the max heap to be printed
            for (int i = 1; i <= amount; ++ i) {
                
                Article a = M.getMax();
                System.out.println ("Match " + i + " with cosine similarity of " + getCosineSimilarity(phrase, a.getBody()) + ":\n");
                
                System.out.println(a.getTitle());
                System.out.println("===");
                System.out.println(a.getBody());
                
                // break for loop after top three articles have been printed
                if (i >= 3) 
                    break;
            }
        }
        
        return "";
    }
            
                  
    
    public static void main(String[] args) {
                     
                           
        Scanner user = new Scanner(System.in);
        
        String dbPath = "articles/";
        
        DatabaseIterator db = setupDatabase(dbPath);
        
        System.out.println("Read " + db.getNumArticles() + 
                           " articles from disk.");
        
        ArticleTable L = new ArticleTable(); 
        Article[] A = getArticleList(db);
        L.initialize(A);
        
        
        int choice = -1;
        do {
            System.out.println();
            System.out.println("Welcome to Mini-Google!");
            System.out.println("=====================");
            System.out.println("Make a selection from the " +
                               "following options:");
            System.out.println();
            System.out.println("    1. add a new article");
            System.out.println("    2. remove an article");
            System.out.println("    3. search by exact article title");
            System.out.println("    4. search by phrase (list of keywords)");
            System.out.println();
            
            System.out.print("Enter a selection (1-4, or 0 to quit): ");
            
            choice = user.nextInt();
            user.nextLine();
            
            switch (choice) {
                case 0:
                    return;
                    
                case 1:
                    addArticle(user, L);
                    break;
                    
                case 2:
                    removeArticle(user, L);
                    break;
                    
                case 3:
                    titleSearch(user, L);
                    break;
                
                case 4:
                            
                    System.out.println("Search by article content");
                    System.out.println("=========================");
                    System.out.println("Enter search phrase: ");
                    String phrase = user.nextLine();
                    phraseSearch(phrase, L);
                    break;
                    
                default:
                    break;
            }
            
            choice = -1;
            
        } while (choice < 0 || choice > 4);
        
    }
    

        
        
    
    
}


