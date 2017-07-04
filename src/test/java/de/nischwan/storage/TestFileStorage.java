package de.nischwan.storage;

import de.nischwan.control.DeciderEntryController;
import de.nischwan.storage.exceptions.CorruptDataFileException;
import de.nischwan.storage.exceptions.NothingToSaveException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nico Schwanebeck
 */
public class TestFileStorage {

    private static final File TEST_DIRECTORY = new File("/tmp/test/file_storage/");
    private static final File TEST_FILE = new File(TEST_DIRECTORY, "testfile");
    private static final String ENTRY_NAME = "entry42";
    private static final int ENTRY_WEIGHT = 23;

    private DeciderEntryController deciderEntryController;

    @Before
    public void setUp() {
        deciderEntryController = new DeciderEntryController(new FileStorage(TEST_FILE));
    }

    @After
    public void tearDown() {
        TEST_FILE.delete();
        TEST_DIRECTORY.delete();
    }

    /**
     * Tests if a file is saved successfully.
     */
    @Test
    public void saveEntriesSuccess() throws IOException {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.addEntry(ENTRY_NAME + ENTRY_WEIGHT, ENTRY_WEIGHT);
        deciderEntryController.save();

        final List<String> lines = Files.readAllLines(TEST_FILE.toPath());
        Assert.assertEquals(2, lines.size());
        Assert.assertEquals(ENTRY_NAME + "\t" + ENTRY_WEIGHT, lines.get(0));
        Assert.assertEquals(ENTRY_NAME + ENTRY_WEIGHT + "\t" + ENTRY_WEIGHT, lines.get(1));
    }

    /**
     * Tests if an empty entry list is not saved.
     */
    @Test(expected = NothingToSaveException.class)
    public void saveEntriesEmptyEntryList() {
        deciderEntryController.save();
    }

    /**
     * Tests if a valid file is loaded successfully.
     */
    @Test
    public void loadEntriesSuccess() {
        deciderEntryController.addEntry(ENTRY_NAME, ENTRY_WEIGHT);
        deciderEntryController.save();
        deciderEntryController = new DeciderEntryController(new FileStorage(TEST_FILE));
        deciderEntryController.load();

        List<DeciderEntry> entries = deciderEntryController.getDeciderEntries();
        Assert.assertEquals(1, entries.size());
        DeciderEntry entry = entries.get(0);
        Assert.assertEquals(ENTRY_NAME, entry.getEntryName());
        Assert.assertEquals(ENTRY_WEIGHT, entry.getEntryWeight());
    }

    /**
     * Tests if a {@link CorruptDataFileException} is thrown if a data line is incomplete.
     */
    @Test(expected = CorruptDataFileException.class)
    public void loadEntriesIncompleteData() throws IOException {
        String name = "NameOrWeight";
        TEST_DIRECTORY.mkdirs();
        Files.write(TEST_FILE.toPath(), name.getBytes());

        deciderEntryController.load();
    }

    /**
     * Tests if a {@link CorruptDataFileException} is thrown if the weight is not a number.
     */
    @Test(expected = CorruptDataFileException.class)
    public void loadEntriesWeightNotANumber() throws IOException {

        String entry = "Name\tWeight";
        TEST_DIRECTORY.mkdirs();
        Files.write(TEST_FILE.toPath(), entry.getBytes());

        deciderEntryController.load();
    }
}
