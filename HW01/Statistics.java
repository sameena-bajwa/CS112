/*
 * File: Statistics.java
 * Author: Sameena Bajwa
 * Date: January 26th, 2015
 * Purpose: This is a solution to problem B.2 of HW 01
*/


import java.util.Scanner;

public class Statistics { 
  
  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    
    System.out.println("\nType in the first number and then hit return:"); 
    int numOne = userInput.nextInt(); 
    System.out.println("\nYour input was " + numOne);
    
    System.out.println("\nType in the second number and then hit return:");
    int numTwo = userInput.nextInt();
    System.out.println("\nYour input was " + numTwo);
    
    System.out.println("\nType in the third number and then hit return:");
    int numThree = userInput.nextInt();
    System.out.println("\nYour input was " +numThree);
    
    System.out.println("You have input the numbers " + numOne + ", " + numTwo + ", and " + numThree);
    
    int sum = numOne + numTwo + numThree;
    System.out.println("\nThe sum is " + sum);
    
    int max = Math.max(numOne, Math.max(numTwo, numThree));
    System.out.println("The max is " + max);
    
    int min= Math.min(numOne, Math.min(numTwo, numThree));
    System.out.println("The min is " + min);
    
    int range = max - min;
    System.out.println("The range is " + range);
    
    double mean = (numOne + numTwo + numThree) / 3.0;
    System.out.printf("The mean is " + "%.2f", + mean);
    
    double variance = (((numOne - mean)*(numOne-mean)) + ((numTwo - mean)*(numTwo-mean)) + ((numThree - mean)*(numThree - mean))) / 3;
    System.out.printf("\nThe variance is " + "%.2f", + variance);
    
    double stand_dev = Math.sqrt(variance);
    System.out.printf("\nThe standard deviation is " + "%.2f", + stand_dev);
    
    int median;
    
    if (numOne < max && numOne > min) {
        median = numOne;
    }
    else if (numTwo < max && numTwo > min) {
        median = numTwo;
    }
    else {
        median = numThree;
    }
    
    System.out.println("\nThe median is " + median);
    
    
    userInput.close();
  }
  
}