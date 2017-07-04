package de.nischwan.control.random;

import de.nischwan.storage.DeciderEntry;

import java.util.List;
import java.util.Random;

/**
 * This class provides the functionality to pick an entry from a list of
 * {@link de.nischwan.storage.DeciderEntry DeciderEntry}s.
 *
 * @author Nico Schwanebeck
 */
public class Decider {

    public DeciderEntry pick(final List<DeciderEntry> deciderEntries) {
        final int summedWeights = sumWeights(deciderEntries);
        calculateCumulativeWeights(deciderEntries, summedWeights);
        final Random random = new MTRandom();
        float randomNumber = random.nextFloat();

        for (final DeciderEntry entry : deciderEntries) {
            if (entry.getCumulativeWeight() > randomNumber) {
                return entry;
            }
            randomNumber -= entry.getCumulativeWeight();
        }

        return null;
    }

    //public DeciderEntry pick(final List<DeciderEntry> deciderEntries) {
    //    final int summedWeights = sumWeights(deciderEntries);
    //    calculateCumulativeWeights(deciderEntries, summedWeights);
    //    final Random random = new MTRandom();
    //
    //    DeciderEntry result = null;
    //    double bestValue = Double.MAX_VALUE;
    //
    //    for (DeciderEntry element : deciderEntries) {
    //        double value = -Math.log(random.nextDouble()) / (double) element.getEntryWeight();
    //
    //        if (value < bestValue) {
    //            bestValue = value;
    //            result = element;
    //        }
    //    }
    //
    //    return result;
    //}

    private int sumWeights(final List<DeciderEntry> deciderEntries) {
        int sum = 0;
        for (final DeciderEntry deciderEntry : deciderEntries) {
            sum += deciderEntry.getEntryWeight();
        }
        return sum;
    }

    private void calculateCumulativeWeights(final List<DeciderEntry> deciderEntries, final int summedWeights) {
        deciderEntries.forEach(entry -> entry.setCumulativeWeight(entry.getEntryWeight() / (float) summedWeights));
    }
}
