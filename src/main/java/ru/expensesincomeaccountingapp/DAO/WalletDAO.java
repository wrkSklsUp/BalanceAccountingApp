package ru.expensesincomeaccountingapp.DAO;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.springframework.stereotype.Component;
import ru.expensesincomeaccountingapp.DAO.interfaces.WalletDAOInterface;
import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.entity.WalletEntity;
import ru.expensesincomeaccountingapp.enums.Curencies;
import ru.expensesincomeaccountingapp.hibernate.factory.HibernateEntityManagerFactory;
import ru.expensesincomeaccountingapp.hibernate.factory.HibernateSessionFactory;


@Component
public class WalletDAO implements WalletDAOInterface {

	EntityManager entityManager = HibernateEntityManagerFactory.getCreatingEntitytManager();
	@Override
	public void close(WalletEntity wallet) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(wallet);
		transaction.commit();
	}
	@Override
	public int updateBalance(WalletEntity wallet) {

		int result;

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		TypedQuery<WalletEntity> query = entityManager.createQuery(
				"update WalletEntity set balance = :balance, balanceStateOnDate = :balanceStateOnDate "
				+ "where currency = :currency and walletOwner = :walletOwner",
				WalletEntity.class

		);

		query.setParameter("currency", wallet.getCurrency());
		query.setParameter("balance", wallet.getBalance());
		query.setParameter("walletOwner", wallet.getWalletOwner());
		query.setParameter("balanceStateOnDate", new Date());
		result = query.executeUpdate();
		transaction.commit();

		return result;
	}
	
	@Override
	public WalletEntity fetchWallet(UserEntity walletOwner, Curencies currency) {
		
		WalletEntity needWallet = null;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Filter filter = session.enableFilter("filterWalletsByOwnerCurrency");
			filter.setParameter("walletCurrency", currency.toString());
			filter.setParameter("walletOwner", walletOwner.getUserId());
		}catch(HibernateException ex){
			ex.getStackTrace();
		}
			
		TypedQuery<WalletEntity> query = entityManager.createQuery("from WalletEntity", WalletEntity.class);

		transaction.commit();

		for(WalletEntity wallet : query.getResultList()) {
			needWallet = wallet;
		}

		if(needWallet != null) {
			return needWallet;
		}
		
		return new WalletEntity();
	}
	
	@Override
	public List<WalletEntity> fetchWallet(UserEntity walletOwner){
		
		List<WalletEntity> walletList;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Filter filter = session.enableFilter("filterWalletsByOwner");
			filter.setParameter("walletOwner", walletOwner.getUserId());
		}catch(HibernateException exception) {
			exception.getStackTrace();
		}

		TypedQuery<WalletEntity> query = entityManager.createQuery("from WalletEntity", WalletEntity.class);

		transaction.commit();

		walletList = query.getResultList();
		
		return walletList;
			
	}
}
