package minerva.task;

import minerva.data.exception.MinervaException;
import minerva.ui.Ui;

import java.util.ArrayList;
public class TaskList {
    private final Ui ui;

    private final ArrayList<Task> tasks = new ArrayList<>();
    public static final String ERROR_NONEXISTENT_TASK = "Perform action on existing task only.";

    public TaskList(Ui ui) {
        this.ui = ui;
    }

    public void addTask(Task t) throws MinervaException {
        tasks.add(t);
        ui.printAddedMessage(t, tasks.size());
    }

    public void deleteTask(int currentTask) throws MinervaException {
        if (currentTask < 0 || currentTask >= tasks.size()) {
            throw new MinervaException(ERROR_NONEXISTENT_TASK);
        }
        Task t = tasks.remove(currentTask);
        ui.printDeletedMessage(t, tasks.size());
    }

    public void markTask(int currentTask) throws MinervaException{
        if (currentTask < 0 || currentTask >= tasks.size()) {
            throw new MinervaException(ERROR_NONEXISTENT_TASK);
        }
        tasks.get(currentTask).markDone();
        ui.printMarkedMessage(tasks.get(currentTask));
    }

    public void unmarkTask(int currentTask) throws MinervaException {
        if (currentTask < 0 || currentTask >= tasks.size()) {
            throw new MinervaException(ERROR_NONEXISTENT_TASK);
        }
        tasks.get(currentTask).unmarkDone();
        ui.printUnmarkedMessage(tasks.get(currentTask));
    }

    public ArrayList<Task> getTasks() {
        return tasks;
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
