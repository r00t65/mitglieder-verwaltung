package rt.mitgliederVerwaltung.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import rt.mitgliederVerwaltung.MainApp;
import rt.mitgliederVerwaltung.controller.TabellenAnsichtWettkampf;
import rt.mitgliederVerwaltung.controller.io.Database;
import rt.mitgliederVerwaltung.controller.io.Reader;
import rt.mitgliederVerwaltung.controller.io.Writer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import rt.mitgliederVerwaltung.model.Config;
import rt.mitgliederVerwaltung.model.Mitglied;
import rt.mitgliederVerwaltung.model.ResultFile;
import rt.mitgliederVerwaltung.model.TableViewColumn;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MitgliederAnsichtController {
     AnchorPane root;

    private MainApp mainApp;
    private MainApp main;

    private Config config;
    private FileChooser fileChooser = new FileChooser();

    @FXML
    private GridPane detailView;
    @FXML
    private TableView<ObservableList> mitgliederListe;
    private ObservableList<Mitglied> mitglieder = FXCollections.observableArrayList();

    private void addColumnsDynamicly() {
        List<TableViewColumn> tableViewColumns = new Mitglied().getTableViewColumns();
        for (int i = 0; i < tableViewColumns.size(); i++) {
            if (tableViewColumns.get(i).isShowInTable()) {
                TableColumn tc = new TableColumn(tableViewColumns.get(i).getName());
                tc.setCellValueFactory(new PropertyValueFactory<Member, String>(tableViewColumns.get(i).getFxid()));
                mitgliederListe.getColumns().add(tc);
            }
        }
    }

    private void addDetailDynamicly() {
        List<TableViewColumn> tableViewColumns = new Mitglied().getTableViewColumns();
        for (int i = 0; i < tableViewColumns.size(); i++) {
            if (i > 0) detailView.addRow(i);
            detailView.add(new Label(tableViewColumns.get(i).getName()), 0, i);
        }
    }

//    public MitgliederAnsichtController(MainApp main){
//
//        this.main = main;
//
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( "MitgliederAnsicht.fxml"));
////        fxmlLoader.setRoot(this);
////        fxmlLoader.setController(this);
//
//        try {
//            root = fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//    }

    @FXML
    public void initialize() {
//        config = new Config(new Reader().readConfig());
fillTable();
//        addColumnsDynamicly();

//        addDetailDynamicly();

//        mitgliederListe.setItems(mitglieder);
//        mitglieder.addAll(Database.getMitgliederliste());
        mitglieder.add(new Mitglied());

        showPersonDetails(null);
//        mitgliederListe.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }


    @FXML
    public TableView<ResultFile> ergebnisTableView;
    ObservableList<ResultFile> ergebnisse = FXCollections.observableArrayList();

    @FXML
    public TabPane tabPane;

//        ergebnisTableView.setItems(ergebnisse);
//        ergebnisse.addAll(new ResultReader(config.getSetting(Config.PFAD_DATEN)).load());

    private List<TableViewColumn> cols = new ArrayList();
    private List<List> rows = new ArrayList();

    ObservableList<ObservableList> data;

    public void fillTable() {
        cols.add(new TableViewColumn("id", "Mitgliedsnummer",  true));
        cols.add(new TableViewColumn("ausweisNr", "Ausweisnummer",  false));
        cols.add(new TableViewColumn("name", "Nachname",  true));
        cols.add(new TableViewColumn("vorname", "Vorname",  true));
        cols.add(new TableViewColumn("strasse", "Straße",  false));
        cols.add(new TableViewColumn("postleitzahl", "Postleitzahl",  false));
        cols.add(new TableViewColumn("ort", "Ort",  false));
        cols.add(new TableViewColumn("geb", "Geburtstag",  true));
        cols.add(new TableViewColumn("eintritt", "Eintritt",  false));
        cols.add(new TableViewColumn("eMail", "e-Mail",  false));

        for (int i = 0; i < cols.size(); i++) {

            final int j = i;
            if (!cols.get(i).isShowInTable()) continue;
            TableColumn col = new TableColumn(cols.get(i).getName());
            col.setId(cols.get(i).getFxid());

            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            mitgliederListe.getColumns().addAll(col);
        }

        Iterator row = rows.iterator();
        while (row.hasNext()) {

            ObservableList<String> rowEntry = FXCollections.observableArrayList();
//            for (int i = 1; i <= row.size(); i++) {
//                rowEntry.add(row.get(i));
//            }

            data.add(rowEntry);
            row.next();
        }

        mitgliederListe.setItems(data);
    }

    @FXML
    public void mitgliederDateiImportieren() {
        /*
        * Mitglieder Liste importieren
        * */

        fileChooser.setTitle("Mitgliederliste importieren");
        File file = fileChooser.showOpenDialog(mitgliederListe.getScene().getWindow());

        config.setSetting(Config.PFAD_MITGLIEDERLISTE, file.getAbsolutePath());
        new Writer().writeConfig(config.exportConfig());

        List<Mitglied> mitglieder = new Reader().readMitglied(config.getSetting(Config.PFAD_MITGLIEDERLISTE));

        Database.loadMitglieder(mitglieder);

    }

    @FXML
    public void eintragLoeschen(ActionEvent actionEvent) {
/*
 *      Eintrag aus TableView Löschen
 */
        for (Object anAuswahl : mitgliederListe.getSelectionModel().getSelectedIndices()) {
            mitglieder.remove((int) anAuswahl);
        }

    }

    @FXML
    public void resultView(ActionEvent actionEvent) {
        String tabID = tabPane.getSelectionModel().getSelectedItem().getId();
        switch (tabID) {
            case "tabMember":

                break;
            case "tabResults":
                ResultFile selection = ergebnisTableView.getSelectionModel().getSelectedItem();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("MitgliederAnsicht.fxml"));
                Stage stage = new Stage();
//                stage.initStyle(StageStyle.UNDECORATED);
                stage.setTitle(selection.getName());
                Scene scene = null;
                try {
                    scene = new Scene(loader.load(), 1024, 600);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TabellenAnsichtWettkampf controller = loader.getController();
                controller.init(selection);

                stage.setScene(scene);

                stage.show();
                break;
        }
    }

    @FXML
    public void editMember(ActionEvent actionEvent) {

//        Mitglied selectedPerson = mitgliederListe.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
//                showPersonDetails(selectedPerson);
            }

//        } else {
//            showAlertDialog();
//        }
//    }

    @FXML
    public void delMember(ActionEvent actionEvent) {
        int selectedIndex = mitgliederListe.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            mitgliederListe.getItems().remove(selectedIndex);
        } else {
            showAlertDialog();
        }
    }

    @FXML
    public void addMember(ActionEvent actionEvent) {

//        Person tempPerson = new Person();
//        boolean okClicked = mainApp.showPersonEditDialog(null);
//        if (okClicked) {
////            mainApp.getPersonData().add(tempPerson);
//        }
    }


    public void showAlertDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.initOwner(mainApp.getStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void showPersonDetails(Mitglied mitglied) {
        if (mitglied != null) {

            ObservableList<Node> childrens = detailView.getChildren();
            for (Node node : childrens) {
                if (detailView.getColumnIndex(node) == 1) {
                    detailView.getChildren().remove(node);
                    break;
                }
            }

            for (int i = 0; i < mitglied.getTableViewColumns().size(); i++) {
//                detailView.add(new Label(mitglied.getTableViewColumns().get(i).getData().toString()), 1, i);
            }
//            detailView.add(new Label(mitglied.getId()), 1, 0);
//            detailView.add(new Label(mitglied.getAusweisNr()), 1, 1);
//            detailView.add(new Label(mitglied.getName()), 1, 2);
//            detailView.add(new Label(mitglied.getVorname()), 1, 3);
//            detailView.add(new Label(mitglied.getStrasse()), 1, 4);
//            detailView.add(new Label(mitglied.getPostleitzahl()), 1, 5);
//            detailView.add(new Label(mitglied.getOrt()), 1, 6);
//            detailView.add(new Label(mitglied.getGeb()), 1, 7);
//            detailView.add(new Label(mitglied.getEintritt()), 1, 8);
//            detailView.add(new Label(mitglied.geteMail()), 1, 9);

            // TODO: We need a way to convert the birthday into a String!
        } else {

            ObservableList<Node> childrens = detailView.getChildren();
            for (Node node : childrens) {
                if (detailView.getColumnIndex(node) == 1) {
                    detailView.getChildren().remove(node);
                    break;
                }
            }
        }
    }
}
