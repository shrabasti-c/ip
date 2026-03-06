package minerva.task;

/**
 * Represents a general task in the Minerva application.
 *
 * <p>A {@code Task} contains a description, a completion status, and a type.
 * Subclasses provide specific behavior for different task types, such as Todo,
 * Deadline, and Event.</p>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected final TaskType type;

    /**
     * Constructs a Task with the specified description and type.
     * The task is initially not completed.
     *
     * @param description the task description
     * @param type the {@link TaskType} of the task
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Constructs a Task with the specified description, completion status, and type.
     *
     * @param description the task description
     * @param isDone whether the task is completed
     * @param type the {@link TaskType} of the task
     */
    public Task(String description, boolean isDone, TaskType type) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Returns a string representing the completion status of the task.
     *
     * @return "X" if done, otherwise a blank space
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task for display.
     *
     * @return a formatted string including status and description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public abstract String toFileFormat();
}
