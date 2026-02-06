public class AddTaskCommand extends Command {
    protected final Task task;

    public AddTaskCommand(Task task) {
        super(CommandType.ADD_TASK);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(task);
    }
}
