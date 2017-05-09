package de.rtDevelopment.mitgliederVerwaltung.plugins;

import de.rtDevelopment.mitgliederVerwaltung.model.member.Member;

import java.util.stream.Stream;

public interface Plugin {

    String getName();
    Stream<String> getFeatures();
    String getType();
    Stream<Member> useFeature(String feature, Stream<Member> member);

}
