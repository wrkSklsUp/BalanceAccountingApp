package ru.expensesincomeaccountingapp.DAO;

import java.util.List;

import java.util.ArrayList;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.hibernate.factory.HibernateSessionFactory;

/*
 * TODO: 1) Rewrite with EntityManager,
 *       2) Implement correct handling Exception
 */
public class UserDAO {
	
	public void updateUserState(UserEntity user) {
			
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			session.merge(user);
			transaction.commit();
			
		}catch(HibernateException exception) {
			exception.getStackTrace();
		}				
	}	
	
	public void softDeleteUser(UserEntity user) {
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();
			
			session.remove(user);
			
			transaction.commit();
		} catch(HibernateException exception) {
			exception.getStackTrace();
		}
	}
	
	public void saveNewUser(UserEntity user) {
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();	
			
			session.persist(user);		
			transaction.commit();
			
		}catch(HibernateException exception) {
			exception.getStackTrace();
		}
	}
	
	public List<UserEntity> readUserRecords() {
		
		List<UserEntity> finalUserList = new ArrayList<>();
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();		
			
			Query<UserEntity> query = session.createQuery(
				"from UserEntity", UserEntity.class
			);
						
			transaction.commit();
			finalUserList = query.list();
			
		}catch(HibernateException exception) {
			exception.getStackTrace();
		}
		
		return finalUserList;
	}
	
	public UserEntity readUserRecordByUserAlias(String userAlias) {
		
		UserEntity finalUser = null;
		
		try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
			Transaction transaction = session.beginTransaction();	
			
			Filter filter = session.enableFilter("filterUsersByAlias");
			filter.setParameter("userAlias", userAlias);
			
			Query<UserEntity> query = session.createQuery(
				"from UserEntity", UserEntity.class
			);
			
			transaction.commit();
			
			for(UserEntity searchUser : query.list()) {
				finalUser = searchUser;
			}
			
		}catch(HibernateException exception) {
			exception.getStackTrace();
		}
		
		if(finalUser != null) {
			return finalUser;
		}
		
		return new UserEntity("User is NULL");
		
	}
}
