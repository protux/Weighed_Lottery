package de.nischwan.control.commands;

import de.nischwan.storage.DeciderEntry;

/**
 * This command renames the entry name of a decider entry.
 *
 * @author Nico Schwanebeck
 */
public class RenameEntryCommand implements ManipulateDeciderEntryCommand {

    private final DeciderEntry deciderEntry;
    private final String newName;
    private String oldName;

    public RenameEntryCommand(final DeciderEntry deciderEntry, final String newName) {
        this.deciderEntry = deciderEntry;
        this.newName = newName;
    }

    @Override
    public void execute() {
        oldName = deciderEntry.getEntryName();
        deciderEntry.setEntryName(newName);
    }

    @Override
    public void undo() {
        deciderEntry.setEntryName(oldName);
    }
}
