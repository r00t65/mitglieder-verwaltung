package rt.mitgliederVerwaltung.view;
import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import rt.mitgliederVerwaltung.MainApp;

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

            loader.setLocation(MainApp.class.getResource("view/MitgliederAnsicht.fxml"));
            this.mitglieder.getChildren().setAll((AnchorPane) loader.load());
            MitgliederAnsichtController mac = loader.getController();
            mac.setMainApp(mainApp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}