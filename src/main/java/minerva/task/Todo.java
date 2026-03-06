package minerva.task;

/**
 * Represents a simple todo task in the Minerva application.
 *
 * <p>A {@code Todo} contains only a description and a completion status,
 * without any associated date or time.</p>
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the given description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Constructs a {@code Todo} task with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone whether the task has been completed
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    /**
     * Returns the string representation of the todo task for display.
     *
     * @return a formatted string representing the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the file storage format of the todo task.
     *
     * @return a string formatted for saving the task to a file
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}