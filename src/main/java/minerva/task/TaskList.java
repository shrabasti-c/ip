package minerva.task;

import minerva.data.exception.MinervaException;
import minerva.ui.Ui;

import java.util.ArrayList;

/**
 * Manages the collection of tasks in the Minerva application.
 *
 * <p>Handles adding, deleting, marking, unmarking, searching, and
 * parsing tasks from a file format. Provides integration with the UI
 * to display messages for task operations.</p>
 */
public class TaskList {
    private final Ui ui;

    private final ArrayList<Task> tasks = new ArrayList<>();
    public static final String ERROR_NONEXISTENT_TASK = "Perform action on existing task only.";

    /**
     * Constructs a TaskList with a reference to the UI.
     *
     * @param ui the {@link Ui} used to display task operation messages
     */
    public TaskList(Ui ui) {
        this.ui = ui;
    }

    /**
     * Adds a task to the task list and prints a message via the UI, if it is not
     * loaded from the existing file.
     *
     * @param isFromFile {@code true} if the task is being loaded from a file; {@code false} if added by the user
     * @throws MinervaException if the task cannot be added
     */
    public void addTask(Task t, boolean isFromFile) throws MinervaException {
        tasks.add(t);
        if (!isFromFile) {
            ui.printAddedMessage(t, tasks.size());
        }
    }

    /**
     * Deletes a task at the specified index and prints a message via the UI.
     *
     * @param currentTask the zero-based index of the task to delete
     * @throws MinervaException if the index is invalid
     */
    public void deleteTask(int currentTask) throws MinervaException {
        if (currentTask < 0 || currentTask >= tasks.size()) {
            throw new MinervaException(ERROR_NONEXISTENT_TASK);
        }
        Task t = tasks.remove(currentTask);
        ui.printDeletedMessage(t, tasks.size());
    }

    /**
     * Marks the task at the specified index as done and updates the UI.
     *
     * @param currentTask the zero-based index of the task to mark
     * @throws MinervaException if the index is invalid
     */
    public void markTask(int currentTask) throws MinervaException{
        if (currentTask < 0 || currentTask >= tasks.size()) {
            throw new MinervaException(ERROR_NONEXISTENT_TASK);
        }
        tasks.get(currentTask).markDone();
        ui.printMarkedMessage(tasks.get(currentTask));
    }

    /**
     * Marks the task at the specified index as not done and updates the UI.
     *
     * @param currentTask the zero-based index of the task to unmark
     * @throws MinervaException if the index is invalid
     */
    public void unmarkTask(int currentTask) throws MinervaException {
        if (currentTask < 0 || currentTask >= tasks.size()) {
            throw new MinervaException(ERROR_NONEXISTENT_TASK);
        }
        tasks.get(currentTask).unmarkDone();
        ui.printUnmarkedMessage(tasks.get(currentTask));
    }


    /**
     * Searches for tasks containing the exact keyword in their description.
     *
     * @param task the keyword to search for
     * @return a list of {@link Task} objects containing the keyword
     * @throws MinervaException never thrown in current implementation
     */
    public ArrayList<Task> findTask(String task) throws MinervaException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks) {
            String[] words = t.getDescription().split("\\s+");
            for (String word : words) {
                if (word.equals(task)) {
                    matchingTasks.add(t);
                    break;
                }
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the list of all tasks.
     *
     * @return an {@link ArrayList} of {@link Task} objects
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Parses a single line from the storage file into a {@link Task} object.
     *
     * <p>Supports Todo, Deadline, and Event tasks. Throws
     * {@link IllegalArgumentException} if the line format is invalid.</p>
     *
     * @param line a line from the storage file
     * @return a {@link Task} represented by the line
     * @throws IllegalArgumentException if the line format is invalid
     */
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
