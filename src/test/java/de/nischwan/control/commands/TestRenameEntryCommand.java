package de.nischwan.control.commands;

import de.nischwan.control.commands.utils.CommandGenerator;
import de.nischwan.storage.DeciderEntry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Nico Schwanebeck
 */
public class TestRenameEntryCommand {

    private CommandGenerator generator = new CommandGenerator();

    @After
    public void tearDown() {
        generator.reset();
    }

    @Test
    public void executeRenameEntryCommand() {
        final DeciderEntry deciderEntry = generator.generateDeciderEntry();
        final String name = deciderEntry.getEntryName();

        final ManipulateDeciderEntryCommand command = generator.generateRenameEntryCommand(deciderEntry);
        command.execute();

        Assert.assertNotEquals(name, deciderEntry.getEntryName());
    }

    @Test
    public void undoRenameEntryCommand() {
        final DeciderEntry deciderEntry = generator.generateDeciderEntry();
        final String name = deciderEntry.getEntryName();

        final ManipulateDeciderEntryCommand command = generator.generateRenameEntryCommand(deciderEntry);
        command.execute();
        command.undo();

        Assert.assertEquals(name, deciderEntry.getEntryName());
    }
}
