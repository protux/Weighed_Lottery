package de.nischwan.storage.exceptions;

import de.nischwan.exceptions.DeciderBaseException;
import de.nischwan.l10n.LocalizationUtility;

/**
 * This exception is thrown if the data could not be loaded by the storage.
 *
 * @author Nico Schwanebeck
 */
public class LoadingDataFailedException extends DeciderBaseException {
    private static final String L10N_KEY = LoadingDataFailedException.class.getSimpleName() + ".message";

    public LoadingDataFailedException(final Throwable cause) {
        super(LocalizationUtility.getString(L10N_KEY), cause);
    }
}
