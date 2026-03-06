package minerva.command;

/**
 * Represents the types of commands available in the Minerva application.
 *
 * <p>This enum is used by {@link Command} objects to determine their behavior
 * and for checking command types (e.g., exit commands).</p>
 */
public enum CommandType {
    EXIT, LIST_TASKS, MARK_TASK, UNMARK_TASK, ADD_TASK, DELETE_TASK, FIND_TASK
}
