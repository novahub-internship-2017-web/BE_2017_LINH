package com.linhtran.assignment.booksmanagement.dao;


import java.util.List;



import javax.persistence.TypedQuery;


import com.linhtran.assignment.booksmanagement.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDaoImp implements UserDao {
	
	@Autowired	
	private SessionFactory sessionFactory;	
	
	@Override
	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);		
	}

	@Override
	@Transactional
	public void update(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public User findUserByEmail(String email) {
		List<User> users;
		users = (List<User>) sessionFactory.getCurrentSession()
				.createQuery("from User where email = ?")
				.setParameter(0, email)
				.list();
		if (!users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(int id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> list() {
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
		return query.getResultList();
	}

}
