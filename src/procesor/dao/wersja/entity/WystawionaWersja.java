package procesor.dao.wersja.entity;

import javax.persistence.*;

@Entity
@Table(name="version")
public class WystawionaWersja {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int identyfikator;
	
	@Column(name = "name")
	private String nazwa;
	
	@Column
	private int major;
	
	@Column
	private int minor;
	
	@Column
	private int release;
	
	@Column
	private int build;
	
	@Column(name = "type")
	private String typ;
	
	@Column(name = "compile")
	private int kompilacja;
	
	@Column(name = "path")
	private String úcieøka;
	
	public WystawionaWersja() {}
	
	public WystawionaWersja(int identyfikator, String nazwa, int major, int minor, int release, int build, String typ, int kompilacja, String úcieøka) {
		this.identyfikator = identyfikator;
		this.nazwa = nazwa;
		this.major = major;
		this.minor = minor;
		this.release = release;
		this.build = build;
		this.typ = typ;
		this.kompilacja = kompilacja;
		this.úcieøka = úcieøka;
	}
	
	public int getIdentyfikator() {
		return identyfikator;
	}
	
	public void setIdentyfikator(int identyfikator) {
		this.identyfikator = identyfikator;
	}
	
	public String getNazwa() {
		return nazwa;
	}
	
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public int getMajor() {
		return major;
	}
	
	public void setMajor(int major) {
		this.major = major;
	}
	
	public int getMinor() {
		return minor;
	}
	
	public void setMinor(int minor) {
		this.minor = minor;
	}
	
	public int getRelease() {
		return release;
	}
	
	public void setRelease(int release) {
		this.release = release;
	}

	public int getBuild() {
		return build;
	}

	public void setBuild(int build) {
		this.build = build;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getKompilacja() {
		return kompilacja;
	}

	public void setKompilacja(int kompilacja) {
		this.kompilacja = kompilacja;
	}

	public String getåcieøka() {
		return úcieøka;
	}

	public void setåcieøka(String úcieøka) {
		this.úcieøka = úcieøka;
	}
}