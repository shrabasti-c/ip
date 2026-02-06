import java.util.Scanner;

public class Ui {
    private final Scanner in;

    private static final String LINE = "\n\t____________________________________________________________\n\t";

    private static final String LOGO = "\t  __  __ _                            \n" +
            "\t  |  \\/  (_)                           \n" +
            "\t  | \\  / |_ _ __   ___ _ ____   ____ _ \n" +
            "\t  | |\\/| | | '_ \\ / _ \\ '__\\ \\ / / _` |\n" +
            "\t  | |  | | | | | |  __/ |   \\ V / (_| |\n" +
            "\t  |_|  |_|_|_| |_|\\___|_|    \\_/ \\__,_|\n";

    private static final String GREETING = "Greetings! I am\n"+ LOGO + "\n\tHow may I be of assistance?";
    private static final String GOODBYE = "Farewell, until our paths cross again!";

    private static final String MESSAGE_ADDED = "Understood: I have added this task\n\t\t";
    private static final String MESSAGE_PENDING_TASKS = "\n\tNumber of pending tasks in list: ";
    private static final String MESSAGE_MARKED = "Congratulations!\n\tmarked done: ";
    private static final String MESSAGE_UNMARKED = "Task pending...!\n\tmarked undone: ";
    private static final String MESSAGE_ERROR =  "\n\tError: ";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void printGreeting() {
        printWithFormatting(GREETING);
    }

    public void printGoodbye() {
        printWithFormatting(GOODBYE);
    }

    public void printAddedMessage(Task task, int numTasks) {
        printWithFormatting(MESSAGE_ADDED + task + MESSAGE_PENDING_TASKS + numTasks);
    }

    public void printMarkedMessage(Task task) {
        printWithFormatting(MESSAGE_MARKED + task);
    }

    public void printUnmarkedMessage(Task task) {
        printWithFormatting(MESSAGE_UNMARKED + task);
    }

    public void printTaskList(Task[] tasks) {
        StringBuilder taskList = new StringBuilder();
        for(int i = 0; i < tasks.length; i++) {
            taskList.append("\t").append(i+1).append(". ").append(tasks[i]).append("\n");
        }
        printWithFormatting(String.valueOf(taskList).trim());
    }

    public void printError(String message) {
        printWithFormatting(MESSAGE_ERROR + message);
    }

    public void printWithFormatting(String message) {
        System.out.println(LINE + message + LINE);
    }
}
