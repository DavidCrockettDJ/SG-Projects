/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineUI;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class UserIOImpl implements UserIO{
    
    
    Scanner sc = new Scanner(System.in);  
    
    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public double readDouble(String prompt) {
        while (true) {
        String input;
        System.out.println(prompt);
        input = sc.nextLine();
        double userInput = Double.parseDouble(input);
        if (userInput >= -0.1 || userInput <= -0.1) {
        return userInput;
        }
        }
    }
    
    @Override
    public double readDouble(String prompt, double min, double max) {
        while (true) {
            double result = readDouble(prompt);
            if (result > min && result < max) {
                return result;
                
                
            }
            
        }
    }
    
    @Override
    public float readFloat(String prompt) {
        while (true) {
            String input;
            System.out.println(prompt);
            input = sc.nextLine();
            float userInput = Float.parseFloat(input);
            if (userInput >= -0.1 || userInput <= -0.1) {
                return userInput;
            }
                
        }
    }
    
    @Override
    public float readFloat(String prompt, float min, float max) {
        while (true) {
            float result = readFloat(prompt);
            if (result > min && result < max) {
                return result;
            }
        }
    }
    
    @Override
    public int readInt (String prompt) {
        while (true) {
            String input;
            System.out.println(prompt);
            input = sc.nextLine();
            int userInput = Integer.parseInt(input);
            if (userInput >= -1 || userInput <= -1) {
                return userInput;
            }
        }
    }
    
    @Override
    public int readInt(String prompt, int min, int max) {
        while (true) {
            int result = readInt(prompt);
            if (result > min && result < max) {
                return result;
            }
                
        }
    }
    
    @Override 
      public long readLong(String prompt) {
        while (true) {
            String input;
            System.out.println(prompt);
            input = sc.nextLine();
            long userInput = Long.parseLong(input);
            if (userInput >= -0.1 || userInput <= -0.1) {
                return userInput;
            }
    }
      }
      
      @Override
      public long readLong(String prompt, long min, long max) {
          while (true) {
              long result = readLong(prompt);
              if (result > min && result < max) {
                  return result;
              }
          }
      }
      
      @Override
      public String readString (String prompt) {
          System.out.println(prompt);
          String input = sc.nextLine();
          return input;
      }
}
