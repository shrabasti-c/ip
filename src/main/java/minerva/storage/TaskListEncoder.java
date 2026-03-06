package minerva.storage;

import java.util.ArrayList;
import java.util.List;

import minerva.task.Task;
import minerva.task.TaskList;

/**
 * Provides functionality to encode a {@link TaskList} into a list of strings
 * suitable for file storage in the Minerva application.
 *
 * <p>Each task is converted to its file format representation. </p>
 */
public class TaskListEncoder {

    /**
     * Encodes a {@link TaskList} into a list of strings for saving to a file.
     *
     * @param tasks the {@link TaskList} to encode
     * @return a {@link List} of strings representing the tasks in file format
     */
    public static List<String> encodeTaskList(TaskList tasks) {
        final List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            encodedTasks.add(encodeTaskToString(task));
        }
        return encodedTasks;
    }

    /**
     * Encodes a single {@link Task} into its string representation for file storage.
     *
     * @param task the task to encode
     * @return the string representation of the task in file format
     */
    private static String encodeTaskToString(Task task) {
        return task.toFileFormat();
    }
}
