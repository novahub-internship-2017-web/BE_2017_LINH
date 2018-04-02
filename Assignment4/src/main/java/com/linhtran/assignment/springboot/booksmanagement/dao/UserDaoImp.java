package com.linhtran.assignment.springboot.booksmanagement.dao;


import com.linhtran.assignment.springboot.booksmanagement.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(User user) {
		entityManager.merge(user);
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	@Override
	@SuppressWarnings("unchecked")
	public User findUserByEmail(String email) {
		List<User> users;
		users = (List<User>) entityManager
				.createNativeQuery("select from users where email = ?")
				.setParameter(0, email)
				.getResultList();
		if (!users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User findUserById(int id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> list() {
		@SuppressWarnings("unchecked")
		Query query = entityManager.createNativeQuery("select * from users");
		return (List<User>) query.getResultList();
	}

}
