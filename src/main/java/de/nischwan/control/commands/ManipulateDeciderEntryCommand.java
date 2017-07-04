package de.nischwan.control.commands;

/**
 * This interface defines the methods every command needs. It contains methods for executing and undoing an action.
 *
 * @author Nico Schwanebeck
 */
public interface ManipulateDeciderEntryCommand {

    /**
     * Execute the action.
     */
    void execute();

    /**
     * Reverse the action of execute.
     */
    void undo();
}
