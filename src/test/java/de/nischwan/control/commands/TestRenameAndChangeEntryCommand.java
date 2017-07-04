package de.nischwan.control.commands;

import de.nischwan.control.commands.utils.CommandGenerator;
import de.nischwan.storage.DeciderEntry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Nico Schwanebeck
 */
public class TestRenameAndChangeEntryCommand {

    private CommandGenerator generator = new CommandGenerator();

    @After
    public void tearDown() {
        generator.reset();
    }

    @Test
    public void executeRenameAndChangeEntryCommand() {
        final DeciderEntry deciderEntry = generator.generateDeciderEntry();
        final ManipulateDeciderEntryCommand command = generator.generateRenameAndChangeEntryWeightCommand(deciderEntry);
        final String name = deciderEntry.getEntryName();
        final int weight = deciderEntry.getEntryWeight();
        command.execute();

        Assert.assertNotEquals(name, deciderEntry.getEntryName());
        Assert.assertNotEquals(weight, deciderEntry.getEntryWeight());
    }

    @Test
    public void undoRenameAndChangeEntryCommand() {
        final DeciderEntry deciderEntry = generator.generateDeciderEntry();
        final ManipulateDeciderEntryCommand command = generator.generateRenameAndChangeEntryWeightCommand(deciderEntry);
        final String name = deciderEntry.getEntryName();
        final int weight = deciderEntry.getEntryWeight();
        command.execute();
        command.undo();

        Assert.assertEquals(name, deciderEntry.getEntryName());
        Assert.assertEquals(weight, deciderEntry.getEntryWeight());
    }
}
