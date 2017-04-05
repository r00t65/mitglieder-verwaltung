package de.rtDevelopment.mitgliederVerwaltung.controller;

import de.rtDevelopment.mitgliederVerwaltung.model.member.Member;
import de.rtDevelopment.mitgliederVerwaltung.plugins.DatabasePlugin;

import java.util.*;
import java.util.stream.Stream;

public class Database {

    public static void write(Stream<Member> memberStream) {
        ServiceLoader<DatabasePlugin> database = ServiceLoader.load(DatabasePlugin.class);

        for (DatabasePlugin plugin : database) {
            if (!plugin.getType().equals("csv")) {
                continue;
            }

            plugin.write(memberStream);
        }
    }

    public static Stream<Member> read() {
        ServiceLoader<DatabasePlugin> database = ServiceLoader.load(DatabasePlugin.class);

        for (DatabasePlugin plugin : database) {
            if (!plugin.getType().equals("csv")) {
                continue;
            }

            return  plugin.read();
        }

        return null;
    }

}
