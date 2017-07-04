package de.nischwan.control.commands;

import de.nischwan.storage.DeciderEntry;

/**
 * This command is a combination of {@link RenameEntryCommand} and {@link ChangeEntryWeightCommand}.
 *
 * @author Nico Schwanebeck
 */
public class RenameAndChangeEntryWeightCommand implements ManipulateDeciderEntryCommand {

    private final RenameEntryCommand renameEntryCommand;
    private final ChangeEntryWeightCommand changeEntryWeightCommand;

    public RenameAndChangeEntryWeightCommand(final DeciderEntry deciderEntry, final String newName, final int newWeight) {
        this.renameEntryCommand = new RenameEntryCommand(deciderEntry, newName);
        this.changeEntryWeightCommand = new ChangeEntryWeightCommand(deciderEntry, newWeight);
    }

    @Override
    public void execute() {
        renameEntryCommand.execute();
        changeEntryWeightCommand.execute();
    }

    @Override
    public void undo() {
        renameEntryCommand.undo();
        changeEntryWeightCommand.undo();
    }
}
