package rt.mitgliederVerwaltung.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private SimpleIntegerProperty id;
    private SimpleStringProperty namet;
    private SimpleIntegerProperty total;
    private SimpleDoubleProperty totalz;
    private SimpleStringProperty series;
    private SimpleStringProperty seriesz;
    private SimpleIntegerProperty best;

    private String idNr;
    private int stNr;
    private String name;
    private String vorname;
    private String anzeige;
    private String nat;
    private int kat;
    private int gruppe;
    private String team;
    private String bay;
    private int bahn;
    private int abloesung;

    private List<Shot> shots;

    public Result(String[] line) {
        this.idNr = line[0];
        this.stNr = Integer.parseInt(line[1]);
        this.name = line[2];
        this.vorname = line[3];
        this.anzeige = line[4];
        this.nat = line[5];
        this.kat = Integer.parseInt(line[6]);
        this.gruppe = Integer.parseInt(line[7]);
        this.team = line[8];
        this.bay = line[9];
        this.bahn = Integer.parseInt(line[10]);
        this.abloesung = Integer.parseInt(line[11]);
        shots = new ArrayList<>();
    }

    public void generateTableData(){
        id = new SimpleIntegerProperty(stNr);
        namet = new SimpleStringProperty(name);
        series = new SimpleStringProperty(genSeries());
        seriesz = new SimpleStringProperty(genSeriesz());
        total = new SimpleIntegerProperty(genTotal());
        totalz = new SimpleDoubleProperty(genTotalz());
        best = new SimpleIntegerProperty(genBest());
        }

    private int genBest() {
        int best = 99999;
        for (Shot shot : shots)if (shot.getSchussart() != 0 && shot.getTeiler() < best) best = shot.getTeiler();
        return best;
    }

    private String genSeriesz() {
        List<Double> series = new ArrayList<>();
        int count = 1;
        double total = 0;
        for (Shot shot : shots)if (shot.getSchussart() != 0){
            total += shot.getSekundärwertung();
            if (count < 10){
                count++;
            } else {
                count = 1;
                total = Math.round(total*10)/10.0;
                series.add(total);
                total = 0;
            }
        }
        if (total != 0)series.add(total);
        String ret = "";
        for (Double i : series) ret += i+" ";
        return ret;
    }

    private String genSeries() {
        List<Integer> series = new ArrayList<>();
        int count = 1;
        int total = 0;
        for (Shot shot : shots)if (shot.getSchussart() != 0){
            total += (int) shot.getPrimaerwertung();
            if (count < 10){
                count++;
            } else {
                count = 1;
                series.add(total);
                total = 0;
            }
        }
        if (total != 0)series.add(total);
        String ret = "";
        for (Integer i : series) ret += i+" ";
        return ret;
    }

    private double genTotalz() {
        double total = 0;
        for (Shot shot : shots)if (shot.getSchussart() != 0) total += shot.getSekundärwertung();
        total = Math.round(total*10)/10.0;
        return total;
    }

    private int genTotal() {
        int total = 0;
        for (Shot shot : shots)if (shot.getSchussart() != 0){
            total += (int) shot.getPrimaerwertung();
        }
        return total;
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getNamet() {
        return namet.get();
    }

    public SimpleStringProperty nametProperty() {
        return namet;
    }

    public int getTotal() {
        return total.get();
    }

    public SimpleIntegerProperty totalProperty() {
        return total;
    }

    public double getTotalz() {
        return totalz.get();
    }

    public SimpleDoubleProperty totalzProperty() {
        return totalz;
    }

    public String getSeries() {
        return series.get();
    }

    public SimpleStringProperty seriesProperty() {
        return series;
    }

    public String getSeriesz() {
        return seriesz.get();
    }

    public SimpleStringProperty serieszProperty() {
        return seriesz;
    }

    public int getBest() {
        return best.get();
    }

    public SimpleIntegerProperty bestProperty() {
        return best;
    }

    public String getIdNr() {
        return idNr;
    }

    public int getStNr() {
        return stNr;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getAnzeige() {
        return anzeige;
    }

    public String getNat() {
        return nat;
    }

    public int getKat() {
        return kat;
    }

    public int getGruppe() {
        return gruppe;
    }

    public String getTeam() {
        return team;
    }

    public String getBay() {
        return bay;
    }

    public int getBahn() {
        return bahn;
    }

    public int getAbloesung() {
        return abloesung;
    }

    public List<Shot> getShots() {
        return shots;
    }

    public void setShots(List<Shot> shots) {
        this.shots = shots;
    }
}
