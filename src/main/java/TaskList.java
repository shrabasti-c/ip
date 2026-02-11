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

    public void addTask(Task t) throws MinervaException{
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
}
