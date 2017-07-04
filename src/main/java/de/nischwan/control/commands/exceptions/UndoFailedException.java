package de.nischwan.control.commands.exceptions;

import de.nischwan.exceptions.DeciderBaseException;
import de.nischwan.l10n.LocalizationUtility;

/**
 * This Exception is used if an action could not be undone.
 *
 * @author Nico Schwanebeck
 */
public class UndoFailedException extends DeciderBaseException {
    private static final String L10N_KEY = UndoFailedException.class.getSimpleName() + ".message";

    public UndoFailedException() {
        super(LocalizationUtility.getString(L10N_KEY));
    }
}
