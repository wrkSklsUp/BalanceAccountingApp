package ru.expensesincomeaccountingapp.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.Filter;
import org.hibernate.Session;

import org.springframework.stereotype.Component;
import ru.expensesincomeaccountingapp.DAO.interfaces.UserDAOInterface;
import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.hibernate.factory.HibernateEntityManagerFactory;


@Component
public class UserDAO implements UserDAOInterface {
	EntityManager entityManager = HibernateEntityManagerFactory.getCreatingEntitytManager();
	@Override
	public void updateUser(UserEntity user) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(user);
		transaction.commit();
	}	
	@Override
	public void softDeleteUser(UserEntity user) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.remove(user);
		transaction.commit();
	}
	@Override
	public void saveUser(UserEntity user) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(user);
		transaction.commit();
	}
	@Override
	public List<UserEntity> readUser() {

		EntityTransaction transaction = entityManager.getTransaction();
		List<UserEntity> finalUserList;
		transaction.begin();

		TypedQuery<UserEntity> query = entityManager.createQuery(
				"from UserEntity", UserEntity.class
		);
						
		transaction.commit();
		finalUserList = query.getResultList();

		return finalUserList;
	}
	@Override
	public UserEntity readUser(String userAlias) {

		EntityTransaction transaction = entityManager.getTransaction();
		UserEntity finalUser = null;
		transaction.begin();

		try(Session session = entityManager.unwrap(Session.class)) {


			Filter filter = session.enableFilter("filterUsersByAlias");
			filter.setParameter("userAlias", userAlias);


			TypedQuery<UserEntity> query = entityManager.createQuery(
					"from UserEntity", UserEntity.class
			);

			transaction.commit();


			for (UserEntity searchUser : query.getResultList()) {
				finalUser = searchUser;
			}

		}

		return finalUser;
	}
}
