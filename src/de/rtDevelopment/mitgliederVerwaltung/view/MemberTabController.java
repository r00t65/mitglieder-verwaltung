package de.rtDevelopment.mitgliederVerwaltung.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import de.rtDevelopment.mitgliederVerwaltung.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import de.rtDevelopment.mitgliederVerwaltung.controller.Config;
import de.rtDevelopment.mitgliederVerwaltung.controller.Database;
import de.rtDevelopment.mitgliederVerwaltung.model.member.Member;
import de.rtDevelopment.mitgliederVerwaltung.model.member.MemberField;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MemberTabController {

    private List<Member> memberList;

    private MainApp mainApp;

    private FileChooser fileChooser = new FileChooser();

    @FXML private GridPane detailView;
    @FXML private TableView<ObservableList> mitgliederListe;
    private ObservableList<ObservableList> members = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        memberList = Database.read().collect(Collectors.toList());

        mitgliederListe.setItems(members);

        addColumns();

        addMembers();

//        members.addAll(DatabasePlugin.getMitgliederliste());

        mitgliederListe.
                getSelectionModel().
                selectedItemProperty().
                addListener((observable, oldValue, newValue) -> showDetail((String)newValue.get(0)));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void addColumns() {
        Stream<MemberField> fieldStream = Config.getMemberFields().stream();
        fieldStream.filter(MemberField::isVisible).forEach(this::addColumn);
    }

    private void addColumn(MemberField field) {
        TableColumn tc = new TableColumn(field.getName());
        int col = mitgliederListe.getColumns().size();
        tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(col).toString());
            }
        });

        mitgliederListe.getColumns().add(tc);
    }

    private void addMembers(){

        Stream<Member> membersStream = memberList.stream();
        membersStream.forEach(this::addMember);
    }

    private void addMember(Member member){

        ObservableList<String> row = FXCollections.observableArrayList();

        Config.getMemberFields().
                stream().
                filter(MemberField::isVisible).
                map(m->(String) member.getMember().getOrDefault(m.getAlias(), "---")).
                forEach(row::add);

        members.add(row);
    }

    private void showDetail(String id) {

        Member mitglied = memberList.stream()
                .filter(m->m.getMember().get("id").equals(id))
                .collect(Collectors.toList())
                .get(0);

        detailView.getChildren().clear();

        int c = 0;

        Iterator iter = Config.getMemberFields().iterator();
        while (iter.hasNext()){
            MemberField f = (MemberField) iter.next();
            detailView.add(new Label(f.getName()), 0, c);
            detailView.add(new Label((String)mitglied.getMember().getOrDefault(f.getAlias(), "---")), 1, c);
            c++;
        }

    }












//    TODO: Ab hier aufräumen

//    @FXML
//    public void mitgliederDateiImportieren() {
//        /*
//        * Mitglieder Liste importieren
//        * */
//
//        fileChooser.setTitle("Mitgliederliste importieren");
//        File file = fileChooser.showOpenDialog(mitgliederListe.getScene().getWindow());
//
////        config.setSetting(Config.PFAD_MITGLIEDERLISTE, file.getAbsolutePath());
////        new Writer().writeConfig(config.exportConfig());
//
////        List<Member> members = new Reader().readMitglied(config.getSetting(Config.PFAD_MITGLIEDERLISTE));
//
////        DatabasePlugin.loadMitglieder(members);
//
//    }

    @FXML
    public void eintragLoeschen(ActionEvent actionEvent) {
/*
 *      Eintrag aus TableView Löschen
 */
        for (Object anAuswahl : mitgliederListe.getSelectionModel().getSelectedIndices()) {
            members.remove((int) anAuswahl);
        }

    }

    @FXML
    public void editMember(ActionEvent actionEvent) {

//        Member selectedPerson = mitgliederListe.getSelectionModel().getSelectedItem();
//        if (selectedPerson != null) {
//            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
//            if (okClicked) {
//                showPersonDetails(selectedPerson);
    }

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


    public void showAlertDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.initOwner(mainApp.getStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
    }



    //    private void addDetailDynamicly() {
//        List<TableViewColumn> tableViewColumns = new Member().getTableViewColumns();
//        for (int i = 0; i < tableViewColumns.size(); i++) {
//            if (i > 0) detailView.addRow(i);
//            detailView.add(new Label(tableViewColumns.get(i).getName()), 0, i);
//        }
//        ergebnisTableView.setItems(ergebnisse);
//        } else {
//            showAlertDialog();
//        }
}
