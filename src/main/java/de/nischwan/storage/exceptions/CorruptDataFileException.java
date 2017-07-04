package de.nischwan.storage.exceptions;

import de.nischwan.exceptions.DeciderBaseException;
import de.nischwan.l10n.LocalizationUtility;

/**
 * This exception is thrown if a loaded data entry could not be loaded.
 *
 * @author Nico Schwanebeck
 */
public class CorruptDataFileException extends DeciderBaseException {
    private static final String L10N_KEY = CorruptDataFileException.class.getSimpleName() + ".message";

    public CorruptDataFileException(final String dataEntry) {
        super(LocalizationUtility.getFormattedString(L10N_KEY, dataEntry));
    }
}
