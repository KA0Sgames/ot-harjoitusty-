package ui;

/**
 * Main class to go around problems with JavaFX.
 */

public class Main {
    
    /**
     * Separate main classes main method calling Ui's main method to avoid problems with JavaFX.
     * @param args String table used by main methods.
     */
    public static void main(String[] args) {
        Ui.main(args);
    }
}
