package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAWroc³awRepozytorium extends JPARepozytorium {
	private static JPAWroc³awRepozytorium jpaWroc³awRepozytorium;
	
	private JPAWroc³awRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Wroc³aw_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAWroc³awRepozytorium pobierzInstancje() {
		if (jpaWroc³awRepozytorium == null) {
			jpaWroc³awRepozytorium = new JPAWroc³awRepozytorium();
		} 
		
		return jpaWroc³awRepozytorium;
	}
}