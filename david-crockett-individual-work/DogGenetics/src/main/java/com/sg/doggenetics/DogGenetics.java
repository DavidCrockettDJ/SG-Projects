/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.doggenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class DogGenetics {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        int dna1 = 0;
        int dna2 = 0;
        int dna3 = 0;
        int dna4 = 0;
        int dna5 = 0;

        Random rGen = new Random();

        System.out.println("What is the name of your dog?");
        input = sc.nextLine();

        System.out.println("Perfect! Now take a look at our highly scientific report on your dog's origins.");

        System.out.println("\n\n" + input + " is");

        while (dna1 + dna2 + dna3 + dna4 + dna5 != 100) {
            dna1 = rGen.nextInt(100) + 1;
            dna2 = rGen.nextInt(100) + 1;
            dna3 = rGen.nextInt(100) + 1;
            dna4 = rGen.nextInt(100) + 1;
            dna5 = rGen.nextInt(100) + 1;

            if (dna1 + dna2 + dna3 + dna4 + dna5 == 100) {
                getResults(dna1, dna2, dna3, dna4, dna5);
                break;
            }

        }

        System.out.println("\n\nWow! What a lineage! Your dog sure is spectacular!!");

    }

    public static void getResults(int dna1, int dna2, int dna3, int dna4, int dna5) {
        System.out.println(dna1 + "% Labrador Retriever");
        System.out.println(dna2 + "% Border Collie");
        System.out.println(dna3 + "% Jack Russell");
        System.out.println(dna4 + "% Doxon");
        System.out.println(dna5 + "% Husky");
    }
}
