package de.rtDevelopment.mitgliederVerwaltung.plugins;

import de.rtDevelopment.mitgliederVerwaltung.model.member.Member;

import java.util.stream.Stream;

public interface DatabasePlugin {

    String getType();

    void write(Stream<Member> memberStream);

    Stream<Member> read();
}
