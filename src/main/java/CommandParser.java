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

    public static boolean isEvent(String command) {
        return command.toLowerCase().startsWith("event");
    }

    public static boolean isDeadline(String command) {
        return command.toLowerCase().startsWith("deadline");
    }

    public static Task assignTaskType(String command) {
        command = command.toLowerCase().trim();
        Task t;
        if (isEvent(command)) {
            String description = command.substring(6, command.indexOf('/') - 1);
            String from = command.substring(command.indexOf('/') + 6, command.lastIndexOf('/') - 1);
            String to = command.substring(command.lastIndexOf('/') + 4);
            t = new Event(description, from, to);
        } else if (isDeadline(command)) {
            String description = command.substring(9, command.indexOf('/') - 1);
            String from = command.substring(command.indexOf('/') + 4);
            t = new Deadline(description, from);
        } else {
            t = new ToDo(command.substring(5));
        }
        return t;
    }


    public static void evaluateAndExecuteCommand(String command, TaskList tasks) {
        if (isExitCommand(command)) {
            return;
        }

        if (isListCommand(command)) {
            MessagePrinter.printTaskList(tasks.getTasks());
        } else if (isMarkedCommand(command)) {
            String[] commandArguments = command.split(" ", 2);
            int currentTask = (commandArguments.length < 2) ? -1 : (Integer.parseInt(commandArguments[1]) - 1);
            tasks.markTask(currentTask);
        } else if (isUnmarkedCommand(command)) {
            String[] commandArguments = command.split(" ", 2);
            int currentTask = (commandArguments.length < 2) ? -1 : (Integer.parseInt(commandArguments[1]) - 1);
            tasks.unmarkTask(currentTask);
        } else {
            Task t = assignTaskType(command);
            tasks.addTask(t);
        }
    }
}
