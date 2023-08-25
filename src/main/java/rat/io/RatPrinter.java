package rat.io;

/**
 * This class encapsulates the printing of messages by Rat.
 * @author Keagan
 */
public class RatPrinter {

    /**
     * Prints the welcome message to the user.
     */
    public static void printWelcome() {
        String welcomeMsg = "                  Hello! I'm Rat!\n"
                + "              What can I do for you?\n"
                + "       type \"help\" to see a list of commands";
        printWithLines(welcomeMsg);
    }

    /**
     * Prints the exit message to the user.
     */
    public static void printExit() {
        String exitMsg = "           Bye. Hope to see you again soon!";
        printWithLines(exitMsg);
    }

    /**
     * Given a message, formats the message with lines and prints it to the user.
     * @param msg The message to be printed.
     */
    public static void printWithLines(String msg) {
        String output = "============================================================\n"
                + msg + "\n"
                + "============================================================\n";
        System.out.println(output);
    }

}
