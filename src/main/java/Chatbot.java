import java.util.*;
public class Chatbot {
    private final Scanner in = new Scanner(System.in);
    private final TaskList tasks = new TaskList();

    private void assistUser() {
        String command;
        do {
            command = in.nextLine().trim();
            CommandParser.evaluateAndExecuteCommand(command, tasks);
        } while (!CommandParser.isExitCommand(command));
    }

    public void run() {
        MessagePrinter.printGreeting();
        assistUser();
        MessagePrinter.printGoodbye();
    }
}
