/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryDao;

import FloorMasteryDto.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class TaxesDao {
    Taxes tax = new Taxes();
    Map<String, Taxes> getTaxes = new HashMap<>();
    
    private static final String TAXES = "Data/Taxes.txt";
    
    private static final String DELIMITER = "::";
    
    private void loadTax() throws FloorMasteryException{
        Scanner scanner;
        try {
          scanner = new Scanner( new BufferedReader(new FileReader(TAXES)));
        }catch (FileNotFoundException e) {
            throw new FloorMasteryException(
                       "Could not load file contents into memory.", e);
        }       
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Taxes currentTax = new Taxes();
            currentTax.setState(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));
            
            getTaxes.put(currentTax.getState(), currentTax);
        }
        scanner.close();      
    }
    
    public Taxes getTax(String state) throws FloorMasteryException{
        loadTax();
        return getTaxes.get(state);
        
    }
    
    
}
