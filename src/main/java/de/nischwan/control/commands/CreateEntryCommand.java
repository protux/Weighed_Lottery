package de.nischwan.control.commands;

import de.nischwan.control.commands.exceptions.UndoFailedException;
import de.nischwan.storage.DeciderEntry;

import java.util.List;
import java.util.Optional;

/**
 * This command is used to add an entry to the decider.
 *
 * @author Nico Schwanebeck
 */
public class CreateEntryCommand implements ManipulateDeciderEntryCommand {

    private final List<DeciderEntry> entries;
    private final String entryName;
    private final int entryWeight;

    public CreateEntryCommand(final List<DeciderEntry> deciderEntries, final String entryName, final int entryWeight) {
        this.entries = deciderEntries;
        this.entryName = entryName;
        this.entryWeight = entryWeight;
    }

    @Override
    public void execute() {
        entries.add(new DeciderEntry(entryName, entryWeight));
    }

    @Override
    public void undo() {
        final Optional<DeciderEntry> entryOptional = entries.stream().filter(
                deciderEntry -> deciderEntry.getEntryName().equals(entryName)
        ).findFirst();

        if (entryOptional.isPresent()) {
            entries.remove(entryOptional.get());
        } else {
            throw new UndoFailedException();
        }
    }
}
