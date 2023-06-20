package ru.expensesincomeaccountingapp.DAO;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.entity.WalletEntity;
import ru.expensesincomeaccountingapp.enums.Curencies;
import ru.expensesincomeaccountingapp.hibernate.factory.HibernateSessionFactory;


/*
 * TODO: 1) Rewrite with EntityManager,
 *       2) Implement correct handling Exception
 */
public class WalletDAO {
	
	public void closeWallet(WalletEntity wallet) {
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			session.remove(wallet);
			transaction.commit();
			
		} catch(HibernateException ex) {
			ex.getStackTrace();
		}
	}
	
	public int updateWalletBalance(WalletEntity wallet) {
		int NOT_RESULT = 0;	
		int result = NOT_RESULT;
		
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			Query<WalletEntity> query = session.createQuery(
					
			"update WalletEntity set balance = :balance, balanceStateOnDate = :balanceStateOnDate "
			+ "where currency = :currency and walletOwner = :waletOwner", 
			WalletEntity.class
			
			);
			
			query.setParameter("currency", wallet.getCurrency());
			query.setParameter("balance", wallet.getBalance());
			query.setParameter("walletOwner", wallet.getWalletOwner());
			query.setParameter("balanceStateOnDate", new Date());
			result = query.executeUpdate();
			transaction.commit();
			
		}catch(HibernateException exception) {
			exception.getStackTrace();
		}
		
		return result;
	}
	

	public WalletEntity getWalletByCurrencyWalletOwner(UserEntity walletOwner, Curencies currency) {
		
		WalletEntity needWallet = null;
		
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			Filter filter = session.enableFilter("filterWalletsByOwnerCurrency");
			filter.setParameter("walletCurrency", currency.toString());
			filter.setParameter("walletOwner", walletOwner.getUserId());
			
			Query<WalletEntity> query = session.createQuery("from WalletEntity", WalletEntity.class);
		
			for(WalletEntity wallet : query.list()) {
				needWallet = wallet;
			}
			
			transaction.commit();
		}catch(HibernateException exception) {
			exception.getStackTrace();
		}
		
		if(needWallet != null) {
			return needWallet;
		}
		
		return new WalletEntity();
	}
	

	public List<WalletEntity> getWalletsByWalletOwner(UserEntity walletOwner){
		
		List<WalletEntity> walletList = new ArrayList<>();
		
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			Filter filter = session.enableFilter("filterWalletsByOwner");
			filter.setParameter("walletOwner", walletOwner.getUserId());
			
			Query<WalletEntity> query = session.createQuery("from WalletEntity", WalletEntity.class);
			
			transaction.commit();
			
			walletList = query.list();
			
			
		}catch(HibernateException exception) {
			exception.getStackTrace();
		}
		
		return walletList;
			
	}
}
