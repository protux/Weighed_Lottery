package de.nischwan.control;

import de.nischwan.control.exceptions.DeciderEntryNotFoundException;
import de.nischwan.control.exceptions.EntryAlreadyExistException;
import de.nischwan.storage.DeciderEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author Nico Schwanebeck
 */
public class TestDeciderEntryController {

    private DeciderEntryController deciderEntryController;
    private static final String ENTRY_NAME = "entry42";
    private static final int ENTRY_WEIGHT = 23;

    @Before
    public void setUp() {
        deciderEntryController = new DeciderEntryController(null);
    }

    @Test
    public void addEntry() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);

        final List<DeciderEntry> entries = deciderEntryController.getDeciderEntries();
        Assert.assertEquals(1, entries.size());
        final DeciderEntry entry = entries.get(0);
        Assert.assertEquals(ENTRY_NAME, entry.getEntryName());
        Assert.assertEquals(ENTRY_WEIGHT, entry.getEntryWeight());
    }

    @Test(expected = EntryAlreadyExistException.class)
    public void addAlreadyExistingEntry() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
    }

    @Test
    public void removeEntry() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.addEntry(ENTRY_NAME + ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.removeEntry(ENTRY_NAME);

        final List<DeciderEntry> entries = deciderEntryController.getDeciderEntries();
        Assert.assertEquals(1, entries.size());
        final DeciderEntry entry = entries.get(0);
        Assert.assertEquals(ENTRY_NAME + ENTRY_NAME, entry.getEntryName());
    }

    @Test
    public void renameEntry() {
        final String newName = ENTRY_NAME + ENTRY_NAME;
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.renameEntry(ENTRY_NAME, newName);

        final List<DeciderEntry> entries = deciderEntryController.getDeciderEntries();
        final DeciderEntry entry = entries.get(0);
        Assert.assertEquals(newName, entry.getEntryName());
    }

    @Test
    public void changeEntryWeight() {
        final int newWeight = 42;
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.changeEntryWeight(ENTRY_NAME, newWeight);

        final List<DeciderEntry> entries = deciderEntryController.getDeciderEntries();
        final DeciderEntry entry = entries.get(0);
        Assert.assertEquals(newWeight, entry.getEntryWeight());
    }

    @Test
    public void changeEntryNameAndWeight() {
        final String newName = ENTRY_NAME + ENTRY_NAME;
        final int newWeight = 42;
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.changeNameAndWeight(ENTRY_NAME, newName, newWeight);

        final List<DeciderEntry> entries = deciderEntryController.getDeciderEntries();
        Assert.assertEquals(1, entries.size());
        final DeciderEntry entry = entries.get(0);
        Assert.assertEquals(newName, entry.getEntryName());
        Assert.assertEquals(newWeight, entry.getEntryWeight());
    }

    @Test
    public void canRedo() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.undo();
        Assert.assertTrue(deciderEntryController.canRedo());
    }

    @Test
    public void canNotRedo() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        Assert.assertFalse(deciderEntryController.canRedo());
    }

    @Test
    public void canUndo() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        Assert.assertTrue(deciderEntryController.canUndo());
    }

    @Test
    public void canNotUndo() {
        Assert.assertFalse(deciderEntryController.canUndo());
    }

    @Test
    public void redo() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.undo();
        deciderEntryController.redo();

        final List<DeciderEntry> entries = deciderEntryController.getDeciderEntries();
        Assert.assertEquals(1, entries.size());
        final DeciderEntry entry = entries.get(0);
        Assert.assertEquals(ENTRY_NAME, entry.getEntryName());
        Assert.assertEquals(ENTRY_WEIGHT, entry.getEntryWeight());
    }

    @Test
    public void undo() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.undo();

        Assert.assertTrue(deciderEntryController.getDeciderEntries().isEmpty());
    }

    @Test(expected = DeciderEntryNotFoundException.class)
    public void doActionWithNonExistentItem() {
        deciderEntryController.changeEntryWeight("non existent", 4711);
    }
}
