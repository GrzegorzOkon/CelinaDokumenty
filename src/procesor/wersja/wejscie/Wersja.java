package procesor.wersja.wejscie;

public class Wersja {
    private String lokalnaNazwa = "JCelinaDokumenty";
    private int lokalnyMajor = 2;
    private int lokalnyMinor = 0;
    private int lokalnyRelease = 0;
    private int lokalnyBuild = 0;
    private String lokalnyTyp = "GA";
    private int lokalnaKomplilacja = 20180206;
    
    public String pobierzWersjê() {
        //zwraca w formacie JCelinaDokumenty v1.5.14 (rev. 20150627)
        return "" + lokalnaNazwa + " v" + lokalnyMajor + "." + lokalnyMinor + "." + lokalnyRelease + " (rev. " + lokalnaKomplilacja + ")";
    }
}
