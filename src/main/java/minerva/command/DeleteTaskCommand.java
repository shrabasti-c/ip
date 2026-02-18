package minerva.command;
import minerva.data.exception.MinervaException;
import minerva.task.TaskList;
import minerva.ui.Ui;

public class DeleteTaskCommand extends Command {
    protected final int currentTask;

    public DeleteTaskCommand(int currentTask) {
        super(CommandType.DELETE_TASK);
        this.currentTask = currentTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        tasks.deleteTask(currentTask);
    }
}
