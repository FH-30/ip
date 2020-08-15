import main.java.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static void log(String message) {
        String startLine = "    ____________________________________________________________\n";
        String endLine = "    ____________________________________________________________";
        String output = startLine + message + endLine;
        System.out.println(output);
    }

    public static void main(String[] args) throws DukeException{

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "    Hello! I'm Duke\n" +
                "    What can I do for you?\n";
        log(greeting);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todos = new ArrayList<>();

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine().trim();

                if (input.equals("bye")) {
                    break;
                }

                if (input.startsWith("done")) {
                    if (input.length() == 4 || input.substring(5).isBlank()) {
                        throw new DukeException("\tNo task number specified.");
                    }
                    int idx;
                    try {
                        idx = Integer.parseInt(input.substring(5).trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tTask number format invalid, " +
                                "must be a number.");
                    }
                    if (idx < 0 || idx > todos.size() - 1) {
                        throw new DukeException("\tThere is no such task.");
                    }
                    Task toChange = todos.get(idx);
                    toChange.markAsDone();
                    String output = "     Nice! I've marked this task as done:\n" +
                            "       " + toChange + "\n";
                    log(output);
                } else if (input.startsWith("delete")) {
                    if (input.length() == 6 || input.substring(7).isBlank()) {
                        throw new DukeException("\tNo task number specified.");
                    }
                    int idx;
                    try {
                        idx = Integer.parseInt(input.substring(7).trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new DukeException("\tTask number format invalid, " +
                                "must be a number.");
                    }
                    if (idx < 0 || idx > todos.size() - 1) {
                        throw new DukeException("\tThere is no such task.");
                    }
                    Task toDelete = todos.get(idx);
                    todos.remove(idx);
                    String output = "     Noted. I've removed this task:\n" +
                            "       " + toDelete + "\n" +
                            "     Now you have " + todos.size() + " tasks in the list.\n";
                    log(output);
                } else if (input.equals("list")) {
                    if (todos.size() == 0) {
                        log("\tYay! You have nothing to do at the moment! :-)\n");
                    } else {
                        StringBuilder output = new StringBuilder("     Here are the tasks in your list:\n");

                        for (int i = 1; i <= todos.size(); i++) {
                            Task theTask = todos.get(i - 1);
                            output.append("\t ").append(i).append(".").append(theTask).append("\n");
                        }

                        log(output.toString());
                    }
                } else if (!input.isBlank()){
                    Task newTask = null;
                    String desc;

                    if (input.startsWith("todo")) {
                        if (input.length() == 4 || input.substring(5).isBlank()) {
                            throw new DukeException("\tThe description of a todo cannot be empty.");
                        }
                        desc = input.substring(5).trim();
                        newTask = new Todo(desc);
                    } else if (input.startsWith("deadline")) {
                        String[] components = input.split(" /by ");
                        if (components[0].length() == 8 || components[0].substring(9).isBlank()) {
                            throw new DukeException("\tThe description of a deadline cannot be empty.");
                        }
                        if (components.length == 1 || components[1].isBlank()) {
                            throw new DukeException("\tThe timing of a deadline cannot be empty.");
                        }
                        desc = components[0].substring(9).trim();
                        newTask = new Deadline(desc, components[1].trim());
                    } else if (input.startsWith("event")) {
                        String[] components = input.split(" /at ");
                        if (components[0].length() == 5 || components[0].substring(6).isBlank()) {
                            throw new DukeException("\tThe description of an event cannot be empty.");
                        }
                        if (components.length == 1 || components[1].isBlank()) {
                            throw new DukeException("\tThe timing of an event cannot be empty.");
                        }
                        desc = components[0].substring(6).trim();
                        newTask = new Event(desc, components[1].trim());
                    } else {
                        throw new DukeException("\tI don't know what that means :-(");
                    }

                    todos.add(newTask);

                    String output = "     Got it. I've added this task:\n" +
                            "       " + newTask + "\n" +
                            "     Now you have " + todos.size() + " tasks in the list.\n" ;

                    log(output);
                }
            } catch (DukeException e) {
                log(e.getMessage() + "\n");
            }
        }

        String byeMessage = "     Bye. Hope to see you again soon!\n";
        log(byeMessage);
    }
}
