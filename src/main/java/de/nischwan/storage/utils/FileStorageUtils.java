package de.nischwan.storage.utils;

import de.nischwan.storage.DeciderEntry;
import de.nischwan.storage.exceptions.CorruptDataFileException;
import de.nischwan.storage.exceptions.NothingToSaveException;
import de.nischwan.storage.exceptions.ParentDirectoriesCouldNotBeCreatedException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides some utility methods for the {@link de.nischwan.storage.FileStorage FileStorage};
 *
 * @author Nico Schwanebeck
 */
public final class FileStorageUtils {

    private FileStorageUtils() {
        // Access restriction
    }

    /**
     * Takes the parent {@link File} of {@code destinationFile} and creates all directories, incl. all parent
     * Directories.
     *
     * @param destinationFile the destination file.
     */
    public static void createDestinationDirectories(final File destinationFile) {
        final File parentDirectory = destinationFile.getParentFile();
        if (!parentDirectory.exists() && !parentDirectory.mkdirs()) {
            throw new ParentDirectoriesCouldNotBeCreatedException(destinationFile);
        }
    }

    /**
     * @param deciderEntries a list of all decider entries.
     * @return a list of strings. Each string represents a line in the file to save.
     */
    public static List<String> buildFileContent(final List<DeciderEntry> deciderEntries) {
        final List<String> lines = new ArrayList<>();

        deciderEntries.forEach(deciderEntry -> {
            final String line = deciderEntry.getEntryName() + "\t" + deciderEntry.getEntryWeight();
            lines.add(line);
        });
        return lines;
    }

    /**
     * @param deciderEntries if this list of {@link DeciderEntry}s is empty a {@link NothingToSaveException} is thrown.
     */
    public static void throwIfEmptyEntries(final List<DeciderEntry> deciderEntries) {
        if (deciderEntries.isEmpty()) {
            throw new NothingToSaveException();
        }
    }

    /**
     * @param textLine one line from the save file.
     * @return a parsed {@link DeciderEntry} based on a line of the save file.
     */
    public static DeciderEntry parseEntry(final String textLine) {
        final String[] entry = textLine.split("\t");

        if (entry.length != 2 || !entry[1].matches("^[0-9]+$")) {
            throw new CorruptDataFileException(textLine);
        }

        return new DeciderEntry(entry[0], Integer.parseInt(entry[1]));
    }
}
