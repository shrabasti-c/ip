public class Parser {
    private final Ui ui;

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    private static final char SEPARATOR = '/';
    private static final int TODO_START = 5;
    private static final int DEADLINE_START = 9;
    private static final int EVENT_START = 6;
    private static final int BY_START = 4;
    private static final int FROM_START = 6;
    private static final int TO_START = 4;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public Command parseCommand(String command) {
        command = command.toLowerCase().trim();

        if (command.startsWith(BYE)) {
            return new ExitCommand();
        } else if (command.startsWith(LIST)) {
            return new PrintListCommand();
        } else if (command.startsWith(MARK)) {
            return parseMarkCommand(command);
        } else if (command.startsWith(UNMARK)) {
            return parseUnmarkCommand(command);
        } else if (command.startsWith(TODO)) {
            return parseTodoCommand(command);
        } else if (command.startsWith(DEADLINE)) {
            return parseDeadlineCommand(command);
        } else if (command.startsWith(EVENT)) {
            return parseEventCommand(command);
        } else {
            return new OtherCommand("Invalid Command");
        }
    }

    public Command parseMarkCommand(String command) {
        int currentTask = getCurrentTask(command);
        return new MarkTaskCommand(currentTask);
    }

    public Command parseUnmarkCommand(String command) {
        int currentTask = getCurrentTask(command);
        return new UnmarkTaskCommand(currentTask);
    }

    private static int getCurrentTask(String command) {
        String[] commandArguments = command.split(" ", 2);
        return Integer.parseInt(commandArguments[1]) - 1;
    }

    public Command parseTodoCommand(String command) {
        Task todo = new Todo(command.substring(TODO_START));
        return new AddTaskCommand(todo);
    }

    public Command parseDeadlineCommand(String command) {
        String description = command.substring(DEADLINE_START, command.indexOf(SEPARATOR) - 1);
        String from = command.substring(command.indexOf(SEPARATOR) + BY_START);

        Task deadline = new Deadline(description, from);
        return new AddTaskCommand(deadline);
    }

    public Command parseEventCommand(String command) {
        String description = command.substring(EVENT_START, command.indexOf(SEPARATOR) - 1);
        String from = command.substring(command.indexOf(SEPARATOR) + FROM_START, command.lastIndexOf(SEPARATOR) - 1);
        String to = command.substring(command.lastIndexOf(SEPARATOR) + TO_START);

        Task event = new Event(description, from, to);
        return new AddTaskCommand(event);
    }
}

