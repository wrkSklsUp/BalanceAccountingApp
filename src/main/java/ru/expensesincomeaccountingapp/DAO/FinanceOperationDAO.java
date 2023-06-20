package ru.expensesincomeaccountingapp.DAO;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.stereotype.Component;
import ru.expensesincomeaccountingapp.DAO.interfaces.FinanceOperationDAOInterface;
import ru.expensesincomeaccountingapp.entity.FinanceOperationEntity;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;
import ru.expensesincomeaccountingapp.hibernate.factory.HibernateEntityManagerFactory;
import ru.expensesincomeaccountingapp.hibernate.factory.HibernateSessionFactory;


@Component
public class FinanceOperationDAO implements FinanceOperationDAOInterface {

	EntityManager entityManager = HibernateEntityManagerFactory.getCreatingEntitytManager();
	@Override
	public List<FinanceOperationEntity> fetchFinanceOperation(LocalDate dateFinancialTransactional) {

		EntityTransaction transaction = entityManager.getTransaction();
		List<FinanceOperationEntity> foList;
		transaction.begin();
		
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			
			Filter filter = session.enableFilter("financeDateOF");
			filter.setParameter("dateFO", dateFinancialTransactional.toString());
			
		}catch(HibernateException ex) {
			ex.getStackTrace();
		}

		TypedQuery<FinanceOperationEntity> query = entityManager.createQuery("from FinanceOperationEntity",
				FinanceOperationEntity.class
		);

		transaction.commit();

		foList = query.getResultList();

		return foList;
	}
	@Override
	public List<FinanceOperationEntity> fetchFinanceOperation(
			FinanceOperationTypes typeFinanceOperation) {

		EntityTransaction transaction = entityManager.getTransaction();
		List<FinanceOperationEntity> finalList;
		transaction.begin();
		
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Filter filter = session.enableFilter("financeTypeOF");
			filter.setParameter("typeFO", typeFinanceOperation.toString());
		}catch(HibernateException ex) {
			ex.getStackTrace();
		}

		TypedQuery<FinanceOperationEntity>query = entityManager.createQuery("from FinanceOperationEntity",
				FinanceOperationEntity.class
		);

		transaction.commit();

		finalList = query.getResultList();
		
		return finalList;
	}
	@Override
	public List<FinanceOperationEntity> fetchFinanceOperation() {
		
		List<FinanceOperationEntity> finalList;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

			
		TypedQuery<FinanceOperationEntity> query = entityManager.createQuery(
				"from FinanceOperationEntity",
				FinanceOperationEntity.class
		);
			
			transaction.commit();		
			finalList = query.getResultList();

		
		return finalList;
	}
	@Override
	public void saveFinanceOperation(FinanceOperationEntity financeOperation) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(financeOperation);
		transaction.commit();
		
	}
	
	@Override
	public void softDeleteFinanceOperationTable(FinanceOperationEntity financeOperationRow) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(financeOperationRow);
		transaction.commit();

	}
	
}
