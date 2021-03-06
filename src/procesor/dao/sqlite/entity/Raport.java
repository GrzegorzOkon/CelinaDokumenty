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
	private String użytkownik;
	
	private String raport;
	
	public Raport() {}
	
	public Raport(String data, String użytkownik, String raport) {
		this.data = data;
		this.użytkownik = użytkownik;
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

	public String getUżytkownik() {
		return użytkownik;
	}

	public void setUżytkownik(String użytkownik) {
		this.użytkownik = użytkownik;
	}

	public String getRaport() {
		return raport;
	}

	public void setRaport(String raport) {
		this.raport = raport;
	}
}
