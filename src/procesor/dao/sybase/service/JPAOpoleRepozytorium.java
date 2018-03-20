package procesor.dao.sybase.service;

import javax.persistence.Persistence;

public class JPAOpoleRepozytorium extends JPARepozytorium {
	private static JPAOpoleRepozytorium jpaOpoleRepozytorium;
	
	private JPAOpoleRepozytorium() {
		menedzerEncjiFabryka = Persistence.createEntityManagerFactory("Opole_JPA");
		menedzerEncji = menedzerEncjiFabryka.createEntityManager();
	}
	
	public static JPAOpoleRepozytorium pobierzInstancje() {
		if (jpaOpoleRepozytorium == null) {
			jpaOpoleRepozytorium = new JPAOpoleRepozytorium();
		} 
		
		return jpaOpoleRepozytorium;
	}
}