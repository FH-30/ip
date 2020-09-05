package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public abstract class Command {

    /**
     * Executes the Command.
     *
     * @param tasks The TaskList manipulated by the Command.
     * @param ui The Ui which will generate outputs significant to the user.
     * @param storage The Storage which will record changes of tasks into the file specified by its path.
     * @throws DukeException Thrown when the command from the user is not feasible.
     * @return The output to be displayed to the user.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Indicates whether the program should exit after executing this Command.
     *
     * @return True if the program should exit after execution, otherwise false.
     */
    public abstract boolean isExit();
}
