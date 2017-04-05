package de.rtDevelopment.mitgliederVerwaltung.model;

import java.util.*;

public class Config {
    private Map<String, String> config = new HashMap<>();

    public final static String TRUE = "true";
    public final static String FALSE = "false";
    public final static String INITIALIZED = "initialized";
    public final static String PFAD_MITGLIEDERLISTE = "pfadMitgliederListe";
    public final static String PFAD_DATEN = "pfadDaten";

    public Config(List<String> config){
        for(String entry : config){
            String[] kv = entry.split(",");
            this.config.put(kv[0], kv[1]);
        }
    }

    public List<String> exportConfig(){
        List<String> export = new ArrayList<>();
        Iterator<Map.Entry<String , String >> configIterator = config.entrySet().iterator();
        while (configIterator.hasNext()){
            Map.Entry<String, String > entry = configIterator.next();
            export.add(entry.getKey()+","+entry.getValue());
        }

        return export;
    }

    public String getSetting(String setting){
        return this.config.get(setting);
    }

    public void setSetting(String setting, String value){
        this.config.put(setting, value);
    }
}
