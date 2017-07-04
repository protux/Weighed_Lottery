package de.nischwan.gui;

import de.nischwan.gui.view.EditEntryLayoutController;
import de.nischwan.gui.view.MainLayoutController;
import de.nischwan.gui.view.RootLayoutController;
import de.nischwan.storage.DeciderEntry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Nico Schwanebeck
 */
public class FXMain extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Lottery");

        initRootLayout();
        showMainView();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setFxMain(this);

            primaryStage.show();
        } catch (IOException e) {
            // TODO logging
        }
    }

    private void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/MainLayout.fxml"));

            AnchorPane mainLayout = loader.load();
            rootLayout.setCenter(mainLayout);

            MainLayoutController controller = loader.getController();
            controller.setFxMain(this);
        } catch (IOException e) {
            // TODO logging
        }
    }

    public boolean showEditDeciderEntryDialog(DeciderEntry deciderEntry) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("view/EditEntryLayout.fxml"));

            AnchorPane editEntryLayout = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Entry");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(editEntryLayout);
            dialogStage.setScene(scene);

            EditEntryLayoutController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDeciderEntry(deciderEntry);

            // TODO set icon

            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            // TODO logging
            return false;
        }
    }
}
