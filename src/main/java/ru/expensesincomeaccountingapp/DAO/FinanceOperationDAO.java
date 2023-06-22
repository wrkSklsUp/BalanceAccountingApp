package ru.expensesincomeaccountingapp.DAO;

import java.time.LocalDate;

import java.util.List;


import org.hibernate.Filter;
import org.hibernate.Session;

import org.springframework.stereotype.Service;

import ru.expensesincomeaccountingapp.DAO.interfaces.FinanceOperationDAOInterface;
import ru.expensesincomeaccountingapp.entity.FinanceOperationEntity;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Service
public class FinanceOperationDAO implements FinanceOperationDAOInterface {

	@PersistenceContext
	EntityManager entityManager;
	@Override
	public List<FinanceOperationEntity> fetchFinanceOperation(LocalDate dateFinancialTransactional) {

		List<FinanceOperationEntity> foList;
		
		try(Session session = entityManager.unwrap(Session.class)) {

			Filter filter = session.enableFilter("financeDateOF");
			filter.setParameter("dateFO", dateFinancialTransactional.toString());


			TypedQuery<FinanceOperationEntity> query = entityManager.createQuery("from FinanceOperationEntity",
					FinanceOperationEntity.class
			);

			foList = query.getResultList();
		}

		return foList;
	}
	@Override
	public List<FinanceOperationEntity> fetchFinanceOperation(
			FinanceOperationTypes typeFinanceOperation) {

		List<FinanceOperationEntity> finalList;
		
		try (Session session = entityManager.unwrap(Session.class)) {
			Filter filter = session.enableFilter("financeTypeOF");
			filter.setParameter("typeFO", typeFinanceOperation.toString());

			TypedQuery<FinanceOperationEntity> query = entityManager.createQuery("from FinanceOperationEntity",
					FinanceOperationEntity.class
			);

			finalList = query.getResultList();
		}

		return finalList;
	}
	@Override
	public List<FinanceOperationEntity> fetchFinanceOperation() {
		
		List<FinanceOperationEntity> finalList;

		TypedQuery<FinanceOperationEntity> query = entityManager.createQuery(
				"from FinanceOperationEntity",
				FinanceOperationEntity.class
		);

			finalList = query.getResultList();

		
		return finalList;
	}
	@Override
	public void saveFinanceOperation(FinanceOperationEntity financeOperation) {
		entityManager.persist(financeOperation);
	}
	
	@Override
	public void softDeleteFinanceOperationTable(FinanceOperationEntity financeOperationRow) {
		entityManager.remove(financeOperationRow);
	}
	
}
