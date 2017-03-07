package rt.mitgliederVerwaltung.controller;

import rt.mitgliederVerwaltung.controller.io.CSVParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import rt.mitgliederVerwaltung.model.Result;
import rt.mitgliederVerwaltung.model.ResultFile;
import rt.mitgliederVerwaltung.model.Shot;

import java.util.ArrayList;
import java.util.List;

public class TabellenAnsichtWettkampf {

    List<Result> results;
    @FXML public TableView<Result> ergebnisListe;
    ObservableList<Result> ergebnisse = FXCollections.observableArrayList();

    public void init(ResultFile wettkampf){
        results = CSVParser.getResult(wettkampf);
        ergebnisListe.setItems(ergebnisse);

        for (Result result : results) {
            if(result.getShots().size() > 0){
                result.generateTableData();
                genCSV(result);
                ergebnisse.add(result);
            }
        }
    }

    public void genCSV(Result result){
        List<Integer> shots = new ArrayList<>();
        List<Integer> serie = new ArrayList<>();
        int gesamt = 0;
        System.out.print(result.getName()+";");
        for (Shot shot : result.getShots()){
            if (shot.getSchussart() != 0) shots.add((int) shot.getPrimaerwertung());
        }
int count = 1;
        int se = 0;
        for (int i = 0;i < 41; i++){
                      if (count < 10){
                          count ++;
                          se += shots.get(i);
                      } else {
                          se += shots.get(i);
                          serie.add(se);
                          se = 0;
                          count = 1;
                      }

        }
        for (int s : serie) gesamt += s;

        System.out.print(" Gesamt: ;" + gesamt+"; Teiler: ;"+result.getShots().get(result.getShots().size()-1).getTeiler()+"; Serien: ;");

        for(int i : serie) System.out.print(i+";");

        System.out.print(" Schüsse: ;");

        for(int s : shots) System.out.print(s+";");




        System.out.println();
    }


    @FXML
    protected void initialize() {

    }
}
