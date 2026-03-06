package minerva.command;

import minerva.data.exception.MinervaException;
import minerva.task.Task;
import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents a command that adds a task to the {@link TaskList}.
 *
 * <p>When executed, the {@code AddTaskCommand} adds the specified {@link Task}
 * to the task list and triggers UI messages accordingly.</p>
 */
public class AddTaskCommand extends Command {
    protected final Task task;

    /**
     * Constructs an {@code AddTaskCommand} with the specified task to add.
     *
     * @param task the {@link Task} to add to the task list
     */
    public AddTaskCommand(Task task) {
        super(CommandType.ADD_TASK);
        this.task = task;
    }

    /**
     * Executes the add task command on the given {@link TaskList} using the provided {@link Ui}.
     *
     * @param tasks the task list to which the task will be added
     * @param ui the user interface to display messages
     * @throws MinervaException if an error occurs while adding the task
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        tasks.addTask(task);
    }
}
