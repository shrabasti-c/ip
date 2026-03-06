package minerva.storage;

import minerva.task.Task;
import minerva.task.TaskList;
import minerva.ui.Ui;
import minerva.data.exception.MinervaException;

import java.util.List;

/**
 * Provides functionality to decode a list of strings from storage
 * into a {@link TaskList} object for the Minerva application.
 *
 * <p>This class reads each line, converts it to a {@link Task}, and adds it to a
 * TaskList.
 * Corrupted lines are skipped with a message printed to the console.</p>
 */
public class TaskListDecoder {

    public static final String ERROR_CORRUPTED_LINE = "Skipping corrupted line: ";

    /**
     * Decodes a list of strings from the storage file into a {@link TaskList}.
     *
     * @param lines the lines read from the storage file
     * @param ui the {@link Ui} to use for task messages
     * @return a {@link TaskList} containing all successfully parsed tasks
     * @throws MinervaException if adding a task fails
     */
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
