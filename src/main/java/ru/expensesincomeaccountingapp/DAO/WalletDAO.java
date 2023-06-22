package ru.expensesincomeaccountingapp.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;

import org.springframework.stereotype.Service;

import ru.expensesincomeaccountingapp.DAO.interfaces.WalletDAOInterface;
import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.entity.WalletEntity;
import ru.expensesincomeaccountingapp.enums.Curencies;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class WalletDAO implements WalletDAOInterface {
	@PersistenceContext
	EntityManager entityManager;
	@Override
	public void close(WalletEntity wallet) {
		entityManager.remove(wallet);
	}
	@Override
	public int updateBalance(WalletEntity wallet) {

		int result;

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

		return result;
	}
	
	@Override
	public WalletEntity fetchWallet(UserEntity walletOwner, Curencies currency) {
		
		WalletEntity needWallet = null;

		try(Session session = entityManager.unwrap(Session.class)) {

			Filter filter = session.enableFilter("filterWalletsByOwnerCurrency");
			filter.setParameter("walletCurrency", currency.toString());
			filter.setParameter("walletOwner", walletOwner.getUserId());


			TypedQuery<WalletEntity> query = entityManager.createQuery("from WalletEntity",
					WalletEntity.class
			);

			for (WalletEntity wallet : query.getResultList()) {
				needWallet = wallet;
			}
		}

		return needWallet;
	}
	
	@Override
	public List<WalletEntity> fetchWallet(UserEntity walletOwner){
		
		List<WalletEntity> walletList;

		try(Session session = entityManager.unwrap(Session.class)) {
			Filter filter = session.enableFilter("filterWalletsByOwner");
			filter.setParameter("walletOwner", walletOwner.getUserId());


			TypedQuery<WalletEntity> query = entityManager.createQuery("from WalletEntity",
					WalletEntity.class
			);

			walletList = query.getResultList();
		}

		return walletList;
			
	}
}
