/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDcontroller;

import DVDdao.DVDDao;
import DVDdao.DVDException;
import DVDdto.DVDDto;
import DVDui.DVDView;

/**
 *
 * @author apprentice
 */
public class DVDController {
    DVDView view = new DVDView();
    DVDDao dao = new DVDDao();
    DVDDto DVD = new DVDDto();
    
    
    public void execute() throws DVDException {
        
        view.DVDGreeting();
        
        boolean continueLoop = true;
        
        while (continueLoop) {
            
            int menuChoice = view.mainMenu();
            
            switch(menuChoice) {
                case 1:
                    addDVD();
                    break;
                case 2:
                    deleteDVD();
                    break;
                case 3:
                    listDVD();
                    break;
                case 4:
                    editDVD();
                    break;
                case 5:
                    searchDVD();
                    break;
                case 6:
                    continueLoop = false;
                    view.goodbyeMessage();
                    break;
                default:
                    view.unknown();
            }
        }
    }
    
    private void addDVD() throws DVDException{
        dao.addDVD(view.addDVD());
    }
    
    private void deleteDVD() throws DVDException{
        dao.deleteDVD(view.deleteDVD());
    }
    
    private void listDVD() throws DVDException{
        view.listDVD(dao.listDVD());
    }
    
    private void searchDVD() throws DVDException{
        view.printDVD(dao.searchDVD(view.searchDVD()));
    }
    
    private void editDVD() throws DVDException {
       DVDDto editedDVD = dao.searchDVD(view.editDVD());
       int userChoice = view.editChoice();
       switch(userChoice) {
           case 1:
               editedDVD.setTitle(view.editTitle());
               dao.write();
               break;
           case 2:
               editedDVD.setReleaseDate(view.editReleaseDate());
               dao.write();
               break;
           case 3:
               editedDVD.setMpaaRating(view.editMpaaRating());
               dao.write();
               break;
           case 4:
               editedDVD.setDirectorName(view.editDirectorName());
               dao.write();
               break;
           case 5:
               editedDVD.setStudio(view.editStudio());
               dao.write();
               break;
           case 6:
               editedDVD.setUserRating(view.editUserRating());
               dao.write();
               break;
           default:
               view.unknown();
       }
    }
    
    
}
