public abstract class Command {
    protected final CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public boolean isExitCommand() {
        return this.type == CommandType.EXIT;
    }

    public abstract void execute(TaskList tasks, Ui ui);
}
