package de.nischwan.exceptions;

/**
 * This Exception is used as base for all other Exceptions in this program.
 *
 * @author Nico Schwanebeck
 */
public abstract class DeciderBaseException extends RuntimeException {

    public DeciderBaseException(final String message) {
        super(message);
    }

    public DeciderBaseException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
