package de.nischwan.control.commands;

import de.nischwan.storage.DeciderEntry;

import java.util.List;

/**
 * This command removes an entry from the decider list.
 *
 * @author Nico Schwanebeck
 */
public class RemoveEntryCommand implements ManipulateDeciderEntryCommand {

    private int listIndex;
    private final List<DeciderEntry> entries;
    private final DeciderEntry entryToRemove;

    public RemoveEntryCommand(final List<DeciderEntry> entries, final DeciderEntry entryToRemove) {
        this.entries = entries;
        this.entryToRemove = entryToRemove;
    }

    @Override
    public void execute() {
        listIndex = entries.indexOf(entryToRemove);
        entries.remove(listIndex);
    }

    @Override
    public void undo() {
        entries.add(listIndex, entryToRemove);
    }
}
