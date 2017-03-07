package rt.mitgliederVerwaltung.controller.io;

import rt.mitgliederVerwaltung.model.ResultFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResultReader {

    private String resultsPath;

    public ResultReader(String ergebnissPfad){
        this.resultsPath = ergebnissPfad;
    }

    public List<ResultFile> load(){
        List<ResultFile> resultList = readResultFolder(resultsPath);
        Database.loadResults(checkErgebnissliste(resultList));

        return Database.getResults();
    }

    public List<ResultFile> readResultFolder(String path){
        List<ResultFile> resultList = new ArrayList<>();

        File results = new File(resultsPath);
        File[] listFiles = results.listFiles();

        if (listFiles != null) {
            for(File file : listFiles){
                String fileName = file.getName();
                if (fileName.substring(0, 1).equals(".")) continue;
                if (file.isFile() && !fileName.substring(fileName.length()-8, fileName.length()-7).equals("_")){
                    resultList.add(new ResultFile(fileName.substring(0,fileName.length()-4), getFileChecksum(file)));
                }
            }
        }

        return resultList;
    }

    private List<ResultFile> checkErgebnissliste(List<ResultFile> resultList) {

        Map<String , String> importedFiles = Database.getImportedResultsMap();

        for (ResultFile result : resultList) {
            if (importedFiles.containsKey(result.getName())) {
                result.setNew(false);
                if (importedFiles.get(result.getName()).equals(result.getResultHash())) result.setChangedFile(false);
            }

        }
        for (int i = 0; i < resultList.size(); i++) {
            if (!resultList.get(i).isChangedFile())
                resultList.remove(i);
        }
        return resultList;
    }

    private static String getFileChecksum(File file) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        try {
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
