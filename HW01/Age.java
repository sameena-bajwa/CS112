/*
 *Author: Sameena Bajwa
 *Filename: Age.java
 *Date: January 26th 2016
 *Purpose: This is a solution to problem B.1 of HW01.
*/
public class Age{
    public static void main(String args[]){
        int ageInSeconds = 1000000000;
        System.out.printf("Age in seconds: " + ageInSeconds);
        
        double years = ageInSeconds / 31536000.0;
        System.out.printf("\nAge in years: %.2f\n", years);
        
        double months = ageInSeconds / 2592000.0;
        System.out.printf("Age in months: %.2f\n", months);
        
        double days = ageInSeconds / 86400.0;
        System.out.printf("Age in days: %.2f\n\n", days);
        
        System.out.printf("Age in years, months, days, hours, minutes, and seconds (all integers): ");
        
        System.out.println("\n\tYears: " + (int)years);
        
        int remainingSeconds = ageInSeconds % ((int)years * 31536000);
        int remainingMonths = remainingSeconds / 2592000;
        System.out.println("\tMonths: " + remainingMonths);
        
        remainingSeconds = remainingSeconds % (remainingMonths * 2592000);
        int remainingDays = remainingSeconds / 86400;
        System.out.println("\tDays: " + remainingDays);
        
        remainingSeconds = remainingSeconds % (remainingDays * 86400);
        int hours = remainingSeconds / 3600;
        System.out.println("\tHours: " + hours);
        
        remainingSeconds = remainingSeconds % (hours * 3600);
        int minutes = remainingSeconds / 60;
        System.out.println("\tMinutes: " + minutes);
        
        remainingSeconds = remainingSeconds % (minutes * 60);
        System.out.println("\tSeconds: " + remainingSeconds);
        
    }}
           