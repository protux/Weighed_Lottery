package de.nischwan.control.exceptions;

import de.nischwan.exceptions.DeciderBaseException;
import de.nischwan.l10n.LocalizationUtility;

/**
 * This exception is thrown if it is tried to add an entry with an already existing name.
 *
 * @author Nico Schwanebeck
 */
public class EntryAlreadyExistException extends DeciderBaseException {
    private static final String L10N_KEY = EntryAlreadyExistException.class.getSimpleName() + ".message";

    public EntryAlreadyExistException(final String entryName) {
        super(LocalizationUtility.getFormattedString(L10N_KEY, entryName));
    }
}
