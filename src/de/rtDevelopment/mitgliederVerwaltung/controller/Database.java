package de.rtDevelopment.mitgliederVerwaltung.controller;

import de.rtDevelopment.mitgliederVerwaltung.model.member.Member;
import de.rtDevelopment.mitgliederVerwaltung.plugins.Plugin;

import java.util.*;
import java.util.stream.Stream;

public class Database {

    private static List<Plugin> plugins = PluginManager.loadPlugins();

    public static void write(Stream<Member> memberStream) {
        for(Plugin plugin : plugins){

            if (!plugin.getType().equals("csv")) {
                continue;
            }

            plugin.useFeature("write", memberStream);
        }
    }

    public static Stream<Member> read() {
        for(Plugin plugin : plugins){

            if (!plugin.getType().equals("csv")) {
                continue;
            }

            return  plugin.useFeature("read", null);
        }

        return null;
    }

}
