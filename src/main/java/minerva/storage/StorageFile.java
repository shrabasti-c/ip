package minerva.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import minerva.task.TaskList;
import minerva.data.exception.MinervaException;
import minerva.ui.Ui;

/**
 * Handles reading from and writing to the storage file for the Minerva application.
 *
 * <p>This class is responsible for saving a {@link TaskList} to a file and
 * loading it back into memory. It ensures the file's directories exist and
 * handles I/O exceptions by throwing {@link MinervaException}.</p>
 */
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

    /**
     * Saves the given {@link TaskList} to the storage file.
     *
     * @param tasks the {@link TaskList} to save
     * @throws MinervaException if an I/O error occurs during writing
     */
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

    /**
     * Loads a {@link TaskList} from the storage file.
     *
     * <p>If the file does not exist, an empty TaskList is returned.</p>
     *
     * @param ui the {@link Ui} to use for messages
     * @return the loaded {@link TaskList}
     * @throws MinervaException if an I/O error occurs during reading
     */
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