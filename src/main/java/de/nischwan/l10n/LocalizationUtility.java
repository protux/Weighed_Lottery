package de.nischwan.l10n;

import java.util.ResourceBundle;

/**
 * This class offers some utilities for localization.
 *
 * @author Nico Schwanebeck
 */
public final class LocalizationUtility {
    private LocalizationUtility() {
    }

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("decider");

    /**
     * @param key the key to lookup in the {@link ResourceBundle}.
     * @return the String or throws {@link java.util.MissingResourceException} if the key does not exist.
     */
    public static String getString(final String key) {
        return resourceBundle.getString(key);
    }

    /**
     * @param key           the key to lookup in the {@link ResourceBundle}.
     * @param formatObjects the objects to format the String with.
     * @return the formatted String or throws {@link java.util.MissingResourceException} if the key does not exist.
     */
    public static String getFormattedString(final String key, final Object... formatObjects) {
        return String.format(resourceBundle.getString(key), formatObjects);
    }
}
