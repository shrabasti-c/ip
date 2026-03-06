package minerva.command;

import minerva.data.exception.MinervaException;
import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents a command that searches for tasks in the {@link TaskList} containing a keyword.
 *
 * <p>When executed, the {@code FindCommand} retrieves all tasks whose descriptions
 * contain the specified keyword and displays them using the {@link Ui}.</p>
 */
public class FindCommand extends Command {
    protected final String task;

    /**
     * Constructs a {@code FindCommand} with the specified search keyword.
     *
     * @param task the keyword to search for in task descriptions
     */
    public FindCommand(String task) {
        super(CommandType.FIND_TASK);
        this.task = task;
    }

    /**
     * Constructs a {@code FindCommand} with the specified search keyword.
     *
     * @param task the keyword to search for in task descriptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        ui.printTaskList(tasks.findTask(task), false);
    }
}
