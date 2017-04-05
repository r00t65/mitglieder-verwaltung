package de.rtDevelopment.mitgliederVerwaltung.controller;

import de.rtDevelopment.mitgliederVerwaltung.model.member.MemberField;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Config {
    public static List<MemberField> getMemberFields(){

        List<MemberField> memberFields;

        Stream<String[]> memberFieldsStream = CSV.read(Paths.get("data/member_fields.cfg")).stream();

        memberFields = memberFieldsStream.
                map(f -> new MemberField(f[0].trim(), f[1].trim(), f[2].trim(), f[3].trim())).
                collect(Collectors.toList());

        return memberFields;
    }
}
