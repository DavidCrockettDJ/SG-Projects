/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FloorMasteryDao;

import FloorMasteryDto.Floor;
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
public class FloorDao {
    Map<String, Floor> floors = new HashMap<>();
    private static final String FLOOR = "Data/Products.txt";
    
    private static final String DELIMITER = "::";
    
    
    private void loadFloor() throws FloorMasteryException{
        Scanner sc;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(FLOOR)));
        }catch (FileNotFoundException e) {
            throw new FloorMasteryException(
                        "Could not load file contents into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Floor currentFloor = new Floor();
            currentFloor.setFloorType(currentTokens[0]);
            currentFloor.setPriceSquareFoot(new BigDecimal(currentTokens[1]));
            currentFloor.setLaborCostSquareFoot(new BigDecimal(currentTokens[2]));
            
            floors.put(currentFloor.getFloorType(), currentFloor);
        }
        sc.close();
    }
    
    public Floor getFloor(String floor) throws FloorMasteryException{
        loadFloor();
        return floors.get(floor);
    }
    
}
