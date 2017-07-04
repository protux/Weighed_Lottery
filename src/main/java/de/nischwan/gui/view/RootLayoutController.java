package de.nischwan.gui.view;

import de.nischwan.gui.FXMain;
import javafx.fxml.FXML;

/**
 * @author Nico Schwanebeck
 */
public class RootLayoutController {
    private FXMain fxMain;

    public void setFxMain(FXMain fxMain) {
        this.fxMain = fxMain;
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}
