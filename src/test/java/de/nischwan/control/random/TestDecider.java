package de.nischwan.control.random;

import de.nischwan.storage.DeciderEntry;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Nico Schwanebeck
 */
public class TestDecider {

    private boolean isInRange(final int timesPicked, final int overallTries, final float cumulativeWeight, final float maxDifference) {
        final float probability = timesPicked / (float) overallTries;
        final float min = cumulativeWeight - maxDifference;
        final float max = cumulativeWeight + maxDifference;
        return probability >= min && probability <= max;
    }

    private DeciderEntry getEntryByName(final String name, final List<DeciderEntry> entries) {
        return entries.stream().filter(entry -> entry.getEntryName().equals(name)).findFirst().get();
    }

    @Test
    public void probabilities() {
        final List<DeciderEntry> deciderEntries = new ArrayList<>();
        deciderEntries.add(new DeciderEntry("0", 100));
        deciderEntries.add(new DeciderEntry("1", 200));
        deciderEntries.add(new DeciderEntry("2", 300));
        deciderEntries.add(new DeciderEntry("3", 400));

        final int[] results = new int[deciderEntries.size()];
        final Decider decider = new Decider();
        final int overallTries = 1_000_000;

        Collections.shuffle(deciderEntries);
        for (int i = 0; i < overallTries; i++) {
            final DeciderEntry result = decider.pick(deciderEntries);
            results[Integer.parseInt(result.getEntryName())] += 1;
        }

        for (int i = 0; i < deciderEntries.size(); i++) {
            final boolean inRange = isInRange(results[i], overallTries, getEntryByName(Integer.toString(i), deciderEntries).getCumulativeWeight(), 0.05f);
            final DeciderEntry entry = deciderEntries.get(i);
            if (!inRange) {
                Assert.fail("The probability of " + entry.getEntryName() + " must be close to "
                        + (entry.getEntryWeight() / 1000f) + " but was " + (results[i] / (float) overallTries));
            }
        }
    }
}
