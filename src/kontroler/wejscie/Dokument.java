package kontroler.wejscie;

import java.util.ArrayList;
import java.util.*;

import kontroler.wejscie.Identyfikator;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;

public class Dokument {
	private final HashMap<Identyfikator, String> szukanyNumer = new HashMap<>();
	private List<DokumentZCentralaCntrValidDok> dokumentyZCentralaCntrValidDok;
	private List<DokumentZCentralaDokumenty> dokumentyZCentralaDokumenty;
	
	public Dokument(Identyfikator szukanyRodzaj, String szukanaWartoœæ) {
		szukanyNumer.put(szukanyRodzaj, szukanaWartoœæ);
	}

	public HashMap<Identyfikator, String> getSzukanyNumer() {
		return szukanyNumer;
	}
	
	public List<DokumentZCentralaCntrValidDok> getDokumentyZCentralaCntrValidDok() {
		return dokumentyZCentralaCntrValidDok;
	}
	
	public void setDokumentyZCentralaCntrValidDok(Object dokumentyZCentralaCntrValidDok) {
		if (dokumentyZCentralaCntrValidDok instanceof DokumentZCentralaCntrValidDok) {
			this.dokumentyZCentralaCntrValidDok = new ArrayList<>();
			this.dokumentyZCentralaCntrValidDok.add((DokumentZCentralaCntrValidDok)dokumentyZCentralaCntrValidDok);
		} else if (dokumentyZCentralaCntrValidDok instanceof List<?>) {
			this.dokumentyZCentralaCntrValidDok = (List<DokumentZCentralaCntrValidDok>)dokumentyZCentralaCntrValidDok;
		}
	}
	
	public List<DokumentZCentralaDokumenty> getDokumentyZCentralaDokumenty() {
		return dokumentyZCentralaDokumenty;
	}
	
	public void setDokumentyZCentralaDokumenty(Object dokumentyZCentralaDokumenty) {	
		if (dokumentyZCentralaDokumenty instanceof DokumentZCentralaDokumenty) {
			this.dokumentyZCentralaDokumenty = new ArrayList<>();
			this.dokumentyZCentralaDokumenty.add((DokumentZCentralaDokumenty)dokumentyZCentralaDokumenty);
		} else if (dokumentyZCentralaDokumenty instanceof List<?>) {
			this.dokumentyZCentralaDokumenty = (List<DokumentZCentralaDokumenty>)dokumentyZCentralaDokumenty;
		}
	}
}
