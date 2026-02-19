package minerva.storage;

import minerva.task.Task;
import minerva.task.TaskList;
import minerva.ui.Ui;
import minerva.data.exception.MinervaException;

import java.util.List;

public class TaskListDecoder {

    public static final String ERROR_CORRUPTED_LINE = "Skipping corrupted line: ";

    public static TaskList decodeTaskList(List<String> lines, Ui ui) throws MinervaException {
        TaskList tasks = new TaskList(ui);

        for (String line : lines) {
            try {
                Task task = tasks.parseTaskFromFile(line);
                tasks.addTask(task);
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_CORRUPTED_LINE + line);
            }
        }
        return tasks;
    }
}
