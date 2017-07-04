package de.nischwan.storage.exceptions;

import de.nischwan.exceptions.DeciderBaseException;
import de.nischwan.l10n.LocalizationUtility;

import java.io.File;

/**
 * This Exception is thrown if a directory could not be created.
 *
 * @author Nico Schwanebeck
 */
public class ParentDirectoriesCouldNotBeCreatedException extends DeciderBaseException {
    private static final String L10N_KEY = ParentDirectoriesCouldNotBeCreatedException.class.getSimpleName() + ".message";

    public ParentDirectoriesCouldNotBeCreatedException(final File destination) {
        super(LocalizationUtility.getFormattedString(L10N_KEY, destination.getAbsolutePath()));
    }
}
