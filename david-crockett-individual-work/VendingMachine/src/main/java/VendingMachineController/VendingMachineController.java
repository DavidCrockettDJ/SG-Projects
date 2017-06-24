/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachineController;

import VendingMachineDao.InsufficientFundsException;
import VendingMachineDao.NoItemInventoryException;
import VendingMachineDao.VendingMachineException;
import VendingMachineService.Change;
import VendingMachineService.VendingMachineServiceLayer;
import VendingMachineUI.VendingMachineView;

/**
 *
 * @author apprentice
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;
    String input = "";

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void execute() throws VendingMachineException {

        input = displayWelcome();

        while (!input.equalsIgnoreCase("no") && !input.equalsIgnoreCase("Exit")) {

            collectMoney();

            selectItem();

            //if (!input.equalsIgnoreCase("Exit") && validateMoney() == false) {
                while (validateMoney() == false && !input.equalsIgnoreCase("Exit")) {
                    displayInsufficientFunds();
                    selectItem();
                    if (input.equalsIgnoreCase("Exit")) {
                        break;
                    }
                }
           // }

            if (input.equalsIgnoreCase("Excalibur")) {
                tryExcalibur();

                if (input.equalsIgnoreCase("Arthur")) {
                    giveExcalibur();
                }

                if (!input.equalsIgnoreCase("Arthur")) {
                    denyExcalibur();
                    break;
                }
            }
            if (input.equalsIgnoreCase("Exit")) {
                break;
            } else {
                giveItem();
                getChange();
            }
            if (input.equalsIgnoreCase("no")) {
                break;
            }
        }
        displayExit();

    }

    private String displayWelcome() throws VendingMachineException {
        return view.displayWelcomeMenu(service.displayList());
    }

    private void selectItem() throws VendingMachineException {
        input = view.selectItem(service.displayList());
    }

    private void giveItem() throws VendingMachineException {
        try {
            view.printItem(service.dispenseItem(view.getSelectedItem()));
            service.updateItem(service.dispenseItem(view.getSelectedItem()));
        } catch (NoItemInventoryException e) {
            view.displayErrorMessage(e);
        }
    }

    private void collectMoney() throws VendingMachineException {
        view.collectMoney();
    }

    private boolean validateMoney() {
        try {
            service.validateMoney(view.getUserMoney(), service.dispenseItem(view.getSelectedItem()));

        } catch (InsufficientFundsException | NoItemInventoryException e) {
            view.displayErrorMessage(e);
            return false;
        }
        return true;
    }

    private void displayExit() {
        view.displayExit();
    }

    private void displayInsufficientFunds() throws VendingMachineException {
        view.displayInsufficientfunds();
    }

    private void getChange() throws VendingMachineException {
        Change change = new Change();
        change.getChange(view.getUserMoney());
        input = view.printChange(change.getQuarters(), change.getDimes(), change.getNickels(), change.getPennies());
    }

    private String tryExcalibur() throws VendingMachineException {
        input = view.tryExcalibur();
        return input;
    }

    private void denyExcalibur() throws VendingMachineException {
        view.denyExcalibur();
    }

    private void giveExcalibur() throws VendingMachineException {
        view.giveExcalibur();
    }
}
