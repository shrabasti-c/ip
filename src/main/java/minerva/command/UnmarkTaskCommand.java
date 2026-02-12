package minerva.command;

import minerva.data.exception.MinervaException;
import minerva.task.TaskList;
import minerva.ui.Ui;

public class UnmarkTaskCommand extends Command {
    protected final int currentTask;

    public UnmarkTaskCommand(int currentTask) {
        super(CommandType.UNMARK_TASK);
        this.currentTask = currentTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws MinervaException {
        tasks.unmarkTask(currentTask);
    }
}
