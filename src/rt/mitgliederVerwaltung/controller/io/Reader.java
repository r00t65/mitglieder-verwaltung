package rt.mitgliederVerwaltung.controller.io;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rt.mitgliederVerwaltung.model.Mitglied;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List<String> readConfig(){
        File file = new File("data/config.sbk");
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(file.toPath(), Charset.defaultCharset() );
        } catch (IOException e) {
        }
        return list;
    }

    public ObservableList<Mitglied> readMitglied(String path){
        File file = new File(path);

        ObservableList<Mitglied> mitgliederListe = FXCollections.observableArrayList();

        for (Mitglied aM : CSVParser.ladeMitgliederListe(file)) {
            mitgliederListe.add(aM);
        }

        return mitgliederListe;
    }
}
