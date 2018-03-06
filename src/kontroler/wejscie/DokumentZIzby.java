package kontroler.wejscie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import procesor.dao.entity.DokumentZCentralaCntrValidDok;
import procesor.dao.entity.DokumentZCentralaDokumenty;
import procesor.dao.entity.DokumentZIzbyDokumenty;

public class DokumentZIzby {
	private final HashMap<Identyfikator, String> szukanyNumer = new HashMap<>();
	private List<DokumentZCentralaCntrValidDok> dokumentyZCentralaCntrValidDok;
	private List<DokumentZCentralaDokumenty> dokumentyZCentralaDokumenty;
	private List<DokumentZIzbyDokumenty> dokumentyZIzbyDokumenty;
	
	public DokumentZIzby(Identyfikator szukanyRodzaj, String szukanaWartoœæ) {
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
	
	public List<DokumentZIzbyDokumenty> getDokumentyZIzbyDokumenty() {
		return dokumentyZIzbyDokumenty;
	}
	
	public void setDokumentyZIzbyDokumenty(Object dokumentyZIzbyDokumenty) {	
		if (dokumentyZIzbyDokumenty instanceof DokumentZIzbyDokumenty) {
			this.dokumentyZIzbyDokumenty = new ArrayList<>();
			this.dokumentyZIzbyDokumenty.add((DokumentZIzbyDokumenty)dokumentyZIzbyDokumenty);
		} else if (dokumentyZIzbyDokumenty instanceof List<?>) {
			this.dokumentyZIzbyDokumenty = (List<DokumentZIzbyDokumenty>)dokumentyZIzbyDokumenty;
		}
	}
}
