package minerva.commands;
import minerva.data.exception.MinervaException;
import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents a command that deletes a task from the {@link TaskList}.
 *
 * <p>When executed, the {@code DeleteTaskCommand} removes the task at the specified
 * index from the task list and triggers UI messages accordingly.</p>
 */
public class DeleteTaskCommand extends Command {
    protected final int currentTask;

    /**
     * Constructs a {@code DeleteTaskCommand} for the task at the specified index.
     *
     * @param currentTask the index of the task to delete (0-based)
     */
    public DeleteTaskCommand(int currentTask) {
        super(CommandType.DELETE_TASK);
        this.currentTask = currentTask;
    }

    /**
     * Executes the delete task command on the given {@link TaskList}.
     *
     * @param tasks the task list from which the task will be deleted
     * @param ui the user interface to display messages
     * @throws MinervaException if an error occurs while deleting the task
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        tasks.deleteTask(currentTask);
    }
}
