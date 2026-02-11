public class Parser {
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

    public static final String ERROR_INVALID_INPUT = "I do not recognize this task.";
    public static final String ERROR_INVALID_INPUT_DEADLINE = "The Deadline task must be entered correctly.";
    public static final String ERROR_INVALID_INPUT_EVENT = "The Event task must be entered correctly.";
    public static final String ERROR_INCOMPLETE_INPUT = "You must follow a task with a description.";
    public static final String ERROR_INCORRECT_INDEX_FORMAT = "You must specify the task number.";

    public Command parseCommand(String command) throws MinervaException {
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
            throw new MinervaException(ERROR_INVALID_INPUT);
        }
    }

    public Command parseMarkCommand(String command) throws MinervaException {
        int currentTask = getCurrentTask(command);
        return new MarkTaskCommand(currentTask);
    }

    public Command parseUnmarkCommand(String command) throws MinervaException {
        int currentTask = getCurrentTask(command);
        return new UnmarkTaskCommand(currentTask);
    }

    private static int getCurrentTask(String command) throws MinervaException {
        String[] commandArguments = command.trim().split(" ", 2);
        if (commandArguments.length < 2 || commandArguments[1].isEmpty()) {
            throw new MinervaException(ERROR_INCOMPLETE_INPUT);
        }
        int currentTask = 0;
        try {
            currentTask = Integer.parseInt(commandArguments[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MinervaException(ERROR_INCORRECT_INDEX_FORMAT);
        }
        return currentTask;
    }

    public Command parseTodoCommand(String command) throws MinervaException {
        if (command.length() < TODO_START) {
            throw new MinervaException(ERROR_INCOMPLETE_INPUT);
        }
        Task todo = new Todo(command.substring(TODO_START));
        return new AddTaskCommand(todo);
    }

    public Command parseDeadlineCommand(String command) throws MinervaException {
        if (command.length() < DEADLINE_START) {
            throw new MinervaException(ERROR_INCOMPLETE_INPUT);
        }
        try {
            String description = command.substring(DEADLINE_START, command.indexOf(SEPARATOR) - 1);
            String from = command.substring(command.indexOf(SEPARATOR) + BY_START);

            Task deadline = new Deadline(description, from);
            return new AddTaskCommand(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MinervaException(ERROR_INVALID_INPUT_DEADLINE);
        }
    }


    public Command parseEventCommand(String command) throws MinervaException {
        if (command.length() < EVENT_START) {
            throw new MinervaException(ERROR_INCOMPLETE_INPUT);
        }
        try {
            String description = command.substring(EVENT_START, command.indexOf(SEPARATOR) - 1);
            String from = command.substring(command.indexOf(SEPARATOR) + FROM_START, command.lastIndexOf(SEPARATOR) - 1);
            String to = command.substring(command.lastIndexOf(SEPARATOR) + TO_START);

            Task event = new Event(description, from, to);
            return new AddTaskCommand(event);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MinervaException(ERROR_INVALID_INPUT_EVENT);
        }
    }
}

