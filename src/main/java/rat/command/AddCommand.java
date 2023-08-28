package rat.command;

import static rat.io.RatPrinter.printWithLines;

import java.text.ParseException;

import rat.tasks.RatTaskManager;

/**
 * This class encapsulates a command that adds a task to the task list.
 */
public class AddCommand extends RatCommand {

    /**
     * The type of task to be added.
     */
    private final CommandType commandType;

    /**
     * The user input passed from RatInput that contains details of the task to be added.
     */
    private final String input;

    /**
     * Constructor for a AddCommand object.
     * @param ratTaskManager The RatTaskManager object used to store and process the user's tasks.
     * @param input The user input passed from RatInput that contains details of the task to be added.
     * @param commandType The type of task to be added.
     */
    public AddCommand(RatTaskManager ratTaskManager, String input, CommandType commandType) {
        super(ratTaskManager);
        this.input = input;
        this.commandType = commandType;
    }

    /**
     * Adds a ToDo task to the task list.
     * @param params The user input passed from RatInput that contains details of the task to be added.
     */
    private void addToDo(String params) {
        try {
            params = params.substring(5);
            this.ratTaskManager.addToDo(params);
        } catch (StringIndexOutOfBoundsException e) {
            printWithLines("To Do name cannot be empty");
        }
    }

    /**
     * Adds a Deadline task to the task list.
     * @param params The user input passed from RatInput that contains details of the task to be added.
     */
    private void addDeadline(String params) {
        try {
            params = params.substring(9);
            String[] paramsArr = params.split(" /by ");
            String name = paramsArr[0];
            String deadline = paramsArr[1];
            this.validateTime(deadline);
            this.ratTaskManager.addDeadline(deadline, name);
        } catch (StringIndexOutOfBoundsException e) {
            printWithLines("Deadline name cannot be empty");
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithLines("Invalid deadline format. Please use \"deadline <name> /by <deadline>\"");
        } catch (ParseException e) {
            printWithLines("Invalid date format. Please use \"dd/MM/yyyy HH:mm\"");
        }
    }

    /**
     * Adds an Event task to the task list.
     * @param params The user input passed from RatInput that contains details of the task to be added.
     */
    private void addEvent(String params) {
        try {
            params = params.substring(6);
            String eventName = params.split(" /from ")[0];
            if (eventName.isEmpty()) {
                printWithLines("Event name cannot be empty");
            }
            String[] time = params.split(" /from ")[1].split(" /to ");
            String startTime = time[0];
            String endTime = time[1];
            this.validateTime(startTime);
            this.validateTime(endTime);
            this.ratTaskManager.addEvent(startTime, endTime, eventName);
        } catch (StringIndexOutOfBoundsException e) {
            printWithLines("Event name cannot be empty");
        } catch (ArrayIndexOutOfBoundsException e) {
            printWithLines("Invalid event format. Please use \"event <name> /from <start> /to <end>\"");
        } catch (ParseException e) {
            printWithLines("Invalid date format. Please use \"dd/MM/yyyy HH:mm\"");
        }
    }

    /**
     * Executes the command.
     */
    @Override
    public void execute() {
        switch (commandType) {
        case TODO:
            this.addToDo(input);
            break;
        case DEADLINE:
            this.addDeadline(input);
            break;
        case EVENT:
            this.addEvent(input);
            break;
        default:
            break;
        }
    }
}
