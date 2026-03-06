package minerva.command;

import minerva.data.exception.MinervaException;
import minerva.task.TaskList;
import minerva.ui.Ui;

/**
 * Represents an executable command in the Minerva application.
 *
 * <p>Each command encapsulates a specific action to perform on the {@link TaskList}.
 * Subclasses implement the {@link #execute(TaskList, Ui)} method to define behavior.</p>
 */
public abstract class Command {
    protected final CommandType type;
    /**
     * Constructs a {@code Command} with the specified {@link CommandType}.
     *
     * @param type the type of the command
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Determines whether this command is an exit command.
     *
     * @return {@code true} if the command type is {@link CommandType#EXIT}, otherwise {@code false}
     */
    public boolean isExitCommand() {
        return this.type == CommandType.EXIT;
    }

    /**
     * Executes the command using the provided {@link TaskList} and {@link Ui}.
     *
     * <p>Subclasses must implement this method to define the command's behavior.</p>
     *
     * @param tasks the {@link TaskList} to operate on
     * @param ui the {@link Ui} for displaying messages
     * @throws MinervaException if an error occurs during command execution
     */
    public abstract void execute(TaskList tasks, Ui ui) throws MinervaException;
}
