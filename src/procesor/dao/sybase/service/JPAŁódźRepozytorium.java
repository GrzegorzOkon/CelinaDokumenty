package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPA��d�Repozytorium extends JPARepozytorium {
	private static JPA��d�Repozytorium jpa��d�Repozytorium;
	
	private JPA��d�Repozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("��d�_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPA��d�Repozytorium pobierzInstancje() {
		if (jpa��d�Repozytorium == null) {
			jpa��d�Repozytorium = new JPA��d�Repozytorium();
		} 
		
		return jpa��d�Repozytorium;
	}
}