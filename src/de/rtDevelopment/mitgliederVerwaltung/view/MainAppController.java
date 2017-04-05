package de.rtDevelopment.mitgliederVerwaltung.view;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import de.rtDevelopment.mitgliederVerwaltung.MainApp;

public class MainAppController extends BorderPane {

    private MainApp mainApp;

    @FXML private AnchorPane mitglieder;

    public MainAppController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( "MainApp.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML protected void initialize() {

        FXMLLoader loader = new FXMLLoader();

        try {

            loader.setLocation(MainApp.class.getResource("view/MemberTab.fxml"));
            this.mitglieder.getChildren().setAll((AnchorPane) loader.load());
            MemberTabController mac = loader.getController();
            mac.setMainApp(mainApp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}