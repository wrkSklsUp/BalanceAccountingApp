package ru.expensesincomeaccountingapp.DAO;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ru.expensesincomeaccountingapp.entity.FinanceOperationEntity;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;
import ru.expensesincomeaccountingapp.hibernate.factory.HibernateSessionFactory;


/*
 * TODO: 1) Rewrite with EntityManager,
 *       2) Implement correct handling Exception
 */
public class FinanceOperationDAO {
	
	
	public List<FinanceOperationEntity> readFinanceOperationFilteredByDate(LocalDate dateFinancialTransactional) {
		
		List<FinanceOperationEntity> foList = new ArrayList<>();
		
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			Filter filter = session.enableFilter("financeDateOF");
			filter.setParameter("dateFO", dateFinancialTransactional.toString());
			
			foList = session.createQuery("from FinanceOperationEntity", FinanceOperationEntity.class).list();
			transaction.commit();
			
		}catch(HibernateException ex) {
			ex.getStackTrace();
		}
			
		return foList;
	}
	
	public List<FinanceOperationEntity> readFinanceOperationFilteredByType(
			FinanceOperationTypes typeFinanceOperation) {
		
		List<FinanceOperationEntity> finalList = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			
			Transaction transaction = session.beginTransaction();
			
			Filter filter = session.enableFilter("financeTypeOF");
			filter.setParameter("typeFO", typeFinanceOperation.toString());
			
			finalList = session.createQuery("from FinanceOperationEntity", FinanceOperationEntity.class).list();
			transaction.commit();
			
		}catch(HibernateException ex) {
			ex.getStackTrace();
		}
		
		return finalList;
	}
	
	public List<FinanceOperationEntity> readAllFinanceOperation() {
		
		List<FinanceOperationEntity> finalList = new ArrayList<>();
		
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			
			Transaction transaction = session.beginTransaction();
			
			Query<FinanceOperationEntity> query = session.createQuery(
					"from FinanceOperationEntity",
					FinanceOperationEntity.class
			);
			
			transaction.commit();		
			finalList = query.list();
					
		}catch(HibernateException ex) {
			ex.getStackTrace();
		}
		
		return finalList;
	}
	
	public void saveFinanceOperation(FinanceOperationEntity financeOperation) {
		
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			
			Transaction transaction = session.beginTransaction();
			session.persist(financeOperation);	
			transaction.commit();
					
		}catch(HibernateException ex) {
			ex.getStackTrace();
		}
		
	}
	

	public void softDeleteFinanceOperationTable(FinanceOperationEntity financeOperationRow) {
		
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			session.remove(financeOperationRow);
			transaction.commit();
			
		} catch(HibernateException ex) {
			ex.getStackTrace();
		}
							
	}
	
}
