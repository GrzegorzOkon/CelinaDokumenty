package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAWroc�awRepozytorium extends JPARepozytorium {
	private static JPAWroc�awRepozytorium jpaWroc�awRepozytorium;
	
	private JPAWroc�awRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Wroc�aw_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAWroc�awRepozytorium pobierzInstancje() {
		if (jpaWroc�awRepozytorium == null) {
			jpaWroc�awRepozytorium = new JPAWroc�awRepozytorium();
		} 
		
		return jpaWroc�awRepozytorium;
	}
}