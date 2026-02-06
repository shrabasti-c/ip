public class OtherCommand extends Command {
    protected final String message;

    public OtherCommand(String message) {
        super(CommandType.OTHER);
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printError(message);
    }
}
