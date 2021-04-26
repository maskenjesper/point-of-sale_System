package se.kth.iv1350.pos.startup;

import se.kth.iv1350.pos.view.View;

/**
 * This class is the entrypoint of the program, it contains the main method which is used to start the entire
 * application.
 */
public class Main {
    /**
     * The main method is the first to run and is used to initialize the application.
     * @param args The application does not receive any command line arguments.
     */
    public static void main(String[] args) {
        View view = new View();
    }
}
