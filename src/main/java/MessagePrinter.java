public class MessagePrinter {
    private static final String LINE = "\n\t____________________________________________________________\n\t";

    public static void printGreeting() {
        String logo = "\t  __  __ _                            \n" +
                "\t  |  \\/  (_)                           \n" +
                "\t  | \\  / |_ _ __   ___ _ ____   ____ _ \n" +
                "\t  | |\\/| | | '_ \\ / _ \\ '__\\ \\ / / _` |\n" +
                "\t  | |  | | | | | |  __/ |   \\ V / (_| |\n" +
                "\t  |_|  |_|_|_| |_|\\___|_|    \\_/ \\__,_|\n";
        String greeting = LINE + "Greetings! I am\n"+ logo + "\n\tHow may I be of assistance?" + LINE;
        System.out.println(greeting);
    }

    public static void printGoodbye() {
        String goodbye = LINE + "Farewell, until our paths cross again!" + LINE;
        System.out.println(goodbye);
    }

    public static void printAddedMessage(Task task, int numTasks) {
        System.out.println(LINE + "Understood: I have added this task");
        System.out.println("\t\t" + task);
        System.out.println("\tNumber of pending tasks in list: " + numTasks + LINE);
    }

    public static void printMarkedMessage(Task task) {
        System.out.println(LINE + "Congratulations!\n\tmarked done: " + task + LINE);
    }

    public static void printUnmarkedMessage(Task task) {
        System.out.println(LINE + "Task pending...!\n\tmarked undone: " + task + LINE);
    }

    public static void printTaskList(Task[] tasks) {
        System.out.print(LINE);
        for(int i = 0; i < tasks.length; i++) {
            if (i!=0) System.out.print("\n\t");
            System.out.print((i+1) + ". "+tasks[i]);
        }
        System.out.println(LINE);
    }

    public static void printError(String message) {
        System.out.println(LINE + "\n\tError: " + message + LINE);
    }
}
