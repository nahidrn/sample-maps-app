package com.mm.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mm.app.dao.AbstractDao;
import com.mm.app.dao.UserDao;
import com.mm.app.model.User;

@Repository
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	
	public User findUserById(Long id) {
        
		return getByKey(id);
    }

    public void createUser(User user) {
        
    	persist(user);
    }

    public void deleteUserById(Long id) {

		delete(getByKey(id));
    	/*Query query = getSession().createSQLQuery("delete from \"user\" where id = :id");
        query.setLong("id", id);
        query.executeUpdate();*/
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {

		//Query query = getSession().createSQLQuery("from user");
		//List<User> locationList = (List<User>) query.list();
		//return locationList;

    	Criteria criteria = createEntityCriteria();

        return (List<User>) criteria.list();
    }

    public User findUserByName(String name) {
        
    	Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (User) criteria.uniqueResult();
    }
}
