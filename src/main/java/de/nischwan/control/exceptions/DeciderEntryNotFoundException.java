package de.nischwan.control.exceptions;

import de.nischwan.exceptions.DeciderBaseException;
import de.nischwan.l10n.LocalizationUtility;

/**
 * This exception is thrown if no {@link de.nischwan.storage.DeciderEntry DeciderEntry} could be found during a lookup.
 *
 * @author Nico Schwanebeck
 */
public class DeciderEntryNotFoundException extends DeciderBaseException {
    private static final String L10N_KEY = DeciderEntryNotFoundException.class.getSimpleName() + ".message";

    public DeciderEntryNotFoundException(final String deciderEntryName) {
        super(LocalizationUtility.getFormattedString(L10N_KEY, deciderEntryName));
    }
}
