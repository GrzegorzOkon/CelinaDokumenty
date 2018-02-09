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
	private String œcie¿ka;
	
	public WystawionaWersja() {}
	
	public WystawionaWersja(int identyfikator, String nazwa, int major, int minor, int release, int build, String typ, int kompilacja, String œcie¿ka) {
		this.identyfikator = identyfikator;
		this.nazwa = nazwa;
		this.major = major;
		this.minor = minor;
		this.release = release;
		this.build = build;
		this.typ = typ;
		this.kompilacja = kompilacja;
		this.œcie¿ka = œcie¿ka;
	}
	
	public int getIdentyfikator() {
		return identyfikator;
	}
	
	public void setIdentyfikator(int identyfikator) {
		this.identyfikator = identyfikator;
	}
}