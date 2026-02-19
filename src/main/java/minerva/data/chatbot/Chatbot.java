package minerva.data.chatbot;

import minerva.command.Command;
import minerva.data.exception.MinervaException;
import minerva.parser.Parser;
import minerva.storage.StorageFile;
import minerva.task.TaskList;
import minerva.ui.Ui;

public class Chatbot {
    private final Ui ui = new Ui();
    private TaskList tasks = new TaskList(ui);
    private final Parser commandParser = new Parser();
    private final StorageFile storage = new StorageFile();

    public Chatbot() {
        try {
            tasks = storage.load(ui);
        }
        catch (MinervaException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList(ui);
        }
    }

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

    public void run() {
        ui.printGreeting();
        assistUser();
    }
}
