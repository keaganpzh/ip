package rat.tasks;

import java.util.ArrayList;

import static rat.io.RatPrinter.*;

import rat.storage.RatStorage;

/**
 * This class encapsulates the taskList of Rat, which is a list of tasks.
 * Includes methods to add, modify, and delete tasks.
 * Handles writing to storage.
 *
 * @author Keagan
 */
public class RatTaskManager {

    /**
     * The list of tasks managed by RatTaskManager, represented by an ArrayList.
     */
    private final ArrayList<Task> taskList;

    private RatStorage storage;

    /**
     * Constructor for a RatTaskManager object.
     * Initialises the storage as an empty ArrayList.
     */
    public RatTaskManager() {
        taskList = new ArrayList<>();
    }

    public RatTaskManager(RatStorage storage) {
        taskList = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Returns the String representation of RatTaskManager.
     * The String representation is a numbered list of tasks.
     *
     * @return String representation of RatTaskManager.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            str.append((i + 1)).append(". ").append(this.taskList.get(i).toString()).append("\n");
        }
        return str.toString();
    }

    /**
     * Adds a ToDo task to the taskList, given its name.
     * Instantiates a new ToDo object and adds it to the taskList.
     *
     * @param item Name of the ToDo task.
     */
    public void addToDo(String item) {
        ToDo newToDo = new ToDo(item);
        this.taskList.add(newToDo);
        printWithLines("Got it. I've added this Deadline:\n"
                + newToDo
                + "\nNow you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Adds a Deadline task to the taskList, given its deadline and name.
     * Instantiates a new Deadline object and adds it to the taskList.
     *
     * @param deadline Deadline of the Deadline task.
     * @param name     Name of the Deadline task.
     */
    public void addDeadline(String deadline, String name) {
        Deadline newDeadline = new Deadline(deadline, name);
        this.taskList.add(newDeadline);
        String msg = "Got it. I've added this Deadline:\n"
                + newDeadline
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        printWithLines(msg);
    }

    /**
     * Adds an Event task to the taskList, given its start time, end time, and name.
     * Instantiates a new Event object and adds it to the taskList.
     *
     * @param startTime Start time of the Event task.
     * @param endTime   End time of the Event task.
     * @param name      Name of the Event task.
     */
    public void addEvent(String startTime, String endTime, String name) {
        Event newEvent = new Event(startTime, endTime, name);
        this.taskList.add(newEvent);
        String msg = "Got it. I've added this Event:\n"
                + newEvent
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        printWithLines(msg);
    }

    /**
     * Marks a task as done, given its 1-indexed index in the taskList.
     *
     * @param index Index of the task in the taskList.
     */
    public void markItemDone(int index) {
        if (index > this.taskList.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.taskList.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.taskList.get(index - 1);
        item.markDone();
        printWithLines("Nice! I've marked this task as done: " + taskList.get(index - 1).toString());
    }

    /**
     * Marks a task as not done, given its 1-indexed index in the taskList.
     *
     * @param index Index of the task in the taskList.
     */
    public void unmarkItemDone(int index) {
        if (index > this.taskList.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.taskList.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.taskList.get(index - 1);
        item.unmarkDone();
        printWithLines("Ok, I've marked this task as not done yet: " + taskList.get(index - 1).toString());
    }

    /**
     * Deletes a task from taskList, given its 1-indexed index in the taskList.
     *
     * @param index Index of the task in the taskList.
     */
    public void deleteItem(int index) {
        if (index > this.taskList.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.taskList.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        printWithLines("Noted. I've removed this task:\n"
                + item.toString()
                + "\nNow you have " + this.taskList.size() + " tasks in the list.");
    }

    /**
     * Deletes all tasks from taskList.
     */
    public void deleteAll() {
        this.taskList.clear();
        printWithLines("Noted. I've removed all tasks.");
    }

    /**
     * Displays all tasks in taskList.
     * Formats the taskList's String representation with a message and count.
     */
    public void listItems() {
        if (this.taskList.isEmpty()) {
            printWithLines("You have no tasks in the list.");
        } else {
            String list = "Here are the tasks in your list:\n"
                    + this + "\n"
                    + "You have " + this.taskList.size() + " tasks in the list.";
            printWithLines(list);
        }
    }

    public void save() {
        StringBuilder data = new StringBuilder();
        for (Task task : this.taskList) {
            data.append(task.formatForFile()).append("\n");
        }
        this.storage.overwriteFile(data.toString());
    }
}