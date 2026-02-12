package minerva.data.chatbot;

import minerva.command.Command;
import minerva.data.exception.MinervaException;
import minerva.parser.Parser;
import minerva.task.TaskList;
import minerva.ui.Ui;

public class Chatbot {
    private final Ui ui = new Ui();
    private final TaskList tasks = new TaskList(ui);
    private final Parser commandParser = new Parser();

    private void assistUser() {
        String input;
        boolean isExitCommand = false;
        do {
            input = ui.readCommand();
            try {
                Command command = commandParser.parseCommand(input);
                command.execute(tasks, ui);
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
