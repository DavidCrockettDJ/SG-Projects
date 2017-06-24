/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDui;

import DVDdto.DVDDto;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class DVDView {
    ImplUserio io = new ImplUserio();
    DVDDto DVD = new DVDDto();
    
    public void DVDGreeting() {
        io.print("Welcome to you DVD Library! We're so glad you're here!");
        io.print("XXXXXXXXXXXXXXX");
        io.print(" ");
        
    }
    
    public int mainMenu() {
        io.print("How can we help you?");
        io.print("1. Add a DVD to your collection.");
        io.print("2. Delete a DVD from your collection.");
        io.print("3. List the DVDs in your collection.");
        io.print("4. Edit a DVD in your collection.");
        io.print("5. Search and display a specific DVD from your collection.");
        io.print("6. Exit the program.");
        int choice = io.readInt("Please choose the option you'd like to do (enter a number between 1 and 6)", 0, 8);
        return choice;
    }
    
    public DVDDto addDVD() {
        io.print("You've selected to add a DVD to your collection. How exciting!");
        io.print("XXXXXXXXXXXXXXX");
        io.print(" ");
        DVDDto currentDVD = new DVDDto();
        String title = io.readString("Please enter a title:");
        String releaseDate = io.readString("Please enter release date(Jan. 1, 0000):");
        String mpaaRating = io.readString("Please enter the MPAA rating for the DVD:");
        String directorName = io.readString("Please enter the name of the director:");
        String studio = io.readString("Please enter the name of the studio that produced the DVD:");
        String userRating = io.readString("Please enter the user rating of the DVD. If there is none, please share your own:");
        io.print(" ");
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }
    
    public String deleteDVD() {
        io.print("You've selected to delete a DVD from your collection. Aww!");
        io.print("XXXXXXXXXXXXXXX");
        io.print(" ");
        String title = io.readString("Please choose the title of the DVD you'd like to remove from your collection:");
        return title;
    }
    
    public void listDVD(List<DVDDto> DVDList) {
        io.print("You've selected to list out your entire DVD collection! Yay, let's see what you have!");
        io.print("XXXXXXXXXXXXXXX");
        io.print(" ");
        showList(DVDList);
    }
    
    public void showList(List<DVDDto> DVDList) {
        for (DVDDto listDVD : DVDList) {
            io.print(listDVD.getTitle() + "/ " + listDVD.getReleaseDate() + "/ " + listDVD.getMpaaRating() + "/ " + listDVD.getDirectorName() + "/ " + listDVD.getStudio() + "/ " + listDVD.getUserRating());
            io.print(" ");
        }
    }
    
    public String searchDVD() {
        io.print("You've selected to search and display a specific DVD from your collection. Let's find it!");
        io.print("XXXXXXXXXXXXXXX");
        io.print(" ");
        String title = io.readString("Please choose the title of the DVD you'd like to display:");
        io.print(" ");
        return title;
    }
    
    public String editDVD() {
        io.print("You've selected to edit a DVD in your collection. Woo!");
        io.print("XXXXXXXXXXXXXX");
        io.print(" ");
        String titleDVD = io.readString("Please choose the title of the DVD you'd like to edit: ");
        return titleDVD;
    }
    
    public int editChoice() {
        io.print("Which part would you like to edit?");
        io.print("1. Title");
        io.print("2. Release Date");
        io.print("3. Mpaa Rating");
        io.print("4. Director Name");
        io.print("5. Studio");
        io.print("6. User Rating");
       int userChoice = io.readInt("Enter the number that corresponds to what you'd like to edit (between 1 and 6):", 0, 7);
       return userChoice;
    }
    
    public String editTitle() {
        String newTitle = io.readString("Please enter the new title for the DVD:");
        return newTitle;
    }
    
    public String editReleaseDate() {
        String newReleaseDate = io.readString("Please enter the new release date for the DVD:");
        return newReleaseDate;
    }
    
    public String editMpaaRating() {
        String newMpaaRating = io.readString("Please enter the new Mpaa Rating for the DVD:");
        return newMpaaRating;
    }
    
    public String editDirectorName() {
        String newDirectorName = io.readString("Please enter the new director name for the DVD:");
        return newDirectorName;
    }
    
    public String editStudio() {
        String newStudio = io.readString("Please enter the new Studio name for the DVD:");
        return newStudio;
    }
    
    public String editUserRating() {
        String newUserRating = io.readString("Please enter the new user rating for the DVD:");
        return newUserRating;
    }
    
    public DVDDto printDVD(DVDDto DVD) {
        io.print(DVD.getTitle() + "/ " + DVD.getReleaseDate() + "/ " + DVD.getMpaaRating() + "/ " + DVD.getDirectorName() + "/ " + DVD.getStudio() + "/ " + DVD.getUserRating());
        io.print(" ");
        return DVD;
    }
    
    public void goodbyeMessage() {
        io.print("We sure do hate to see you go. Thanks for coming by, we hope to see you again soon!");
    }
    
    public void unknown() {
        io.print("Unknown command");
    }
    
    
}
