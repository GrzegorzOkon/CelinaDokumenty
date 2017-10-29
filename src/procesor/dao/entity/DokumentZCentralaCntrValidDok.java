package procesor.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="cntr_valid_dok")
public class DokumentZCentralaCntrValidDok {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	
	@Column(name = "id_dok")
	private String identyfikatorDokumentu;
	
	@Column(name = "id_rodz_dok")
	private String identyfikatorRodzajuDokumentu;
	
	@Column(name = "jedn_przezn")
	private String jednostkaPrzeznaczenia;
	
	@Column(name = "status_przetw")
	private String statusPrzetwarzania;
	
	@Column(name = "data_przyjecia")
	private String dataPrzyjecia;

	public DokumentZCentralaCntrValidDok() {}
	
	public DokumentZCentralaCntrValidDok(String identyfikatorDokumentu, String identyfikatorRodzajuDokumentu, String jednostkaPrzeznaczenia, String statusPrzetwarzania, String dataPrzyjecia) {
		this.identyfikatorDokumentu = identyfikatorDokumentu;
		this.identyfikatorRodzajuDokumentu = identyfikatorRodzajuDokumentu;
		this.jednostkaPrzeznaczenia = jednostkaPrzeznaczenia;
		this.statusPrzetwarzania = statusPrzetwarzania;
		this.dataPrzyjecia = dataPrzyjecia;
	}
	
	public String getIdentyfikatorDokumentu() {
		return identyfikatorDokumentu;
	}
	
	public void setIdentyfikatorDokumentu(String identyfikatorDokumentu) {
		this.identyfikatorDokumentu = identyfikatorDokumentu;
	}
	
	public String getIdentyfikatorRodzajuDokumentu() {
		return identyfikatorRodzajuDokumentu;
	}
	
	public void setIdentyfikatorRodzajuDokumentu(String identyfikatorRodzajuDokumentu) {
		this.identyfikatorRodzajuDokumentu = identyfikatorRodzajuDokumentu;
	}
	
	public String getJednostkaPrzeznaczenia() {
		return jednostkaPrzeznaczenia;
	}
	
	public void setJednostkaPrzeznaczenia(String jednostkaPrzeznaczenia) {
		this.jednostkaPrzeznaczenia = jednostkaPrzeznaczenia;
	}
	
	public String getStatusPrzetwarzania() {
		return statusPrzetwarzania;
	}	
	
	public void setStatusPrzetwarzania(String statusPrzetwarzania) {
		this.statusPrzetwarzania = statusPrzetwarzania;
	}
	
	public String getDataPrzyjecia() {
		return dataPrzyjecia;
	}		
	
	public void getDataPrzyjecia(String dataPrzyjecia) {
		this.dataPrzyjecia = dataPrzyjecia;
	}
}
