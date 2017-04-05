package de.rtDevelopment.mitgliederVerwaltung.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CSV {

    static List<String[]> read(Path path) {
        List<String[]> ret = new ArrayList<>();

        try (Stream<String> stream = Files.lines(path)) {

            ret = stream.
                    filter(l -> !l.startsWith("#")).
                    map(l -> l.split(",")).
                    collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }


        return ret;
    }

    static void write(Path path, List<String[]> list){
        Iterator<String[]> iter = list.iterator();
        List<String> data = new ArrayList<>();
        while (iter.hasNext()){
            StringBuilder lineBuilder = new StringBuilder();
            for(String str : iter.next()){
                lineBuilder.append(str).append(",");
            }
            String line = lineBuilder.toString();
            line = line.substring(0, line.length()-1);
            data.add(line);
        }

        try {
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
