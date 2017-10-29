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
		dokumentyZCentralaCntrValidDok = new ArrayList<>();
	}

	public HashMap<Identyfikator, String> getSzukanyNumer() {
		return szukanyNumer;
	}
	
	/*public List<DokumentZCentralaCntrValidDokPOJO> getDokumentyZCentralaCntrValidDok() {
		return dokumentyZCentralaCntrValidDok;
	}*/
	
	public void setDokumentyZCentralaCntrValidDok(DokumentZCentralaCntrValidDok dokumentZCentralaCntrValidDok) {
		this.dokumentyZCentralaCntrValidDok.add(dokumentZCentralaCntrValidDok);
	}
	
	/*public void setDokumentyZCentralaCntrValidDok(List<DokumentZCentralaCntrValidDok> dokumentyZCentralaCntrValidDok) {
		this.dokumentyZCentralaCntrValidDok = dokumentyZCentralaCntrValidDok;
	}*/
	
	public DokumentZCentralaDokumenty getDokumentZCentralaDokumenty() {
		return dokumentZCentralaDokumenty;
	}
	
	public void setDokumentZCentralaDokumenty(DokumentZCentralaDokumenty dokumentZCentralaDokumenty) {
		this.dokumentZCentralaDokumenty = dokumentZCentralaDokumenty;
	}
}
