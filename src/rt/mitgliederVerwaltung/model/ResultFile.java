package rt.mitgliederVerwaltung.model;

import rt.mitgliederVerwaltung.controller.io.CSVParser;
import rt.mitgliederVerwaltung.controller.io.Reader;
import javafx.beans.property.SimpleStringProperty;

import java.io.File;
import java.util.*;

public class ResultFile {

    private final SimpleStringProperty name;
    private final SimpleStringProperty hash;
    private final SimpleStringProperty sch = new SimpleStringProperty("");

    private String resultFile;
    private String starterFile;
    private String modFile;
    private String resultHash;
    private boolean newFile = true;
    private boolean changedFile = true;

    public ResultFile(String resultFile, String resultHash) {
        this.resultFile = resultFile+".csv";
        this.starterFile = resultFile+"_stl.csv";
        this.modFile = resultFile+"_mod.csv";
        this.resultHash = resultHash;

        this.name = new SimpleStringProperty(resultFile);
        this.hash = new SimpleStringProperty(resultHash);
    }

    public ResultFile(String resultFile, String resultHash, String result) {
        this.resultFile = resultFile+".csv";
        this.starterFile = resultFile+"_stl.csv";
        this.modFile = resultFile+"_mod.csv";
        this.resultHash = resultHash;

        this.name = new SimpleStringProperty(resultFile);
        this.hash = new SimpleStringProperty(resultHash);
        setSch(result);
    }

    public boolean isChangedFile() {
        return changedFile;
    }

    public void setChangedFile(boolean changedFile) {
        this.changedFile = changedFile;
    }

    public String getResultFile() {
        return resultFile;
    }

    public String getStarterFile() {
        return starterFile;
    }

    public String getModFile() {
        return modFile;
    }
    public String getResultHash() {
        return resultHash;
    }

    public void setNew(boolean newFile) {
        this.newFile = newFile;
    }

    public boolean isNewFile() {
        return newFile;
    }

    public void setSch(String sch) {
        this.sch.set(sch);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getHash() {
        return hash.get();
    }

    public SimpleStringProperty hashProperty() {
        return hash;
    }

    public String getSch() {
        return sch.get();
    }

    public SimpleStringProperty schProperty() {
        return sch;
    }

    public void loadFile() {
        List<String[]> file = CSVParser.ladeErgebnissDatei(new File(new Config(new Reader().readConfig()).getSetting(Config.PFAD_DATEN)+this.resultFile));
        List<String> schuetzen = analyze(file);
        setSch(schuetzen.toString());
    }

    private List<String> analyze(List<String[]> file) {
        Map<String, Float> test = new HashMap<>();

        for(String[] line : file){
            if (!test.containsKey(line[0])){
                test.put(line[0], Float.parseFloat(line[1]));
            } else {
                test.put(line[0], test.get(line[0])+Float.parseFloat(line[1]));
            }
        }

        List<String> erg = new ArrayList<>();
        Iterator it = test.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            erg.add(pair.getKey()+":"+pair.getValue());
        }

        return erg;
    }
}
