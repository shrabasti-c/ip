public class PrintListCommand extends Command {
    public PrintListCommand() {
        super(CommandType.LIST_TASKS);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printTaskList(tasks.getTasks());
    }
}
