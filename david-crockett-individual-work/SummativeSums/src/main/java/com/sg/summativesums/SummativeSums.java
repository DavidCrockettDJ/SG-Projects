/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.summativesums;

/**
 *
 * @author apprentice
 */
public class SummativeSums {
    public static void main(String[] args) {
        int[] num1 = {
            1, 90, -33, -55, 67, -16, 28, -55, 15
        };
        int[] num2 = {
            999, -60, -77, 14, 160, 301
        };
        int[] num3 = {
            10, 20, 30, 40, 50, 60, 70, 80, 90, 
            100, 110, 120, 130, 140, 150, 160, 
            170, 180, 190, 200, -99
        };
        
        System.out.println("The sum of Array #1 is: " + getResults(num1));
        System.out.println("The sum of Array #2 is: " + getResults(num2));
        System.out.println("The sum of Array #3 is: " + getResults(num3));
        
        
        
    }
    public static int getResults(int num[]) {
       int sum = 0;
        for (int i = 0; i < num.length; i++) {
            
            sum += num[i];
        }
        return sum;
        
    }
}
