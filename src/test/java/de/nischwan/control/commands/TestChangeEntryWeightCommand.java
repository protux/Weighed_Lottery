package de.nischwan.control.commands;

import de.nischwan.control.commands.utils.CommandGenerator;
import de.nischwan.storage.DeciderEntry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Nico Schwanebeck
 */
public class TestChangeEntryWeightCommand {

    private CommandGenerator generator = new CommandGenerator();

    @After
    public void tearDown() {
        generator.reset();
    }

    @Test
    public void executeChangeEntryWeight() {
        final DeciderEntry entry = generator.generateDeciderEntry();
        final int entryWeight = entry.getEntryWeight();

        final ManipulateDeciderEntryCommand command = generator.generateChangeEntryWeightCommand(entry);
        command.execute();

        Assert.assertNotEquals(entryWeight, entry.getEntryWeight());
    }

    @Test
    public void undoChangeEntryWeight() {
        final DeciderEntry entry = generator.generateDeciderEntry();
        final int entryWeight = entry.getEntryWeight();

        final ManipulateDeciderEntryCommand command = generator.generateChangeEntryWeightCommand(entry);
        command.execute();
        command.undo();

        Assert.assertEquals(entryWeight, entry.getEntryWeight());
    }
}
