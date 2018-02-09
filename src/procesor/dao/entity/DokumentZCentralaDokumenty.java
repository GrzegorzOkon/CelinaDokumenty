package procesor.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dokumenty")
public class DokumentZCentralaDokumenty {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) 	
   @Column(name = "id_dok")
   private String identyfikatorDokumentu;
   
   @Column(name = "id_jedn")
   private String identyfikatorJednostki;
	
   @Column(name = "id_rodz_dok")
   private String identyfikatorRodzajuDokumentu;
   
   @Column(name = "sym_dok")
   private String symbolDokumentu;
	
   @Column(name = "status")
   private String statusPrzetwarzania;
	
   @Column(name = "data_formal_przyj")
   private String dataFormalnegoPrzyjecia;
   
   public DokumentZCentralaDokumenty( ) {}
   
   public DokumentZCentralaDokumenty(String identyfikatorDokumentu, String identyfikatorJednostki, String identyfikatorRodzajuDokumentu, String symbolDokumentu, String statusPrzetwarzania, String dataFormalnegoPrzyjecia) {
      this.identyfikatorDokumentu = identyfikatorDokumentu;
      this.identyfikatorJednostki = identyfikatorJednostki;
      this.identyfikatorRodzajuDokumentu = identyfikatorRodzajuDokumentu;
      this.symbolDokumentu = symbolDokumentu;
      this.statusPrzetwarzania = statusPrzetwarzania;
      this.dataFormalnegoPrzyjecia = dataFormalnegoPrzyjecia;
   }

   public String getIdentyfikatorDokumentu( ) {
      return identyfikatorDokumentu;
   }
   
   public void setIdentyfikatorDokumentu(String identyfikatorDokumentu) {
      this.identyfikatorDokumentu = identyfikatorDokumentu;
   }
   
   public String getIdentyfikatorJednostki( ) {
	  return identyfikatorJednostki;
   }
	   
   public void setIdentyfikatorJednostki(String identyfikatorJednostki) {
	      this.identyfikatorJednostki = identyfikatorJednostki;
   }
   
   public String getIdentyfikatorRodzajuDokumentu( ) {
      return identyfikatorRodzajuDokumentu;
   }
   
   public void setIdentyfikatorRodzajuDokumentu(String identyfikatorRodzajuDokumentu) {
      this.identyfikatorRodzajuDokumentu = identyfikatorRodzajuDokumentu;
   }

   public String getSymbolDokumentu( ) {
      return symbolDokumentu;
   }
   
   public void setSymbolDokumentu(String symbolDokumentu) {
      this.symbolDokumentu = symbolDokumentu;
   }

   public String getStatusPrzetwarzania( ) {
      return statusPrzetwarzania;
   }
   
   public void setStatusPrzetwarzania(String statusPrzetwarzania) {
      this.statusPrzetwarzania = statusPrzetwarzania;
   }
   
   public String getDataFormalnegoPrzyjecia( ) {
	  return dataFormalnegoPrzyjecia;
}
	   
   public void setDataFormalnegoPrzyjecia(String dataFormalnegoPrzyjecia) {
	  this.dataFormalnegoPrzyjecia = dataFormalnegoPrzyjecia;
   }
   
   @Override
   public String toString() {
      return "Employee [eid=" + identyfikatorDokumentu + "]";
   }
}