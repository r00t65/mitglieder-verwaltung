package rt.mitgliederVerwaltung.model;

public class TableViewColumn {
    private String fxid;
    private String name;
    private boolean showInTable;

    public TableViewColumn(String fxid, String name,  boolean showInTable) {
        this.fxid = fxid;
        this.name = name;
        this.showInTable = showInTable;
    }

    public String getFxid() { return fxid; }

    public String getName() { return name; }

    public boolean isShowInTable(){ return showInTable; }
}
