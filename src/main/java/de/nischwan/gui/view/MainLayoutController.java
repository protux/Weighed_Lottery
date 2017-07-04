package de.nischwan.gui.view;

import de.nischwan.gui.FXMain;
import de.nischwan.storage.DeciderEntry;
import de.nischwan.storage.Winner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * @author Nico Schwanebeck
 */
public class MainLayoutController {
    private FXMain fxMain;

    @FXML
    private TableView<DeciderEntry> participantsTable;

    @FXML
    private TableColumn<DeciderEntry, String> participantNameColumn;

    @FXML
    private TableColumn<DeciderEntry, Number> participantTicketsColumn;

    @FXML
    private TableView<Winner> winnerTable;

    @FXML
    private TableColumn<Winner, Integer> rankColumn;

    @FXML
    private TableColumn<Winner, String> nameColumn;

    @FXML
    private Label overallTicketCount;

    @FXML
    private TextField possibleWinners;

    private ObservableList<Winner> winners = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        participantNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        participantTicketsColumn.setCellValueFactory(cellData -> cellData.getValue().getWeightProperty());
    }

    @FXML
    private void onNewClicked() {
        final DeciderEntry deciderEntry = new DeciderEntry("", 0);
        final boolean confirmed = fxMain.showEditDeciderEntryDialog(deciderEntry);
        if (confirmed) {
            // TODO execute add entry
            // TODO recalculate overall ticket count
        }
    }

    @FXML
    private void onEditClicked() {
        // TODO get selected entry and pass it to FXMain to open edit dialog
    }

    @FXML
    private void onDeleteClicked() {
        // TODO get selected and remove it
    }

    @FXML
    private void onGambleClicked() {
        // TODO gamble and add winners to list
    }

    public void setFxMain(FXMain fxMain) {
        this.fxMain = fxMain;
    }
}
