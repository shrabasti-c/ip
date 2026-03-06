package minerva.data.chatbot;

import minerva.command.Command;
import minerva.data.exception.MinervaException;
import minerva.parser.Parser;
import minerva.storage.StorageFile;
import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents the main chatbot that interacts with the user.
 * <p>
 * The Chatbot handles reading user input, parsing commands, executing them,
 * and saving tasks to storage. It also manages the main conversation loop
 * and displays greetings and errors through the UI.
 * </p>
 */
public class Chatbot {
    private final Ui ui = new Ui();
    private TaskList tasks = new TaskList(ui);
    private final Parser commandParser = new Parser();
    private final StorageFile storage = new StorageFile();

    /**
     * Constructs a new Chatbot instance.
     */
    public Chatbot() {
        try {
            tasks = storage.load(ui);
        }
        catch (MinervaException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList(ui);
        }
    }

    /**
     * Handles the main user interaction loop.
     * <p>
     * Continuously reads commands from the user, parses and executes them,
     * updates the storage, and checks for the exit command.
     * Errors during command execution are displayed to the user.
     * </p>
     */
    private void assistUser() {
        String input;
        boolean isExitCommand = false;
        do {
            input = ui.readCommand();
            try {
                Command command = commandParser.parseCommand(input);
                command.execute(tasks, ui);
                storage.save(tasks);
                isExitCommand = command.isExitCommand();
            } catch (MinervaException e) {
                ui.printError(e.getMessage());
            }
        } while (!isExitCommand);
    }

    /**
     * Starts the Chatbot application.
     * <p>
     * Prints a greeting to the user and begins the main command loop.
     * </p>
     */
    public void run() {
        ui.printGreeting();
        assistUser();
    }
}
