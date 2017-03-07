package rt.mitgliederVerwaltung.controller.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Writer {

    public Writer(){
    }

    public void writeConfig(List<String> config){
        Path file = Paths.get("data/config.sbk");
        try {
            Files.write(file, config, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//      Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
    }
}
