package de.nischwan.control.commands.utils;

import de.nischwan.control.commands.*;
import de.nischwan.storage.DeciderEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nico Schwanebeck
 */
public class CommandGenerator {

    public static final String CHANGE_NAME = "Peter";
    public static final int CHANGE_WEIGHT = 100;

    public static final String DECIDER_ENTRY_NAME = "Klaus";
    public static final int DECIDER_ENTRY_WEIGHT = 500;

    private static final int INITIAL_OFFSET = -1;

    private int changeOffset = INITIAL_OFFSET;
    private int deciderOffset = INITIAL_OFFSET;

    public void reset() {
        changeOffset = INITIAL_OFFSET;
        deciderOffset = INITIAL_OFFSET;
    }

    private String generateName(final String name, final int offset) {
        if (offset != 0) {
            return name + offset;
        }
        return name;
    }

    private int generateWeight(final int weight, final int offset) {
        if (offset != 0) {
            return weight + offset;
        }
        return weight;
    }

    public ManipulateDeciderEntryCommand generateCreateEntryCommand(final List<DeciderEntry> entries) {
        deciderOffset += 1;

        final String name = generateName(DECIDER_ENTRY_NAME, deciderOffset);
        final int weight = generateWeight(DECIDER_ENTRY_WEIGHT, deciderOffset);

        return new CreateEntryCommand(entries, name, weight);
    }

    public ManipulateDeciderEntryCommand generateChangeEntryWeightCommand(final DeciderEntry entry) {
        changeOffset += 1;
        return new ChangeEntryWeightCommand(entry, generateWeight(CHANGE_WEIGHT, changeOffset));
    }

    public ManipulateDeciderEntryCommand generateRemoveEntryCommand(final List<DeciderEntry> entries, final DeciderEntry entry) {
        return new RemoveEntryCommand(entries, entry);
    }

    public ManipulateDeciderEntryCommand generateRenameAndChangeEntryWeightCommand(final DeciderEntry entry) {
        changeOffset += 1;

        final String name = generateName(CHANGE_NAME, changeOffset);
        final int weight = generateWeight(CHANGE_WEIGHT, changeOffset);

        return new RenameAndChangeEntryWeightCommand(entry, name, weight);
    }

    public ManipulateDeciderEntryCommand generateRenameEntryCommand(final DeciderEntry entry) {
        changeOffset += 1;
        return new RenameEntryCommand(entry, generateName(CHANGE_NAME, changeOffset));
    }

    public DeciderEntry generateDeciderEntry() {
        deciderOffset += 1;

        final String name = generateName(DECIDER_ENTRY_NAME, deciderOffset);
        final int weight = generateWeight(DECIDER_ENTRY_WEIGHT, deciderOffset);

        return new DeciderEntry(name, weight);
    }

    public List<DeciderEntry> generateDeciderEntryList(final int entryCount) {
        final List<DeciderEntry> deciderEntries = new ArrayList<>();

        for (int i = 0; i < entryCount; i++) {
            deciderEntries.add(generateDeciderEntry());
        }

        return deciderEntries;
    }
}
