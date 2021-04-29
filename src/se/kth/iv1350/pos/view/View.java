package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.DTO.SaleDTO;
import se.kth.iv1350.pos.controller.Controller;

/**
 * A placeholder class to represent the interface between the application and user. Used to call system operations.
 */
public class View {
    private Controller controller;

    /**
     * The default constructor for a View that creates a Controller instance and stores a reference to it.
     */
    public View() {
        controller = new Controller();
    }

    /**
     * A method that calls all the sysops in appropriate order and with variations to showcase the programs
     * functionality.
     */
    public void sampleExecution() { // Har jag tänkt rätt här? Ska man bara hårdkoda på detta vis eller måste man ta input o grejer?
        SaleDTO saleDTO;

        System.out.println("START SALE:\n");
        controller.startSale();

        System.out.println("ADD ITEM WITH IDENTIFIER 1 QUANTITY 20:");
        saleDTO = controller.addItemToSale(1, 20);
        if (saleDTO != null)
            System.out.println("Item added: " + saleDTO.getItemTable().getLastItemInTable().getName() +
                    "\n" + saleDTO.getItemTable().getLastItemInTable().getDescription() +
                    "\nRunning total: " + saleDTO.getItemTable().getRunningTotal() + "\n");
        else
            System.out.println("Invalid identifier");

        System.out.println("ADD ITEM WITH IDENTIFIER 1 AGAIN QUANTITY 5:");
        saleDTO = controller.addItemToSale(1, 5);
        if (saleDTO != null)
            System.out.println("Item added: " + saleDTO.getItemTable().getLastItemInTable().getName() +
                    "\n" + saleDTO.getItemTable().getLastItemInTable().getDescription() +
                    "\nRunning total: " + saleDTO.getItemTable().getRunningTotal() + "\n");
        else
            System.out.println("Invalid identifier");

        System.out.println("ADD ITEM WITH IDENTIFIER 2 QUANTITY 20:");
        saleDTO = controller.addItemToSale(2, 20);
        if (saleDTO != null)
            System.out.println("Item added: " + saleDTO.getItemTable().getLastItemInTable().getName() +
                    "\n" + saleDTO.getItemTable().getLastItemInTable().getDescription() +
                    "\nRunning total: " + saleDTO.getItemTable().getRunningTotal() + "\n");
        else
            System.out.println("Invalid identifier");

        System.out.println("ADD ITEM WITH IDENTIFIER 3 (INVALID) QUANTITY 20:");
        saleDTO = controller.addItemToSale(3, 20);
        if (saleDTO != null)
            System.out.println("Item added: " + saleDTO.getItemTable().getLastItemInTable().getName() +
                    "\n" + saleDTO.getItemTable().getLastItemInTable().getDescription() +
                    "\nRunning total: " + saleDTO.getItemTable().getRunningTotal() + "\n");
        else
            System.out.println("Invalid identifier");
/*
        System.out.println("END REGISTERING:");
        controller.endRegistering();

        System.out.println("REQUEST DISCOUNT (INVALID CUSTOMERID):");
        controller.discountRequest(124);

        System.out.println("REQUEST DISCOUNT:");
        controller.discountRequest(123);

        System.out.println("ADD PAYMENT:");
        controller.addPayment(2000);*/

    }
}
