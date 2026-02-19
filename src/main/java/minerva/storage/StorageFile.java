package minerva.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import minerva.task.TaskList;
import minerva.data.exception.MinervaException;
import minerva.ui.Ui;

public class StorageFile {
    public static final String DEFAULT_STORAGE_FILEPATH = "data/minerva.txt";
    public static final String ERROR_WRITING_TO_FILE = "Error writing to file: ";
    public static final String ERROR_READING_FROM_FILE = "Error reading from file: ";
    public final Path path;

    public StorageFile() {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public StorageFile(String filePath) {
        this.path = Paths.get(filePath);
    }

    public void save(TaskList tasks) throws MinervaException {
        try {
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }

            List<String> encoded = TaskListEncoder.encodeTaskList(tasks);
            Files.write(path, encoded);

        } catch (IOException e) {
            throw new MinervaException(ERROR_WRITING_TO_FILE + path);
        }
    }

    public TaskList load(Ui ui) throws MinervaException {
        try {
            if (!Files.exists(path) || !Files.isRegularFile(path)) {
                return new TaskList(ui);
            }
            return TaskListDecoder.decodeTaskList(Files.readAllLines(path), ui);

        } catch (IOException e) {
            throw new MinervaException(ERROR_READING_FROM_FILE + path);
        }
    }
}