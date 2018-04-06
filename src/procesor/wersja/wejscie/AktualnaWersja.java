package procesor.wersja.wejscie;

import procesor.dao.sqlite.entity.WystawionaWersja;
import procesor.wersja.KontrolerWersji;

public class AktualnaWersja {
	private static AktualnaWersja instancja;
	
    private String lokalnaNazwa = "JCelinaDokumenty";
    private int lokalnyMajor = 2;
    private int lokalnyMinor = 0;
    private int lokalnyRelease = 0;
    private int lokalnyBuild = 0;
    private String lokalnyTyp = "GA";
    private int lokalnaKomplilacja = 20180406;
    
	private AktualnaWersja() {}
	
	public static AktualnaWersja pobierzInstancjê() {
		if (instancja == null) {
			instancja = new AktualnaWersja();
		} 		
		return instancja;
	}
    
	public String getLokalnaNazwa() {
		return lokalnaNazwa;
	}
	
	public int getLokalnyMajor() {
		return lokalnyMajor;
	}
	
	public int getLokalnyMinor() {
		return lokalnyMinor;
	}
	
	public int getLokalnyRelease() {
		return lokalnyRelease;
	}
	
	public int getLokalnyBuild() {
		return lokalnyBuild;
	}
	
	public String getLokalnyTyp() {
		return lokalnyTyp;
	}
	
	public int getLokalnaKomplilacja() {
		return lokalnaKomplilacja;
	}
	
    public String pobierzOpis() {
        //zwraca w formacie JCelinaDokumenty v1.5.14 (rev. 20150627)
        return "" + lokalnaNazwa + " v" + lokalnyMajor + "." + lokalnyMinor + "." + lokalnyRelease + " (rev. " + lokalnaKomplilacja + ")";
    }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof WystawionaWersja)) return false;
		
		WystawionaWersja wystawionaWersja = (WystawionaWersja)obj;
		
		if (this.lokalnaNazwa.equals(wystawionaWersja.getNazwa()) && this.lokalnyMajor == wystawionaWersja.getMajor() 
			&& this.lokalnyMinor == wystawionaWersja.getMinor() && this.lokalnyRelease == wystawionaWersja.getRelease() 
			&& this.lokalnaKomplilacja == wystawionaWersja.getKompilacja()) {
			return true;
		} else {
			return false;
		}
	}
}
