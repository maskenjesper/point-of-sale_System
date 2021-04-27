package se.kth.iv1350.pos.view;

import se.kth.iv1350.pos.controller.Controller;

/**
 * A placeholder class to represent the interface between the application and user. Used to call system operations.
 */
public class View {
    private Controller controller;

    /**
     * The default constructor for a View that creates a Controller instance.
     */
    public View() {
        controller = new Controller();
    }

    public void sampleExecution() {
        controller.startSale();
        System.out.println("ADD ITEM WITH IDENTIFIER 1 QUANTITY 20:\n" + controller.addItemToSale(1, 20));
        System.out.println("ADD ITEM WITH IDENTIFIER 1 AGAIN QUANTITY 5:\n" + controller.addItemToSale(1, 5));
        System.out.println("ADD ITEM WITH IDENTIFIER 2 QUANTITY 20:\n" + controller.addItemToSale(2, 20));
        System.out.println("ADD ITEM WITH IDENTIFIER 3 (INVALID) QUANTITY 20:\n" + controller.addItemToSale(3, 20));
        System.out.println("END REGISTERING:\n" + controller.endRegistering());
        System.out.println("REQUEST DISCOUNT (INVALID CUSTOMERID):\n" + controller.discountRequest(124));
        System.out.println("REQUEST DISCOUNT:\n" + controller.discountRequest(123));
        System.out.println("ADD PAYMENT:\n" + controller.addPayment(2000));
    }
}
