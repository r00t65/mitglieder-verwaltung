package rt.mitgliederVerwaltung.model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Mitglied {

    private final SimpleStringProperty id;
    private final SimpleStringProperty ausweisNr;
    private final SimpleStringProperty name;
    private final SimpleStringProperty vorname;
    private final SimpleStringProperty strasse;
    private final SimpleStringProperty postleitzahl;
    private final SimpleStringProperty ort;
    private final SimpleStringProperty geb;
    private final SimpleStringProperty eintritt;
    private final SimpleStringProperty eMail;

    public Mitglied (){
        this.id = new SimpleStringProperty("0");
        this.ausweisNr = new SimpleStringProperty("123");
        this.name = new SimpleStringProperty("Rüpplein");
        this.vorname = new SimpleStringProperty("Patrick");
        this.strasse = new SimpleStringProperty("Am Hang 7a");
        this.postleitzahl = new SimpleStringProperty("95182");
        this.ort = new SimpleStringProperty("Tauperlitz");
        this.geb = new SimpleStringProperty("1");
        this.eintritt = new SimpleStringProperty("1");
        this.eMail = new SimpleStringProperty("2");
    }

    public Mitglied (String id, String ausweisNr, String name, String vorname, String strasse, String postleitzahl, String ort, String geb, String eintritt, String eMail){
        this.id = new SimpleStringProperty(id);
        this.ausweisNr = new SimpleStringProperty(ausweisNr);
        this.name = new SimpleStringProperty(name);
        this.vorname = new SimpleStringProperty(vorname);
        this.strasse = new SimpleStringProperty(strasse);
        this.postleitzahl = new SimpleStringProperty(postleitzahl);
        this.ort = new SimpleStringProperty(ort);
        this.geb = new SimpleStringProperty(geb);
        this.eintritt = new SimpleStringProperty(eintritt);
        this.eMail = new SimpleStringProperty(eMail);
    }

    public List<TableViewColumn> getTableViewColumns(){
        List<TableViewColumn> tableViewColumns = new ArrayList<>();
        return tableViewColumns;
    }


    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getAusweisNr() {
        return ausweisNr.get();
    }

    public SimpleStringProperty ausweisNrProperty() {
        return ausweisNr;
    }

    public void setAusweisNr(String ausweisNr) {
        this.ausweisNr.set(ausweisNr);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getVorname() {
        return vorname.get();
    }

    public SimpleStringProperty vornameProperty() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname.set(vorname);
    }

    public String getStrasse() {
        return strasse.get();
    }

    public SimpleStringProperty strasseProperty() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse.set(strasse);
    }

    public String getPostleitzahl() {
        return postleitzahl.get();
    }

    public SimpleStringProperty postleitzahlProperty() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl.set(postleitzahl);
    }

    public String getOrt() {
        return ort.get();
    }

    public SimpleStringProperty ortProperty() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort.set(ort);
    }

    public String getGeb() {
        return geb.get();
    }

    public SimpleStringProperty gebProperty() {
        return geb;
    }

    public void setGeb(String geb) {
        this.geb.set(geb);
    }

    public String getEintritt() {
        return eintritt.get();
    }

    public SimpleStringProperty eintrittProperty() {
        return eintritt;
    }

    public void setEintritt(String eintritt) {
        this.eintritt.set(eintritt);
    }

    public String geteMail() {
        return eMail.get();
    }

    public SimpleStringProperty eMailProperty() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail.set(eMail);
    }
}
