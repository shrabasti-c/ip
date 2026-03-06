package minerva.command;

import minerva.data.exception.MinervaException;
import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents a command that marks a task in the {@link TaskList} as not completed.
 *
 * <p>When executed, the {@code UnmarkTaskCommand} updates the status of the task
 * at the specified index to undone and triggers UI messages accordingly.</p>
 */
public class UnmarkTaskCommand extends Command {
    protected final int currentTask;

    /**
     * Constructs an {@code UnmarkTaskCommand} for the task at the specified index.
     *
     * @param currentTask the index of the task to mark as not done (0-based)
     */
    public UnmarkTaskCommand(int currentTask) {
        super(CommandType.UNMARK_TASK);
        this.currentTask = currentTask;
    }

    /**
     * Executes the unmark task command on the given {@link TaskList} using the provided {@link Ui}.
     *
     * @param tasks the task list containing the task to unmark
     * @param ui the user interface to display messages
     * @throws MinervaException if an error occurs while unmarking the task
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        tasks.unmarkTask(currentTask);
    }
}
