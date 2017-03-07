package rt.mitgliederVerwaltung.controller.io;

import rt.mitgliederVerwaltung.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVParser {

    public static List<Mitglied> ladeMitgliederListe(File file) {

        List<Mitglied> mitglieder = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String mbr[] = line.split(",");

                int id = 0;

                try {
                    id = Integer.parseInt(mbr[0]);
//                    mitglieder.add(new Mitglied(id, mbr[1], mbr[2], mbr[3], mbr[4], mbr[5], mbr[6], mbr[7]));
                } catch (NumberFormatException e) {
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mitglieder;
    }

    public static List<String[]> ladeErgebnissDatei(File file) {

        List<String[]> ergebnissDatei = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "Windows-1252"));

            String line;

            while ((line = in.readLine()) != null) {
                String mbr[] = line.split(";");
                ergebnissDatei.add(mbr);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ergebnissDatei;
    }

    public static List<Result> getResult(ResultFile resultFile) {
        List<Result> results = new ArrayList<>();
        List<String[]> starter = ladeErgebnissDatei(new File(new Config(new Reader().readConfig()).getSetting(Config.PFAD_DATEN) + resultFile.getStarterFile()));
        Map<Integer, Result> starterIDMap = new HashMap<>();
        for (String[] line : starter) {
            starterIDMap.put(Integer.parseInt(line[1]), new Result(line));
        }
        List<String[]> shots = ladeErgebnissDatei(new File(new Config(new Reader().readConfig()).getSetting(Config.PFAD_DATEN) + resultFile.getResultFile()));
        for (String[] shot : shots) {
            if (!shot[0].equals("") && starterIDMap.containsKey(Integer.parseInt(shot[0]))) {
                List<Shot> shotList = starterIDMap.get(Integer.parseInt(shot[0])).getShots();
                shotList.add(new Shot(shot));
                starterIDMap.get(Integer.parseInt(shot[0])).setShots(shotList);
            }
        }
        for (String[] line : starter) {
            if (!line[1].equals("")) results.add(starterIDMap.get(Integer.parseInt(line[1])));
        }
        return results;
    }
}
