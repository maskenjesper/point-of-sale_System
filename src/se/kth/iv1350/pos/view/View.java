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
        controller.addItemToSale(1, 20);
        controller.addItemToSale(1, 20);
        controller.addItemToSale(1, 5);
        controller.addItemToSale(2, 20);
        System.out.println(controller.endRegistering());
    }
}
