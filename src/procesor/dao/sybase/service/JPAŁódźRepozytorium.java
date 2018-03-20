package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPA£ódŸRepozytorium extends JPARepozytorium {
	private static JPA£ódŸRepozytorium jpa£ódŸRepozytorium;
	
	private JPA£ódŸRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("£ódŸ_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPA£ódŸRepozytorium pobierzInstancje() {
		if (jpa£ódŸRepozytorium == null) {
			jpa£ódŸRepozytorium = new JPA£ódŸRepozytorium();
		} 
		
		return jpa£ódŸRepozytorium;
	}
}