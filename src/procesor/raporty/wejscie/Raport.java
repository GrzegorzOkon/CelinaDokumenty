package procesor.raporty.wejscie;

public class Raport {
	private final String raportDlaHelpDesku;
	private final String raportDlaAdministratora;
	
	public Raport(String raportDlaHelpDesku, String raportDlaAdministratora) {
		this.raportDlaHelpDesku = raportDlaHelpDesku;
		this.raportDlaAdministratora = raportDlaAdministratora;
	}

	public String getRaportDlaHelpDesku() {
		return raportDlaHelpDesku;
	}

	public String getRaportDlaAdministratora() {
		return raportDlaAdministratora;
	}
}