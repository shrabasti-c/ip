/**
 * Provides functionality for parsing user input into executable commands
 * for the Minerva task management application.
 *
 * <p>
 * The {@code Parser} interprets raw command strings entered by the user
 * and converts them into corresponding {@link minerva.command.Command}
 * objects. These command objects encapsulate the logic required to
 * perform actions such as adding tasks, marking tasks, deleting tasks,
 * and searching tasks.
 * </p>
 *
 * <p>
 * If the input format is invalid or incomplete, a {@link minerva.data.exception.MinervaException}
 * is thrown with an appropriate error message.
 * </p>
 */
package minerva.parser;

import minerva.data.exception.MinervaException;
import minerva.command.*;
import minerva.task.Deadline;
import minerva.task.Event;
import minerva.task.Task;
import minerva.task.Todo;

/**
 * Parses user commands and converts them into {@link Command} objects
 * that can be executed by the application.
 */
public class Parser {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String DELETE = "delete";
    private static final String FIND = "find";

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

    /**
     * Parses a user command string and returns the corresponding {@link Command}.
     *
     * @param command the raw command entered by the user
     * @return a {@code Command} representing the requested operation
     * @throws MinervaException if the command is invalid or cannot be parsed
     */
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
        } else if (command.startsWith(DELETE)) {
            return deleteTaskCommand(command);
        } else if (command.startsWith(FIND)) {
            return parseFindCommand(command);
        } else {
            throw new MinervaException(ERROR_INVALID_INPUT);
        }
    }

    /**
     * Parses a delete command that removes a task from the task list.
     *
     * @param command the delete command string
     * @return a {@link DeleteTaskCommand} representing the delete operation
     * @throws MinervaException if the task index is missing or invalid
     */
    public Command deleteTaskCommand(String command) throws MinervaException {
        int currentTask = getCurrentTask(command);
        return new DeleteTaskCommand(currentTask);
    }

    /**
     * Parses a mark command that marks a task as completed.
     *
     * @param command the mark command string
     * @return a {@link MarkTaskCommand}
     * @throws MinervaException if the task index is missing or invalid
     */
    public Command parseMarkCommand(String command) throws MinervaException {
        int currentTask = getCurrentTask(command);
        return new MarkTaskCommand(currentTask);
    }

    /**
     * Parses an unmark command that marks a completed task as incomplete.
     *
     * @param command the unmark command string
     * @return an {@link UnmarkTaskCommand}
     * @throws MinervaException if the task index is missing or invalid
     */
    public Command parseUnmarkCommand(String command) throws MinervaException {
        int currentTask = getCurrentTask(command);
        return new UnmarkTaskCommand(currentTask);
    }

    /**
     * Extracts the task index from a command string.
     *
     * <p>The user provides task numbers starting from 1. This method converts
     * the index to a zero-based index used internally.</p>
     *
     * @param command the command containing a task index
     * @return the task index
     * @throws MinervaException if the index is missing or not a valid number
     */
    private static int getCurrentTask(String command) throws MinervaException {
        String[] commandArguments = command.trim().split(" ", 2);
        if (commandArguments.length < 2 || commandArguments[1].isEmpty()) {
            throw new MinervaException(ERROR_INCOMPLETE_INPUT);
        }
        int currentTask;
        try {
            currentTask = Integer.parseInt(commandArguments[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MinervaException(ERROR_INCORRECT_INDEX_FORMAT);
        }
        return currentTask;
    }

    /**
     * Parses a todo command and creates a {@link Todo} task.
     *
     * @param command the todo command string
     * @return an {@link AddTaskCommand} containing the new task
     * @throws MinervaException if the task description is missing
     */
    public Command parseTodoCommand(String command) throws MinervaException {
        if (command.length() < TODO_START) {
            throw new MinervaException(ERROR_INCOMPLETE_INPUT);
        }
        Task todo = new Todo(command.substring(TODO_START));
        return new AddTaskCommand(todo);
    }

    /**
     * Parses a deadline command and creates a {@link Deadline} task.
     *
     * <p>The command format should follow: {@code deadline <description> /by <time>}</p>
     *
     * @param command the deadline command string
     * @return an {@link AddTaskCommand} containing the new task
     * @throws MinervaException if the command format is invalid
     */
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

    /**
     * Parses an event command and creates an {@link Event} task.
     *
     * <p>The command format should follow:
     * {@code event <description> /from <start> /to <end>}</p>
     *
     * @param command the event command string
     * @return an {@link AddTaskCommand} containing the new task
     * @throws MinervaException if the command format is invalid
     */
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

    /**
     * Parses a find command used to search for tasks containing a keyword.
     *
     * @param command the find command string
     * @return a {@link FindCommand} containing the search keyword
     * @throws MinervaException if the keyword is missing
     */
    public Command parseFindCommand(String command) throws MinervaException {
        if (!command.contains(" ")) {
            throw new MinervaException(ERROR_INCOMPLETE_INPUT);
        }
        String task = command.substring(command.indexOf(" ")+ 1).trim();
        if (task.isEmpty()) {
            throw new MinervaException(ERROR_INCOMPLETE_INPUT);
        }
        return new FindCommand(task);
    }

}

