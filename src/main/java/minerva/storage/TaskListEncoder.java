package minerva.storage;

import java.util.ArrayList;
import java.util.List;

import minerva.task.Task;
import minerva.task.TaskList;

public class TaskListEncoder {
    public static List<String> encodeTaskList(TaskList tasks) {
        final List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            encodedTasks.add(encodeTaskToString(task));
        }
        return encodedTasks;
    }

    private static String encodeTaskToString(Task task) {
        return task.toFileFormat();
    }
}
