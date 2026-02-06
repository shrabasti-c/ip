public class UnmarkTaskCommand extends Command {
    protected final int currentTask;

    public UnmarkTaskCommand(int currentTask) {
        super(CommandType.UNMARK_TASK);
        this.currentTask = currentTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.unmarkTask(currentTask);
    }
}
