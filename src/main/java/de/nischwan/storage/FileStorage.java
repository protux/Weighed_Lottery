package de.nischwan.storage;

import de.nischwan.storage.exceptions.CorruptDataFileException;
import de.nischwan.storage.exceptions.LoadingDataFailedException;
import de.nischwan.storage.exceptions.NothingToSaveException;
import de.nischwan.storage.exceptions.ParentDirectoriesCouldNotBeCreatedException;
import de.nischwan.storage.exceptions.SavingDataFailedException;
import de.nischwan.storage.utils.FileStorageUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * This Storage implementation saves the data to a text file on the local filesystem.
 *
 * @author Nico Schwanebeck
 */
public class FileStorage implements Storage {

    private static final String DEFAULT_DESTINATION = "/tmp/decider_files/save";
    private File destinationFile;

    /**
     * Saves to and loads from {@value DEFAULT_DESTINATION}.
     */
    public FileStorage() {
        destinationFile = new File(DEFAULT_DESTINATION);
    }

    /**
     * @param destinationFile the file to save to and to load from.
     */
    public FileStorage(final File destinationFile) {
        this.destinationFile = destinationFile;
    }

    public void setDestinationFile(File destinationFile) {
        this.destinationFile = destinationFile;
    }

    @Override
    public void save(final List<DeciderEntry> deciderEntries) {
        FileStorageUtils.throwIfEmptyEntries(deciderEntries);
        FileStorageUtils.createDestinationDirectories(destinationFile);
        final List<String> lines = FileStorageUtils.buildFileContent(deciderEntries);

        try {
            Files.write(destinationFile.toPath(), lines);
        } catch (final IOException e) {
            throw new SavingDataFailedException(e);
        }
    }

    @Override
    public List<DeciderEntry> load() {
        final List<DeciderEntry> entries = new ArrayList<>();

        try {
            final List<String> lines = Files.readAllLines(destinationFile.toPath());

            lines.forEach(line -> {
                final String textLine = line.trim();
                if (!textLine.isEmpty()) {
                    entries.add(FileStorageUtils.parseEntry(textLine));
                }
            });
        } catch (final IOException e) {
            throw new LoadingDataFailedException(e);
        }

        return entries;
    }
}
