package de.nischwan.control;

import de.nischwan.control.commands.ChangeEntryWeightCommand;
import de.nischwan.control.commands.CreateEntryCommand;
import de.nischwan.control.commands.ManipulateDeciderEntryCommand;
import de.nischwan.control.commands.RemoveEntryCommand;
import de.nischwan.control.commands.RenameAndChangeEntryWeightCommand;
import de.nischwan.control.commands.RenameEntryCommand;
import de.nischwan.control.exceptions.DeciderEntryNotFoundException;
import de.nischwan.control.exceptions.EntryAlreadyExistException;
import de.nischwan.storage.DeciderEntry;
import de.nischwan.storage.Storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Nico Schwanebeck
 */
public class DeciderEntryController {
    private List<DeciderEntry> deciderEntries = new ArrayList<>();
    private List<ManipulateDeciderEntryCommand> commandHistory = new ArrayList<>();
    private int currentCommandIndex = -1;
    private Storage storage;

    public DeciderEntryController(Storage storage) {
        this.storage = storage;
    }

    public void addEntry(final String name, final int weight) {
        if (deciderEntries.stream().anyMatch(deciderEntry -> deciderEntry.getEntryName().equalsIgnoreCase(name))) {
            throw new EntryAlreadyExistException(name);
        }

        executeCommand(new CreateEntryCommand(deciderEntries, name, weight));
    }

    public void removeEntry(final String name) {
        final DeciderEntry deciderEntry = getDeciderEntry(name);
        executeCommand(new RemoveEntryCommand(deciderEntries, deciderEntry));
    }

    public void renameEntry(final String oldName, final String newName) {
        final DeciderEntry deciderEntry = getDeciderEntry(oldName);
        executeCommand(new RenameEntryCommand(deciderEntry, newName));
    }

    public void changeEntryWeight(final String name, final int newWeight) {
        final DeciderEntry deciderEntry = getDeciderEntry(name);
        executeCommand(new ChangeEntryWeightCommand(deciderEntry, newWeight));
    }

    public void changeNameAndWeight(final String name, final String newName, final int newWeight) {
        final DeciderEntry deciderEntry = getDeciderEntry(name);
        executeCommand(new RenameAndChangeEntryWeightCommand(deciderEntry, newName, newWeight));
    }

    private void executeCommand(final ManipulateDeciderEntryCommand command) {
        command.execute();
        addCommandToHistory(command);
    }

    private DeciderEntry getDeciderEntry(final String name) {
        final Optional<DeciderEntry> deciderEntryOptional = deciderEntries.stream().filter(
                deciderEntry -> deciderEntry.getEntryName().matches(name)
        ).findFirst();

        if (deciderEntryOptional.isPresent()) {
            return deciderEntryOptional.get();
        } else {
            throw new DeciderEntryNotFoundException(name);
        }
    }

    public List<DeciderEntry> getDeciderEntries() {
        return Collections.unmodifiableList(deciderEntries);
    }

    private void addCommandToHistory(final ManipulateDeciderEntryCommand command) {
        while (canRedo()) {
            commandHistory.remove(currentCommandIndex + 1);
        }
        commandHistory.add(command);
        currentCommandIndex += 1;
    }

    public boolean canUndo() {
        return currentCommandIndex >= 0;
    }

    public void undo() {
        if (canUndo()) {
            commandHistory.get(currentCommandIndex).undo();
            currentCommandIndex -= 1;
        }
    }

    public boolean canRedo() {
        return commandHistory.size() > 0 && commandHistory.size() - 1 > currentCommandIndex;
    }

    public void redo() {
        if (canRedo()) {
            commandHistory.get(currentCommandIndex + 1).execute();
            currentCommandIndex += 1;
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public void save() {
        storage.save(getDeciderEntries());
    }

    public void load() {
        deciderEntries = storage.load();
    }
}
