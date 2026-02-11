public class MarkTaskCommand extends Command {
    protected final int currentTask;

    public MarkTaskCommand(int currentTask) {
        super(CommandType.MARK_TASK);
        this.currentTask = currentTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        tasks.markTask(currentTask);
    }
}
