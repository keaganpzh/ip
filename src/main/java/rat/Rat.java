package rat;

import java.util.Scanner;

import static rat.io.RatPrinter.*;

import rat.tasks.*;
import rat.io.RatInput;
import rat.storage.RatStorage;

/**
 * This class encapsulates my version of Duke, called Rat.
 * Rat is a chatbot that helps the user keep track of their tasks.
 *
 * @author Keagan
 * @version Week-2
 */
public class Rat {

    private static RatStorage ratStorage;

    /**
     * The RatTaskManager object used to store and process the user's tasks.
     */
    private static RatTaskManager ratTaskManager;

    /**
     * The RatInput Object used to process user input.
     */
    private static RatInput ratInput;

    /**
     * The Scanner object used to read user input.
     */
    private static Scanner sc;

    /**
     * Initialises the Rat program.
     * Instantiates a RatTaskManager object, Scanner object, and a RatInput Object.
     */
    public static void initialise() {
        printWelcome();
        ratStorage = new RatStorage("data/rat.txt");
        ratTaskManager = new RatTaskManager(ratStorage);
        sc = new Scanner(System.in);
        ratInput = new RatInput(sc, ratTaskManager);
    }

    /**
     * The main method of Rat.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Rat.initialise();
        ratInput.handleInput();
        sc.close();
    }
}


