import java.util.*;
import java.io.*;
/**
 * A program that loads the English dictionary into memory and allows the user
 * to search the dictionary.
 * 
 * Author: Sameena Bajwa (sbajwa@bu.edu)
 * Date: April 13, 2016
 * Class: CS112
 * 
 * 1. 7 bytes
 * 2. We save 9 bytes
 * 3. Trie worst case time : O(mlogn), linked list worst case time O(logn)
 * 4. You would have to search through each linkedlist for a matching character 
 * and then recurse through that list and compare the characters each time
 * only tasks 1-6 have been implenmented
 */


public class WordSearcher {
    
       
    
             // takes in a string from the file and returns a string that will 
        // not cause an exception to be thrown 
        
            public static String processWord (String line) {
            
            for (int i = 0; i < line.length(); i ++) {
                
                char letter = line.charAt(i);
                
                // removes any characters that are not letter
                
                if (Character.isLetter(letter) == false) {
                    
                    line = line.substring (0,i) + line.substring (i+1);
                    i --;
                }
                
                // changes any upper case letters to lower case
                else if (Character.isLowerCase(letter) == false) {
                    
                    letter = Character.toLowerCase(letter);
                    
                    line = line.substring (0,i) + Character.toString(letter) + line.substring(i+1); 
                }
                
                
                
            }
            return line;
        }
    public static void main(String[] args) throws IOException{
        

        

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        while (input.hasNextLine()) {
            String line = input.nextLine();
            System.out.print("Enter a string: ");
        }
        
                        TrieNode root = new TrieNode();
    
                        
          
        BufferedReader reader = new BufferedReader(new FileReader("words.txt"));   

        try {
            
            
                   
            String word = reader.readLine();
   
            

            
            while (word != null) {
                String newWord = processWord(word);
                root.add(newWord);
                word = reader.readLine();

                
            }
            
            reader.close();
            
            
 
            
        
     
        }
        
        catch (IOException x) {
            
            System.err.println();
        }
        }
    }
