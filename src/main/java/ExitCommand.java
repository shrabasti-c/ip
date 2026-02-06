public class ExitCommand extends Command {
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printGoodbye();
        return;
    }
}
