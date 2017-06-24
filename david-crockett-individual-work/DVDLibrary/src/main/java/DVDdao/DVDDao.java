/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDdao;

import DVDdto.DVDDto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class DVDDao {
    DVDDto DVD = new DVDDto();
    List<DVDDto> DVDList = new ArrayList<>();
    
    public static final String DVD_LIBRARY = "DVDLibray.txt";
    
    public static final String DELIMITER = "::";
    
    private List<DVDDto> load() throws DVDException {
	    Scanner scanner;
	    List<DVDDto> DVDList = new ArrayList<>();
	    try {
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(DVD_LIBRARY)));
	    } catch (FileNotFoundException e) {
	        throw new DVDException(
	                "-_- Could not load DVD data into memory.", e);
	    }
            
            String currentLine;
            String[] currentString;
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentString = currentLine.split(DELIMITER);
                DVDDto currentDVD = new DVDDto();
                currentDVD.setTitle(currentString[0]);
                currentDVD.setReleaseDate(currentString[1]);
                currentDVD.setMpaaRating(currentString[2]);
                currentDVD.setDirectorName(currentString[3]);
                currentDVD.setStudio(currentString[4]);
                currentDVD.setUserRating(currentString[5]);
                DVDList.add(currentDVD);
                
            }
            scanner.close();
            return DVDList;
            }
    
    public void write() throws DVDException{
        List<DVDDto> DVDList = this.writeList();
        PrintWriter out = null;
        try {
        out = new PrintWriter(new FileWriter(DVD_LIBRARY));
        } catch (IOException e) {
            throw new DVDException("Could not save DVD data.", e);
        }
        for (DVDDto currentDVD : DVDList) {
            out.println(currentDVD.getTitle() + DELIMITER + currentDVD.getReleaseDate() + DELIMITER + currentDVD.getMpaaRating() 
                    + DELIMITER + currentDVD.getDirectorName() + DELIMITER + currentDVD.getStudio() + DELIMITER + currentDVD.getUserRating() );
            out.flush();
        }
        out.close();
    }
    
    public void addDVD(DVDDto DVD) throws DVDException{
        DVDList.add(DVD);
        write();
    }
    
    public boolean deleteDVD(String title) throws DVDException{
        DVDDto removeDVD = null;
        for(DVDDto DVD : DVDList) {
            if (title.equals(DVD.getTitle())) {
                removeDVD = DVD;
            }
            if (removeDVD == DVD) {
                DVDList.remove(removeDVD);
                write();
                break;
            }
        }
        return true;
    }
    
    
    public DVDDto searchDVD(String title) throws DVDException{
        DVDDto findDVD = null;
        DVDList = load();
        for(DVDDto DVD : DVDList) {
            if (title.equals(DVD.getTitle())) {
                findDVD = DVD;
                return findDVD;
            }
        }
        return findDVD;
    }
    
    public List<DVDDto> listDVD() throws DVDException{
        DVDList = load();
        return DVDList;
        
    }
    
    public List<DVDDto> writeList() throws DVDException {
        return new ArrayList<DVDDto>(DVDList);
    }
    
}
