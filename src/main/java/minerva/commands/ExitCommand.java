package minerva.commands;

import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents a command that exits the Minerva application.
 *
 * <p>When executed, the {@code ExitCommand} prints a farewell message
 * using the {@link Ui} and signals the application to terminate.</p>
 */
public class ExitCommand extends Command {
    /**
     * Constructs an {@code ExitCommand}.
     */
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    /**
     * Executes the exit command by displaying a goodbye message.
     *
     * @param tasks the task list (not used in this command)
     * @param ui the user interface to display the farewell message
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printGoodbye();
        return;
    }
}
