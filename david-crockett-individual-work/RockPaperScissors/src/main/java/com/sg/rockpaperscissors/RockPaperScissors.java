/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class RockPaperScissors {

    static Scanner sc = new Scanner(System.in);
    static String result;
    static Random rps = new Random();

    public static void play() {

        int rounds;
        String input = "yes";
        int ties = 0;
        int winCPU = 0;
        int winPlayer = 0;
        while (input.equals("yes")) {
            rounds = getChoice("Welcome to RPS!!\nHow many rounds would you like to play?");

            if (rounds < 1 || rounds > 10) {
                System.out.println("Sorry, invalid number. Try entering a number between 1 and 10 next time.");
                break;
            }

            

                for (int i = 0; i < rounds; i++) {
                    int cpuChoice = rps.nextInt(3) + 1;
                    int choice2 = getChoice("Wonderful! Please choose 1 for Rock, 2 for Paper, and 3 for Scissors!");

                    if (cpuChoice == choice2) {
                        System.out.println("Tie!");
                        ties++;
                    } if (choice2 == 1) {
                        switch (cpuChoice) {
                            case 2:
                                System.out.println("Winner: Me!");
                                winCPU++;
                                break;
                            case 3:
                                System.out.println("Winner: Player.");
                                winPlayer++;
                                break;
                        }
                    }

                    if (choice2 == 2) {
                        switch (cpuChoice) {
                            case 1:
                                System.out.println("Winner: Player.");
                                winPlayer++;
                                break;
                            case 3:
                                System.out.println("Winner: Me!");
                                winCPU++;
                                break;
                        }
                    }
                    if (choice2 == 3) {
                        switch (cpuChoice) {
                            case 1:
                                System.out.println("Winner: Me!");
                                winCPU++;
                                break;
                            case 2:
                                System.out.println("Winner: Player.");
                                winPlayer++;
                                break;

                        }
                    }
                }
            

            System.out.println("\n\nPlayer wins: " + winPlayer + "\nMy wins: " + winCPU + "\nTies: " + ties);

            getResults(winPlayer, winCPU, ties);

            System.out.println("Would you like to play again?");
            input = sc.nextLine();
        }

        System.out.println("Thanks for playing!!");

    }

    public static int getChoice(String prompt) {
        String input;
        System.out.println(prompt);
        input = sc.nextLine();
        int choice = Integer.parseInt(input);

        return choice;
    }

    public static void getResults(int winPlayer, int winCPU, int ties) {
        if (winPlayer > winCPU) {
            System.out.println("\nOverall winner: Player!!!");
        }
        if (winCPU > winPlayer) {
            System.out.println("\nOverall winner: Me!!!");
        }
        if (winPlayer == winCPU) {
            System.out.println("\nIt's a tie!!!");
        }
    }

}
