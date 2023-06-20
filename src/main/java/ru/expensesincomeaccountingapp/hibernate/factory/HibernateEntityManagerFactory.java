package ru.expensesincomeaccountingapp.hibernate.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateEntityManagerFactory {
	 
	private static final EntityManagerFactory entityFactory = buildEntityManagerFactory("persistence");
	
	private static EntityManagerFactory buildEntityManagerFactory(String persistance) {
		return Persistence.createEntityManagerFactory(persistance);
	}
	
	public static EntityManager getCreatingEntitytManager() {
		return entityFactory.createEntityManager();
	}
	
	
}
