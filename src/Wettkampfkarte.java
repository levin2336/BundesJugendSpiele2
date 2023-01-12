public class Wettkampfkarte {
    String name;
    String vorname;
    String klasse;
    int alter;
    int lauf;
    int sprung;
    int wurf;

    public Wettkampfkarte(String zName, String zVorname, String zKlasse, int zAlter) {
        this.name = zName;
        this.vorname = zVorname;
        this.klasse = zKlasse;
        this.alter = zAlter;
    }

    public void setzePunkte(int pDisziplin, int pPunkte){
        switch (pDisziplin){
            case 1:
                lauf = pPunkte;
                break;
            case 2:
                sprung = pPunkte;
                break;
            case 3:
                wurf = pPunkte;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getKlasse() {
        return klasse;
    }

    public int getAlter() {
        return alter;
    }

    public int getPunkte(int pDisziplin){
        return 0;
    }

    @Override
    public String toString() {
        return "Wettkampfkarte{" +
                "zName='" + name + '\'' +
                ", zVorname='" + vorname + '\'' +
                ", zKlasse='" + klasse + '\'' +
                ", zAlter=" + alter +
                ", zLauf=" + lauf +
                ", zSprung=" + sprung +
                ", zWurf=" + wurf +
                '}';
    }
}
