package minerva.task;

/**
 * Represents an event task in the Minerva application.
 *
 * <p>An {@code Event} has a description, a completion status, a start time, and an end time.
 * It is used to track tasks that occur within a specific time period.</p>
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an {@code Event} task with a description, start time, and end time.
     * The task is initially not completed.
     *
     * @param description the task description
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an {@code Event} task with a description, completion status, start time, and end time.
     *
     * @param description the task description
     * @param isDone whether the task has been completed
     * @param from the start time of the event
     * @param to the end time of the event
     */

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event for display.
     *
     * @return a formatted string including status, description, and time range
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }


    /**
     * Returns the file storage format of the event task.
     *
     * @return a string formatted for saving the event to a file
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + "-" + to;
    }
}

