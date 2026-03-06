package minerva.command;

import minerva.data.exception.MinervaException;
import minerva.task.TaskList;
import minerva.ui.Ui;

public class FindCommand extends Command {
    protected final String task;

    public FindCommand(String task) {
        super(CommandType.FIND_TASK);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        ui.printTaskList(tasks.findTask(task), false);
    }
}
