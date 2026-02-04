import java.util.*;
public class TaskList {
    private final Task[] tasks = new Task[100];
    private int numTasks = 0;

    public void addTask(Task t) {
        if (numTasks >= 100) {
            MessagePrinter.printError("Cannot add more tasks.");
            return;
        }
        tasks[numTasks++] = t;
        MessagePrinter.printAddedMessage(t, numTasks);
    }

    public void markTask(int currentTask) {
        if (currentTask < 0 || currentTask >= numTasks) {
            MessagePrinter.printError("This task does not exist!");
            return;
        }
        tasks[currentTask].markDone();
        MessagePrinter.printMarkedMessage(tasks[currentTask]);
    }

    public void unmarkTask(int currentTask) {
        if (currentTask < 0 || currentTask >= numTasks) {
            MessagePrinter.printError("This task does not exist!");
            return;
        }
        tasks[currentTask].unmarkDone();
        MessagePrinter.printUnmarkedMessage(tasks[currentTask]);
    }

    public Task[] getTasks() {
        return Arrays.copyOf(tasks, numTasks);
    }
}
