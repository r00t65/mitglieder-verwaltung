package de.rtDevelopment.mitgliederVerwaltung.controller;

import de.rtDevelopment.mitgliederVerwaltung.plugins.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class PluginManager {

    public static List<Plugin> loadPlugins(){

        List<Plugin> pluginList = new ArrayList<>();

        ServiceLoader<Plugin> plugin = ServiceLoader.load(Plugin.class);

        for (Plugin loaded : plugin) { pluginList.add(loaded); }

        return  pluginList;
    }
}