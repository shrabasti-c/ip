public class CommandParser {
    public static boolean isExitCommand(String command) {
        return command.equalsIgnoreCase("bye");
    }

    public static boolean isListCommand(String command) {
        return command.equalsIgnoreCase("list");
    }

    public static boolean isMarkedCommand(String command) {
        return command.toLowerCase().startsWith("mark");
    }

    public static boolean isUnmarkedCommand(String command) {
        return command.toLowerCase().startsWith("unmark");
    }

    public static void evaluateAndExecuteCommand(String command, TaskList tasks) {
        if (isExitCommand(command))
            return;
        if (isListCommand(command))
            MessagePrinter.printTaskList(tasks.getTasks());
        else if (isMarkedCommand(command)) {
            String[] temp = command.split(" ", 2);
            int currentTask = (temp.length < 2) ? -1 : (Integer.parseInt(temp[1]) - 1);
            tasks.markTask(currentTask);
        }
        else if (isUnmarkedCommand(command)) {
            String[] temp = command.split(" ", 2);
            int currentTask = (temp.length < 2) ? -1 : (Integer.parseInt(temp[1]) - 1);
            tasks.unmarkTask(currentTask);
        }
        else
            tasks.addTask(command);
    }
}
