package de.nischwan.gui.view;

import de.nischwan.storage.DeciderEntry;
import javafx.stage.Stage;

/**
 * @author Nico Schwanebeck
 */
public class EditEntryLayoutController {
    private Stage dialogStage;
    private DeciderEntry entry;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDeciderEntry(DeciderEntry deciderEntry) {
        this.entry = deciderEntry;
    }

    public boolean isOkClicked() {
        return false;
    }
}
