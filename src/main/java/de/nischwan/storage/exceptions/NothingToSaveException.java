package de.nischwan.storage.exceptions;

import de.nischwan.exceptions.DeciderBaseException;
import de.nischwan.l10n.LocalizationUtility;

/**
 * This exception is used if the user tries to save an empty list.
 *
 * @author Nico Schwanebeck
 */
public class NothingToSaveException extends DeciderBaseException {

    private static final String L10N_KEY = NothingToSaveException.class.getSimpleName() + ".message";

    public NothingToSaveException() {
        super(LocalizationUtility.getString(L10N_KEY));
    }
}
