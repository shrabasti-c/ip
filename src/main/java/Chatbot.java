public class Chatbot {
    private final Ui ui = new Ui();
    private final TaskList tasks = new TaskList(ui);
    Parser commandParser = new Parser(ui);

    private void assistUser() {
        String input;
        boolean isExitCommand = false;
        do {
            input = ui.readCommand();
            Command command = commandParser.parseCommand(input);
            command.execute(tasks, ui);
            isExitCommand = command.isExitCommand();
        } while (!isExitCommand);
    }

    public void run() {
        ui.printGreeting();
        assistUser();
    }
}
