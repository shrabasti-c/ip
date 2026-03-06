# Minerva User Guide
Minerva is a personalized chatbot for managing tasks, optimized for use via a Command Line Interface (CLI).

## Features
```
Notes about the command format:
Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in deadline TASK /by DEADLINE, DEADLINE is a parameter which can be used as deadline return book /by Monday 4 PM
```

## Adding a ToDo task : `todo`
Adds a task without any date/time attached to it.

Format: `todo TASK`

Example: `todo borrow book`

Expected Output:
```
	____________________________________________________________
	Understood: I have added this task
		[T][ ] borrow book
	Number of pending tasks in list: 1
	____________________________________________________________
```

Note: The number of pending tasks will vary depending on your previous commands.

## Adding a Deadline task : `deadline`
Adds a task that needs to be done before a certain date/time.

Format: `deadline TASK /by DEADLINE`

Example: `deadline return book /by Monday 4 PM`

Expected Output:
```
	____________________________________________________________
	Understood: I have added this task
		[D][ ] return book (by: monday 4 pm)
	Number of pending tasks in list: 2
	____________________________________________________________
```

Note: The number of pending tasks will vary depending on your previous commands.


Note: The number of pending tasks will vary depending on your previous commands.

## Adding an Event task : `event`
Adds a task that starts at a specific date/time and ends at a specific date/time.

Format: `event TASK /from START /to END`

Example: `event library fundraiser /from afternoon 1 pm /to evening 5 pm`

Expected Output:
```
	____________________________________________________________
	Understood: I have added this task
		[E][ ] library fundraiser (from: afternoon 1 pm to: evening 5 pm)
	Number of pending tasks in list: 3
	____________________________________________________________
```

Note: The number of pending tasks will vary depending on your previous commands.

## Marking a task : `mark`
Marks the task at a specified TASK_INDEX as done.

Format: `mark TASK_INDEX`

Example: `mark 1`

Expected Output:
```
	____________________________________________________________
	Congratulations!
	marked done: [T][X] borrow book
	____________________________________________________________
```

## Unmarking a task : `unmark`
Marks the task at a specified TASK_INDEX as not done.

Format: `unmark TASK_INDEX`

Example: `unmark 1`

Expected Output:
```
	____________________________________________________________
	Task pending...!
	marked undone: [T][ ] borrow book
	____________________________________________________________
```

## Listing all tasks : `list`
Shows a list of all tasks.

Format: `list`
```
____________________________________________________________
1. [T][ ] borrow book
2. [D][ ] return book (by: monday 4 pm)
3. [E][ ] library fundraiser (from: afternoon 1 pm to: evening 5 pm)
____________________________________________________________
```

## Deleting a task : `delete`
Deletes the task at a specified TASK_INDEX.

Format: `delete TASK_INDEX`

Example: `delete 3`

Expected Output:
```
    ____________________________________________________________
	Your folly is undone: I have deleted this task
		[E][ ] library fundraiser (from: afternoon 1 pm to: evening 5 pm)
	Number of pending tasks in list: 2
	____________________________________________________________
	

```

Note: The number of pending tasks will vary depending on your previous commands.

## Finding a task : `find`
Finds a task containing a specified KEYWORD in its description.

Format: `find KEYWORD`

Example: 
* `find borrow`
* `find book`

Expected Outputs:
```
	____________________________________________________________
	
	Behold! I have found matching tasks in your list
	1. [T][ ] borrow book
	____________________________________________________________
```
```
	____________________________________________________________
	
	Behold! I have found matching tasks in your list
	1. [T][ ] borrow book
	2. [D][ ] return book (by: monday 4 pm)
	____________________________________________________________
```

## Exiting the application : `bye`
Terminates the session with the Chatbot.

Format: `bye`

Expected Outputs:
```
	____________________________________________________________
	Farewell, until our paths cross again!
	____________________________________________________________
```
