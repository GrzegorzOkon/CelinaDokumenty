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
	private String u�ytkownik;
	
	private String raport;
	
	public Raport() {}
	
	public Raport(String data, String u�ytkownik, String raport) {
		this.data = data;
		this.u�ytkownik = u�ytkownik;
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

	public String getU�ytkownik() {
		return u�ytkownik;
	}

	public void setU�ytkownik(String u�ytkownik) {
		this.u�ytkownik = u�ytkownik;
	}

	public String getRaport() {
		return raport;
	}

	public void setRaport(String raport) {
		this.raport = raport;
	}
}
