package de.rtDevelopment.mitgliederVerwaltung.model.member;

public class MemberField {
    private int pos;
    private String alias;
    private String name;
    private Boolean visible;

    public MemberField(String pos, String alias, String name, String visible) {
        this.pos = Integer.parseInt(pos);
        this.alias = alias;
        this.name = name;
        if(visible.equals("true")){this.visible=true;}else{this.visible=false;}
    }

    public int getPos() { return pos; }

    public void setPos(int pos) { this.pos = pos; }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
