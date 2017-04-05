package de.rtDevelopment.mitgliederVerwaltung.model.member;

import java.util.HashMap;
import java.util.Map;

public class Member {
    Map<String, String> member = new HashMap<>();

    public Member(){ }

    public void set(String alias, String value){
        member.put(alias, value);
    }

    public Map getMember(){
        return member;
    }
}
