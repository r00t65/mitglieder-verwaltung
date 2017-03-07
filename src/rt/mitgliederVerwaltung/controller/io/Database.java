package rt.mitgliederVerwaltung.controller.io;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import rt.mitgliederVerwaltung.model.Mitglied;
import rt.mitgliederVerwaltung.model.ResultFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    public static List<Mitglied> getMitgliederliste(){
        ODatabaseDocumentTx db = new ODatabaseDocumentTx("plocal:data/db").open("admin", "admin");

        List<Mitglied> mitglieder = new ArrayList<>();

        if(db.getMetadata().getSchema().existsClass("Mitglied")){
            for (ODocument mitglied : db.browseClass("Mitglied")) {
//                mitglieder.add(new Mitglied(Integer.parseInt(mitglied.field("id").toString()),
//                        mitglied.field("name").toString(),
//                        mitglied.field("vorname").toString(),
//                        mitglied.field("geb").toString(),
//                        mitglied.field("str").toString(),
//                        mitglied.field("plz").toString(),
//                        mitglied.field("ort").toString(),
//                        mitglied.field("eintritt").toString()));
            }
        }

        db.close();
        return mitglieder;
    }

    public static Map<String, String> getImportedResultsMap(){
        ODatabaseDocumentTx db = new ODatabaseDocumentTx("plocal:data/db").open("admin", "admin");

        Map<String , String> importedResults = new HashMap<>();

        if(db.getMetadata().getSchema().existsClass("Ergebniss")){
            for (ODocument ergebniss : db.browseClass("Ergebniss")) {
                importedResults.put(ergebniss.field("name").toString(), ergebniss.field("hash").toString());
            }
        }

        db.close();
        return importedResults;
    }

    public static List<ResultFile> getResults(){
        ODatabaseDocumentTx db = new ODatabaseDocumentTx("plocal:data/db").open("admin", "admin");

        List<ResultFile> results = new ArrayList<>();

        if(db.getMetadata().getSchema().existsClass("Ergebniss")){
            for (ODocument e : db.browseClass("Ergebniss")) {
                results.add(new ResultFile(e.field("name").toString(), e.field("hash").toString(), e.field("ergebniss").toString()));
            }
        }

        db.close();
        return results;
    }

    public static void loadResults(List<ResultFile> newResults) {
        ODatabaseDocumentTx db = new ODatabaseDocumentTx("plocal:data/db").open("admin", "admin");

        for (ResultFile result : newResults) {
            if (result.isNewFile()) {
                result.loadFile();

                ODocument doc = new ODocument("Ergebniss");
                doc.field("name", result.getName());
                doc.field("hash", result.getResultHash());
                doc.field("ergebniss", result.getSch());

                doc.save();
            } else {
//                TODO: ResultFile aktualisieren
            }

        }

        db.close();
    }

    public static void loadMitglieder(List<Mitglied> mitglieder) {

        ODatabaseDocumentTx db = new ODatabaseDocumentTx("plocal:data/db").open("admin", "admin");

        for(Mitglied mitglied : mitglieder){

            ODocument doc = new ODocument("Mitglied");

//            doc.field( "id" , mitglied.getId());
//            doc.field( "name", mitglied.getName());
//            doc.field( "vorname", mitglied.getVorname() );
//            doc.field( "str", mitglied.getStr());
//            doc.field( "plz", mitglied.getPlz());
//            doc.field( "ort", mitglied.getOrt());
//            doc.field( "geb", mitglied.getGeb());
//            doc.field( "eintritt", mitglied.getEintritt());
//            doc.field( "gebOrt", mitglied.getGebOrt());
//            doc.field( "beruf", mitglied.getBeruf());
//            doc.field( "verein", mitglied.getVerein());

            doc.save();
        }

        db.close();
    }
}
