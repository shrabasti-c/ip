import java.util.*;
public class TaskList {
    private final Task[] tasks = new Task[100];
    private int numTasks = 0;
    private final Ui ui;

    public TaskList(Ui ui) {
        this.ui = ui;
    }

    public void addTask(Task t) {
        if (numTasks >= 100) {
            ui.printError("Cannot add more tasks.");
            return;
        }
        tasks[numTasks++] = t;
        ui.printAddedMessage(t, numTasks);
    }

    public void markTask(int currentTask) {
        if (currentTask < 0 || currentTask >= numTasks) {
            ui.printError("This task does not exist!");
            return;
        }
        tasks[currentTask].markDone();
        ui.printMarkedMessage(tasks[currentTask]);
    }

    public void unmarkTask(int currentTask) {
        if (currentTask < 0 || currentTask >= numTasks) {
            ui.printError("This task does not exist!");
            return;
        }
        tasks[currentTask].unmarkDone();
        ui.printUnmarkedMessage(tasks[currentTask]);
    }

    public Task[] getTasks() {
        return Arrays.copyOf(tasks, numTasks);
    }
}
