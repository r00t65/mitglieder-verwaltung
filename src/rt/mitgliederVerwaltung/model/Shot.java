package rt.mitgliederVerwaltung.model;

public class Shot {
    private int startNr;
    private double primaerwertung;
    private int schussart;
    private int bahnNr;
    private double sekundärwertung;
    private int teiler;
    private String zeit;
    private int mouche;
    private double x;
    private double y;
    private boolean inTime;
    private double timeSinceChange;
    private int sweepDirection;
    private int demonstration;
    private int match;
    private int stich;
    private int insDel;
    private int totalArt;
    private int gruppe;
    private int feuerart;
    private int logEvent;
    private int logTyp;
    private int timestamp;
    private int abloesung;

    public Shot(String[] shot) {
        this.startNr = Integer.parseInt(shot[0]);
        this.primaerwertung = Double.parseDouble(shot[1]);
        this.schussart = Integer.parseInt(shot[2]);
        this.bahnNr = Integer.parseInt(shot[3]);
        this.sekundärwertung = Double.parseDouble(shot[4]);
        this.teiler = Integer.parseInt(shot[5]);
        this.zeit = shot[6];
        this.mouche = Integer.parseInt(shot[7]);
        this.x = Double.parseDouble(shot[8]);
        this.y = Double.parseDouble(shot[9]);
        this.inTime = Boolean.parseBoolean(shot[10]);
        this.timeSinceChange = Double.parseDouble(shot[11]);
        this.sweepDirection = Integer.parseInt(shot[12]);
        this.demonstration = Integer.parseInt(shot[13]);
        this.match = Integer.parseInt(shot[14]);
        this.stich = Integer.parseInt(shot[15]);
        this.insDel = Integer.parseInt(shot[16]);
        this.totalArt = Integer.parseInt(shot[17]);
        this.gruppe = Integer.parseInt(shot[18]);
        this.feuerart = Integer.parseInt(shot[19]);
        this.logEvent = Integer.parseInt(shot[20]);
        this.logTyp = Integer.parseInt(shot[21]);
        this.timestamp = Integer.parseInt(shot[22]);
        this.abloesung = Integer.parseInt(shot[23]);
    }

    public int getStartNr() {
        return startNr;
    }

    public double getPrimaerwertung() {
        return primaerwertung;
    }

    public int getSchussart() {
        return schussart;
    }

    public int getBahnNr() {
        return bahnNr;
    }

    public double getSekundärwertung() {
        return sekundärwertung;
    }

    public int getTeiler() {
        return teiler;
    }

    public String getZeit() {
        return zeit;
    }

    public int getMouche() {
        return mouche;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isInTime() {
        return inTime;
    }

    public double getTimeSinceChange() {
        return timeSinceChange;
    }

    public int getSweepDirection() {
        return sweepDirection;
    }

    public int getDemonstration() {
        return demonstration;
    }

    public int getMatch() {
        return match;
    }

    public int getStich() {
        return stich;
    }

    public int getInsDel() {
        return insDel;
    }

    public int getTotalArt() {
        return totalArt;
    }

    public int getGruppe() {
        return gruppe;
    }

    public int getFeuerart() {
        return feuerart;
    }

    public int getLogEvent() {
        return logEvent;
    }

    public int getLogTyp() {
        return logTyp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getAbloesung() {
        return abloesung;
    }
}
