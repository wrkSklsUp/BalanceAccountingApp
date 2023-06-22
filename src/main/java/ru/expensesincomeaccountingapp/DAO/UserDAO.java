package ru.expensesincomeaccountingapp.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.hibernate.Filter;
import org.hibernate.Session;

import org.springframework.stereotype.Service;

import ru.expensesincomeaccountingapp.DAO.interfaces.UserDAOInterface;
import ru.expensesincomeaccountingapp.entity.UserEntity;


@Service
public class UserDAO implements UserDAOInterface {

	@PersistenceContext
	EntityManager entityManager;
	@Override
	public void updateUser(UserEntity user) {
		entityManager.merge(user);
	}	
	@Override
	public void softDeleteUser(UserEntity user) {
		entityManager.remove(user);
	}
	@Override
	public void saveUser(UserEntity user) {
		entityManager.persist(user);
	}
	@Override
	public List<UserEntity> readUser() {

		List<UserEntity> finalUserList;

		TypedQuery<UserEntity> query = entityManager.createQuery(
				"from UserEntity", UserEntity.class
		);

		finalUserList = query.getResultList();

		return finalUserList;
	}
	@Override
	public UserEntity readUser(String userAlias) {

		UserEntity finalUser = null;

		try(Session session = entityManager.unwrap(Session.class)) {


			Filter filter = session.enableFilter("filterUsersByAlias");
			filter.setParameter("userAlias", userAlias);


			TypedQuery<UserEntity> query = entityManager.createQuery(
					"from UserEntity", UserEntity.class
			);


			for (UserEntity searchUser : query.getResultList()) {
				finalUser = searchUser;
			}

		}

		return finalUser;
	}
}
