package minerva.command;

import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents a command that prints all tasks in the {@link TaskList}.
 *
 * <p>When executed, the {@code PrintListCommand} displays the full list of tasks
 * using the {@link Ui}.</p>
 */
public class PrintListCommand extends Command {
    /**
     * Constructs a {@code PrintListCommand}.
     */
    public PrintListCommand() {
        super(CommandType.LIST_TASKS);
    }

    /**
     * Executes the print list command by displaying all tasks in the {@link TaskList}.
     *
     * @param tasks the task list to display
     * @param ui the user interface to show the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printTaskList(tasks.getTasks(), true);
    }
}
