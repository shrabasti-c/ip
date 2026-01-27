import java.util.*;
public class Chatbot {

    private final String LINE = "\n____________________________________________________________\n";
    private final Scanner in = new Scanner(System.in);
    private String command;

    private void printGreeting() {
        String logo = "  __  __ _                            \n" +
                " |  \\/  (_)                           \n" +
                " | \\  / |_ _ __   ___ _ ____   ____ _ \n" +
                " | |\\/| | | '_ \\ / _ \\ '__\\ \\ / / _` |\n" +
                " | |  | | | | | |  __/ |   \\ V / (_| |\n" +
                " |_|  |_|_|_| |_|\\___|_|    \\_/ \\__,_|\n";
        String greeting = LINE + "Greetings! I am\n"+ logo + "\nHow may I be of assistance?" + LINE;
        System.out.println(greeting);
    }

    private void printGoodbye() {
        String goodbye = LINE + "Farewell, until our paths cross again!" + LINE;
        System.out.println(goodbye);
    }

    private boolean isExitCommand() {
        return command.equalsIgnoreCase("bye");
    }

    private void echo() {
        System.out.println(LINE + command + LINE);
    }

    private void assistUser() {
        while (true) {
            command = in.nextLine();
            if (isExitCommand())
                return;
            echo();
        }
    }
    public void run() {
        printGreeting();
        assistUser();
        printGoodbye();
    }
}
