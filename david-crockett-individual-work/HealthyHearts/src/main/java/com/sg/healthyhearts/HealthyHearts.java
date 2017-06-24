/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class HealthyHearts {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        int age = getAge("What is your age?");
        
        int maxHeartRate = 220 - age;
        
        float targetHR1 = maxHeartRate * 0.5f;
        
        float targetHR2 = maxHeartRate * 0.85f;
        
        System.out.println("Your maximum heart rate should be " + maxHeartRate + " beats per minute.");
        
        System.out.println("Your target hear rate zone is " + (int)targetHR1 + " - " +(int)targetHR2 + " beats per minute.");
        
    }
    
    public static int getAge(String prompt) {
        String input;
        System.out.println(prompt);
        input = sc.nextLine();
        int age = Integer.parseInt(input);
        return age;
    }
}
