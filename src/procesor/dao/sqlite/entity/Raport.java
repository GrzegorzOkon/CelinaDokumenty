package procesor.dao.sqlite.entity;

import javax.persistence.*;

@Entity
@Table(name = "raports")
public class Raport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name = "id")
	private int identyfikator;
	
	@Column(name = "date")
	private String data;
	
	@Column(name = "user")
	private String u퓓tkownik;
	
	private String raport;
	
	public Raport() {}
	
	public Raport(String data, String u퓓tkownik, String raport) {
		this.data = data;
		this.u퓓tkownik = u퓓tkownik;
		this.raport = raport;
	}
	
	public int getIdentyfikator() {
		return identyfikator;
	}
	
	public void setIdentyfikator(int identyfikator) {
		this.identyfikator = identyfikator;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getU퓓tkownik() {
		return u퓓tkownik;
	}

	public void setU퓓tkownik(String u퓓tkownik) {
		this.u퓓tkownik = u퓓tkownik;
	}

	public String getRaport() {
		return raport;
	}

	public void setRaport(String raport) {
		this.raport = raport;
	}
}
