package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Adds a remark to a person.
 */
public class RemarkCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a remark for a person at INDEX. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1"
            + PREFIX_REMARK + "Likes cake";

    public static final String MESSAGE_SUCCESS = "New remark added: %1$s";
    public static final String MESSAGE_NOT_IMPLEMENTED = "The method has not been implemented";
    
    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED);
    }
}
