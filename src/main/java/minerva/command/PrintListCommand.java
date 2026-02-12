package minerva.command;

import minerva.task.TaskList;
import minerva.ui.Ui;

public class PrintListCommand extends Command {
    public PrintListCommand() {
        super(CommandType.LIST_TASKS);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printTaskList(tasks.getTasks());
    }
}
