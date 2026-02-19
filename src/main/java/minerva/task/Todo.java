package minerva.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}