package com.mm.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mm.app.dao.AbstractDAO;
import com.mm.app.dao.LocationDAO;
import com.mm.app.model.Location;

@Repository
public class LocationDAOImpl extends AbstractDAO<Long, Location> implements LocationDAO {

	private static final Logger logger = LoggerFactory.getLogger(LocationDAOImpl.class);
	
	@Override
	public void createLocation(Location loc) {

		persist(loc);
	}

	@Override
	public void deleteLocationById(Long id) {

		delete(getByKey(id));
		/*Query query = getSession().createSQLQuery("delete from location where id = :id");
        query.setLong("id", id);
        query.executeUpdate();*/
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Location> findAllLocations() {

		Criteria criteria = createEntityCriteria();
        return (List<Location>) criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Location> findLocationsByType(String type) {

		Query query = getSession().createSQLQuery("from location where type = :type");
        query.setString("type", type);
		List<Location> locationList = (List<Location>) query.list();
		return locationList;
	}

	@Override
	public Location findLocationById(Long id) {

		return getByKey(id);
	}

	public Location findLocationByName(String name) {

		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("name", name));
		return (Location) criteria.uniqueResult();
	}

}
