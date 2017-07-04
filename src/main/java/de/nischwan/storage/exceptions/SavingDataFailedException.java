package de.nischwan.storage.exceptions;

import de.nischwan.exceptions.DeciderBaseException;
import de.nischwan.l10n.LocalizationUtility;

/**
 * This exception is thrown if the storage could not save the data.
 *
 * @author Nico Schwanebeck
 */
public class SavingDataFailedException extends DeciderBaseException {

    private static final String L10N_KEY = SavingDataFailedException.class.getSimpleName() + ".message";

    public SavingDataFailedException(final Throwable cause) {
        super(LocalizationUtility.getString(L10N_KEY), cause);
    }
}
