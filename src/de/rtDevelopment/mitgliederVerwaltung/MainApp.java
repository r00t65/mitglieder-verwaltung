package de.rtDevelopment.mitgliederVerwaltung;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import de.rtDevelopment.mitgliederVerwaltung.view.MainAppController;

public class MainApp extends Application {

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) {

        MainAppController controller = new MainAppController();
        controller.setMainApp(this);

        stage.setScene(new Scene(controller));
        stage.setTitle("SBK Manager");
        stage.show();

    }

//    public boolean showPersonEditDialog(Member mitglied) {
//        try {
//            FXMLLoader loader = new FXMLLoader(MemberTabController.class.getResource("MitgliederBearbeiten.fxml"));
//            AnchorPane page = loader.load();
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Member bearbeiten");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//            MitgliederBearbeitenController mitgliederBearbeitenController = loader.getController();
//            mitgliederBearbeitenController.setDialogStage(dialogStage);
//            mitgliederBearbeitenController.setMitglied(mitglied);
//
//            dialogStage.showAndWait();
//
//            return mitgliederBearbeitenController.isOkClicked();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
