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
}
