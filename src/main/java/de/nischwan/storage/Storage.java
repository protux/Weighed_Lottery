package de.nischwan.storage;

import java.util.List;

/**
 * This interface defines a storage to load and save DeciderEntries.
 *
 * @author Nico Schwanebeck
 */
public interface Storage {
    /**
     * This method saves all entries to the implemented destination.
     *
     * @param deciderEntries the entries to save.
     */
    void save(final List<DeciderEntry> deciderEntries);

    /**
     * This method loads all entries from the storage.
     *
     * @return all loaded entries.
     */
    List<DeciderEntry> load();
}
