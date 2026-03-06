package minerva.task;

/**
 * Represents a deadline task in the Minerva application.
 *
 * <p>A {@code Deadline} has a description, a completion status, and a due date or time.
 * It is used to track tasks that must be completed by a specific deadline.</p>
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a {@code Deadline} task with a description and a due time.
     * The task is initially not completed.
     *
     * @param description the task description
     * @param by the due time or date of the task
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Constructs a {@code Deadline} task with a description, completion status, and due time.
     *
     * @param description the task description
     * @param isDone whether the task has been completed
     * @param by the due time or date of the task
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline for display.
     *
     * @return a formatted string including status, description, and deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the file storage format of the deadline task.
     *
     * @return a string formatted for saving the deadline to a file
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
