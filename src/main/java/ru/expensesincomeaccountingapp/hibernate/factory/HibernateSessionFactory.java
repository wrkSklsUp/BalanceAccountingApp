package ru.expensesincomeaccountingapp.hibernate.factory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	
	private static final SessionFactory sessionFactoryObj = buildSessionFactoryObj();
	
	private static SessionFactory buildSessionFactoryObj() {
		return new Configuration().configure().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactoryObj;
	}
	
	public static void sessionFactoryStopWork() {
		sessionFactoryObj.close();
	}
}
