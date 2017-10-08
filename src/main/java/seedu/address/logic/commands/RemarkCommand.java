package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Remark;

/**
 * Adds a remark to a person.
 */
public class RemarkCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a remark for a person at INDEX. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Likes cake";

    public static final String MESSAGE_SUCCESS = "New remark added: %1$s";
    public static final String MESSAGE_CAN_TAKE_ARGS = "The method took two arguments %1$d, %2$s";
    
    private final Index index;
    private final Remark remarkDescriptor;

    /**
     * @param index of the person in the filtered person list to edit remark
     * @param remarkDescriptor details of remark
     */
    public RemarkCommand(Index index, Remark remarkDescriptor) {
        requireNonNull(index);
        requireNonNull(remarkDescriptor);

        this.index = index;
        this.remarkDescriptor = remarkDescriptor;
    }
    
    public Remark getRemark() { return remarkDescriptor; }
    
    @Override
    protected CommandResult executeUndoableCommand() throws CommandException {
        throw new CommandException(String.format(MESSAGE_CAN_TAKE_ARGS, index.getOneBased(), remarkDescriptor));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;

        return getRemark().equals(e.getRemark());
    }
}
