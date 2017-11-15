package kontroler.wejscie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kontroler.wejscie.Identyfikator;
import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;

public class Dokument {
	private final HashMap<Identyfikator, String> szukanyNumer = new HashMap<>();
	private List<DokumentZCentralaCntrValidDok> dokumentyZCentralaCntrValidDok;
	private DokumentZCentralaDokumenty dokumentZCentralaDokumenty;
	
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
			dokumentyZCentralaCntrValidDok = new ArrayList<>();
			this.dokumentyZCentralaCntrValidDok.add((DokumentZCentralaCntrValidDok)dokumentyZCentralaCntrValidDok);
		} else if (dokumentyZCentralaCntrValidDok instanceof List<?>) {
			this.dokumentyZCentralaCntrValidDok = (List<DokumentZCentralaCntrValidDok>)dokumentyZCentralaCntrValidDok;
		}
	}
	
	public DokumentZCentralaDokumenty getDokumentZCentralaDokumenty() {
		return dokumentZCentralaDokumenty;
	}
	
	public void setDokumentZCentralaDokumenty(DokumentZCentralaDokumenty dokumentZCentralaDokumenty) {
		this.dokumentZCentralaDokumenty = dokumentZCentralaDokumenty;
	}
}
