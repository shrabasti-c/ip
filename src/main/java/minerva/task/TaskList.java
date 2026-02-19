package minerva.task;

import minerva.data.exception.MinervaException;
import minerva.ui.Ui;

import java.util.Arrays;
public class TaskList {
    private final Ui ui;

    public static final int MAX_TASKS = 100;
    private final Task[] tasks = new Task[MAX_TASKS];
    private int numTasks = 0;

    public static final String ERROR_OVERFLOW = "Cannot add more tasks.";
    public static final String ERROR_NONEXISTENT_TASK = "Perform action on existing task only.";

    public TaskList(Ui ui) {
        this.ui = ui;
    }

    public void addTask(Task t) throws MinervaException {
        if (numTasks >= MAX_TASKS) {
            throw new MinervaException(ERROR_OVERFLOW);
        }
        tasks[numTasks++] = t;
        ui.printAddedMessage(t, numTasks);
    }

    public void markTask(int currentTask) throws MinervaException{
        if (currentTask < 0 || currentTask >= numTasks) {
            throw new MinervaException(ERROR_NONEXISTENT_TASK);
        }
        tasks[currentTask].markDone();
        ui.printMarkedMessage(tasks[currentTask]);
    }

    public void unmarkTask(int currentTask) throws MinervaException {
        if (currentTask < 0 || currentTask >= numTasks) {
            throw new MinervaException(ERROR_NONEXISTENT_TASK);
        }
        tasks[currentTask].unmarkDone();
        ui.printUnmarkedMessage(tasks[currentTask]);
    }

    public Task[] getTasks() {
        return Arrays.copyOf(tasks, numTasks);
    }

    public Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new IllegalArgumentException();
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        return switch (type) {
            case "T" -> new Todo(description, isDone);
            case "D" -> {
                if (parts.length < 4) throw new IllegalArgumentException();
                yield new Deadline(description, isDone, parts[3]);
            }
            case "E" -> {
                if (parts.length < 4) throw new IllegalArgumentException();
                String from = parts[3].substring(0, parts[3].indexOf("-"));
                String to = parts[3].substring(parts[3].indexOf("-") + 1);
                yield new Event(description, isDone, from, to);
            }
            default -> throw new IllegalArgumentException();
        };
    }
}
