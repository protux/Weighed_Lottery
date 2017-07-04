package de.nischwan.control.commands;

import de.nischwan.control.commands.utils.CommandGenerator;
import de.nischwan.storage.DeciderEntry;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nico Schwanebeck
 */
public class TestCreateEntryCommand {

    private final CommandGenerator generator = new CommandGenerator();

    @After
    public void tearDown() throws Exception {
        generator.reset();
    }

    /**
     * Tests if an entry is added correctly.
     */
    @Test
    public void executeCreateDeciderEntry() {
        final List<DeciderEntry> entries = new ArrayList<>();
        final ManipulateDeciderEntryCommand addCommand = generator.generateCreateEntryCommand(entries);
        addCommand.execute();

        Assert.assertEquals(1, entries.size());
        Assert.assertEquals(CommandGenerator.DECIDER_ENTRY_NAME, entries.get(0).getEntryName());
        Assert.assertEquals(CommandGenerator.DECIDER_ENTRY_WEIGHT, entries.get(0).getEntryWeight());
    }

    /**
     * Tests if an add entry action is undone correctly.
     */
    @Test
    public void undoCreateDeciderEntry() {
        final List<DeciderEntry> entries = new ArrayList<>();
        final ManipulateDeciderEntryCommand addCommand = generator.generateCreateEntryCommand(entries);
        addCommand.execute();
        final ManipulateDeciderEntryCommand addCommand2 = generator.generateCreateEntryCommand(entries);
        addCommand2.execute();
        final ManipulateDeciderEntryCommand addCommand3 = generator.generateCreateEntryCommand(entries);
        addCommand3.execute();

        addCommand3.undo();

        Assert.assertEquals(2, entries.size());
        Assert.assertEquals(CommandGenerator.DECIDER_ENTRY_NAME, entries.get(0).getEntryName());
        Assert.assertEquals(CommandGenerator.DECIDER_ENTRY_WEIGHT, entries.get(0).getEntryWeight());
        Assert.assertEquals(CommandGenerator.DECIDER_ENTRY_NAME + (entries.size() - 1), entries.get(1).getEntryName());
        Assert.assertEquals(CommandGenerator.DECIDER_ENTRY_WEIGHT + (entries.size() - 1), entries.get(1).getEntryWeight());
    }
}
