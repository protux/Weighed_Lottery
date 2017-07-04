package de.nischwan.control.commands;

import de.nischwan.control.commands.utils.CommandGenerator;
import de.nischwan.storage.DeciderEntry;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nico Schwanebeck
 */
public class TestRemoveEntryCommand {

    private CommandGenerator generator = new CommandGenerator();

    public void tearDown() {
        generator.reset();
    }

    @Test
    public void executeRemoveEntry() {
        final List<DeciderEntry> entries = generator.generateDeciderEntryList(5);
        final DeciderEntry entryToRemove = entries.get(3);

        final ManipulateDeciderEntryCommand command = generator.generateRemoveEntryCommand(entries, entryToRemove);
        command.execute();

        Assert.assertFalse(entries.contains(entryToRemove));
    }

    @Test
    public void undoRemoveEntry() {
        final List<DeciderEntry> entries = generator.generateDeciderEntryList(5);
        final DeciderEntry entryToRemove = entries.get(3);

        final ManipulateDeciderEntryCommand command = generator.generateRemoveEntryCommand(entries, entryToRemove);
        command.execute();
        command.undo();

        Assert.assertTrue(entries.contains(entryToRemove));
    }
}
