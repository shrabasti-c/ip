/**
 * Provides the user interface for the Minerva task management application.
 * <p>
 * This class handles all interactions with the user through the command line,
 * including reading user input and displaying formatted messages such as
 * greetings, task updates, errors, and task lists.
 * </p>
 *
 * <p>
 * The {@code Ui} class is responsible only for presentation and input,
 * while task logic is handled by other components such as {@link minerva.task.Task}.
 * </p>
 */
package minerva.ui;
import minerva.task.Task;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles input and output operations for the Minerva application.
 */
public class Ui {
    /** Scanner used to read user input from standard input. */
    private final Scanner in;

    /** Decorative separator used for formatted console output. */
    private static final String LINE = "\n\t____________________________________________________________\n\t";

    /** ASCII logo displayed in the greeting message. */
    private static final String LOGO = "\t  __  __ _                            \n" +
            "\t  |  \\/  (_)                           \n" +
            "\t  | \\  / |_ _ __   ___ _ ____   ____ _ \n" +
            "\t  | |\\/| | | '_ \\ / _ \\ '__\\ \\ / / _` |\n" +
            "\t  | |  | | | | | |  __/ |   \\ V / (_| |\n" +
            "\t  |_|  |_|_|_| |_|\\___|_|    \\_/ \\__,_|\n";

    /** Greeting message shown when the program starts. */
    private static final String GREETING = "Greetings! I am\n"+ LOGO + "\n\tHow may I be of assistance?";

    /** Farewell message shown when the program terminates. */
    private static final String GOODBYE = "Farewell, until our paths cross again!";

    /** Message displayed when a task is added. */
    private static final String MESSAGE_ADDED = "Understood: I have added this task\n\t\t";

    /** Message displayed when a task is deleted. */
    private static final String MESSAGE_DELETED = "Your folly is undone: I have deleted this task\n\t\t";

    /** Message displaying the number of tasks remaining. */
    private static final String MESSAGE_PENDING_TASKS = "\n\tNumber of pending tasks in list: ";

    /** Message displayed when a task is marked as completed. */
    private static final String MESSAGE_MARKED = "Congratulations!\n\tmarked done: ";

    /** Message displayed when a task is marked as not completed. */
    private static final String MESSAGE_UNMARKED = "Task pending...!\n\tmarked undone: ";

    /** Message displayed when matching tasks are found during a search. */
    private static final String MESSAGE_MATCHING_TASKS =
            "\n\tBehold! I have found matching tasks in your list\n\t";

    /** Message displayed when no matching tasks are found. */
    private static final String MESSAGE_NO_MATCHING_TASKS =
            "Alas! I have found no matching tasks in your list";

    /** Generic error message prefix. */
    private static final String MESSAGE_ERROR =  "\n\tYou err in your ways! ";

    /**
     * Constructs a {@code Ui} object with a scanner for reading user input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Reads a command entered by the user.
     *
     * @return the trimmed user input string
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Prints the greeting message to the user.
     */
    public void printGreeting() {
        printWithFormatting(GREETING);
    }

    /**
     * Prints the goodbye message when the application exits.
     */
    public void printGoodbye() {
        printWithFormatting(GOODBYE);
    }

    /**
     * Prints a confirmation message when a task is added.
     *
     * @param task the task that was added
     * @param numTasks the total number of tasks in the list
     */
    public void printAddedMessage(Task task, int numTasks) {
        printWithFormatting(MESSAGE_ADDED + task + MESSAGE_PENDING_TASKS + numTasks);
    }

    /**
     * Prints a confirmation message when a task is deleted.
     *
     * @param task the task that was deleted
     * @param numTasks the total number of tasks remaining
     */
    public void printDeletedMessage(Task task, int numTasks) {
        printWithFormatting(MESSAGE_DELETED + task + MESSAGE_PENDING_TASKS + numTasks);
    }

    /**
     * Prints a confirmation message when a task is marked as completed.
     *
     * @param task the task that was marked
     */
    public void printMarkedMessage(Task task) {
        printWithFormatting(MESSAGE_MARKED + task);
    }

    /**
     * Prints a confirmation message when a task is marked as not completed.
     *
     * @param task the task that was unmarked
     */
    public void printUnmarkedMessage(Task task) {
        printWithFormatting(MESSAGE_UNMARKED + task);
    }

    /**
     * Prints a list of tasks.
     *
     * @param tasks the list of tasks to display
     * @param isFullList true if displaying the entire task list,
     *                   false if displaying only matching tasks
     */
    public void printTaskList(ArrayList<Task> tasks, boolean isFullList) {
        StringBuilder taskList = extractTaskList(tasks);
        if (!isFullList) {
            if (taskList.isEmpty()) {
                printWithFormatting(MESSAGE_NO_MATCHING_TASKS);
            } else {
                printWithFormatting(MESSAGE_MATCHING_TASKS + String.valueOf(taskList).trim());
            }
        } else {
            printWithFormatting(String.valueOf(taskList).trim());
        }
    }

    /**
     * Converts a list of tasks into a formatted string representation.
     *
     * @param tasks the list of tasks
     * @return a {@code StringBuilder} containing the formatted task list
     */
    private static StringBuilder extractTaskList(ArrayList<Task> tasks) {
        StringBuilder taskList = new StringBuilder();
        for(int i = 0; i < tasks.size(); i++) {
            taskList.append("\t").append(i+1).append(". ").append(tasks.get(i)).append("\n");
        }
        return taskList;
    }

    /**
     * Prints an error message to the user.
     *
     * @param message the specific error message
     */
    public void printError(String message) {
        printWithFormatting(MESSAGE_ERROR + message);
    }

    /**
     * Prints a message surrounded by formatting lines.
     *
     * @param message the message to display
     */
    public void printWithFormatting(String message) {
        System.out.println(LINE + message + LINE);
    }
}
