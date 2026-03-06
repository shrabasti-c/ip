package minerva.command;

import minerva.data.exception.MinervaException;
import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents a command that marks a task in the {@link TaskList} as completed.
 *
 * <p>When executed, the {@code MarkTaskCommand} updates the status of the task
 * at the specified index to done and triggers UI messages accordingly.</p>
 */
public class MarkTaskCommand extends Command {
    protected final int currentTask;

    /**
     * Constructs a {@code MarkTaskCommand} for the task at the specified index.
     *
     * @param currentTask the index of the task to mark as done (0-based)
     */
    public MarkTaskCommand(int currentTask) {
        super(CommandType.MARK_TASK);
        this.currentTask = currentTask;
    }

    /**
     * Executes the mark task command on the given {@link TaskList}.
     *
     * @param tasks the task list containing the task to mark
     * @param ui the user interface to display messages
     * @throws MinervaException if an error occurs while marking the task
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        tasks.markTask(currentTask);
    }
}
