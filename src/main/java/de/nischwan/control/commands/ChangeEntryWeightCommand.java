package de.nischwan.control.commands;

import de.nischwan.storage.DeciderEntry;

/**
 * This command changes the weight of an entry.
 *
 * @author Nico Schwanebeck
 */
public class ChangeEntryWeightCommand implements ManipulateDeciderEntryCommand {

    private final DeciderEntry deciderEntry;
    private final int newWeight;
    private int oldWeight;

    public ChangeEntryWeightCommand(final DeciderEntry deciderEntry, final int newWeight) {
        this.deciderEntry = deciderEntry;
        this.newWeight = newWeight;
    }

    @Override
    public void execute() {
        oldWeight = deciderEntry.getEntryWeight();
        deciderEntry.setEntryWeight(newWeight);
    }

    @Override
    public void undo() {
        deciderEntry.setEntryWeight(oldWeight);
    }
}
