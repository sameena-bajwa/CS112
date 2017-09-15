/* File: Histogram.java
 * Author: Sameena Bajwa
 * Date: February 1, 2016
 * Purpose: Asks users for numbers and prints a histogram of the inputs
*/

import java.util.Scanner;

public class Histogram {

    public static void main(String[] args) {
        
        // Initialize constant variables
        final int NUM_AMOUNT = 20;
        final int NUM_BINS = 10;
        final int BIN_SIZE = 100 / NUM_BINS;
        
        // Initialize scanner and arrays 
        Scanner userInput = new Scanner(System.in);
        double[] numbers = new double[NUM_AMOUNT];
        int[] histogram = new int[NUM_BINS];
        
        // Calls method to print welcome message
        printHeading();
       
        // Checks to see if all inputted numbers follow the requirements       
        for (int i = 0; i <= NUM_AMOUNT; i++) {
            
            double num = userInput.nextDouble();
            
            if (validInput(num) && i < NUM_AMOUNT) {
                numbers[i] = num;
            }
            
            else if (i == NUM_AMOUNT) {
                System.out.println("Maximum number of inputs exceeded, proceeding to calculating histogram...");
                break;
            }    
                
            else {
                System.out.println("Input must be a double in range [0,100]. Try again!");
                i--;
            } 
        }
        
        // Create a string to put inputs into
        String numberList = "";
        
        // Print list of inputs
        for (int i = 0; i < numbers.length - 1; i++) {
            numberList += numbers[i] + ", ";
        }
        
        System.out.println("You input " + numbers.length + " numbers: [" + numberList + numbers[numbers.length - 1] + "]");
        
        // Prints histogram
        printHistogram(numbers, NUM_BINS, histogram, NUM_AMOUNT, BIN_SIZE);
        
    }
    

    
    // Prints welcome message
    public static void printHeading() {
        System.out.println("Welcome to the Histogram program! \nThis program will print out a histogram of the numbers \ninput by the user; enter up to 20 doubles and hit Control-D to end.");
    }
    
    
    // Decide if the inputs are in the valid range
    public static boolean validInput(double d) {
                      
        if (d >= 0 && d <= 100) {
            return true;
        }
        else 
            return false;
    }
    
    // Print out the histogram
    public static void printHistogram(double [] numbers, int NUM_BINS, int [] histogram, int NUM_AMOUNT, int BIN_SIZE) {
            
            // Finds the count for each bin
            for (int i = 0; i < NUM_AMOUNT; i++){
                
                int index = (int)(numbers[i]/BIN_SIZE);
                ++histogram[index];
            }
            
            // Prints bins 
            for (int i = 0; i < NUM_BINS; i ++) {
            
                if (i == 0) {
                    System.out.print("[" + i);
                }
                else {
                    System.out.print("(" + (i * NUM_BINS));
                }
            
                System.out.print(".." + ((i + 1) * NUM_BINS) + "]:\t");
                
                
                // Prints appropriate amount of asterisks per bin
                for (int star = 0; star < histogram[i]; star ++) {
                    System.out.print("*");
                    }
                
                System.out.print("\n");
                }
            }  
            
            
                
            
    }
    
       