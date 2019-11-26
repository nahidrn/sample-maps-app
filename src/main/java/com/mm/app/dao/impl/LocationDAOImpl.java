package com.mm.app.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mm.app.dao.AbstractDAO;
import com.mm.app.dao.LocationDAO;
import com.mm.app.model.Location;

@Repository
public class LocationDAOImpl extends AbstractDAO<Integer, Location> implements LocationDAO {

	private static final Logger logger = LoggerFactory.getLogger(LocationDAOImpl.class);
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public void addLocation(Location loc) {
		// TODO Auto-generated method stub

	}

	public void updateLocation(Location loc) {
		// TODO Auto-generated method stub

	}

	public void removeLocation(Location loc) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public List<Location> getLocationList() {

		Session session = this.sessionFactory.getCurrentSession();
		List<Location> locationList = session.createQuery("FROM location").list();
		for (Location loc : locationList) {
			logger.info("Location List::" + loc);
		}
		return locationList;
	}

	@SuppressWarnings("unchecked")
	public List<Location> getLocationListByType(String type) {

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM location WHERE type = :location_type");
		query.setParameter("location_type", type);
		List<Location> locationList = (List<Location>) query.list();
		for (Location loc : locationList) {
			logger.info("Location List::" + loc);
		}
		return locationList;
	}

	public Location getLocationById(Long id) {

		Session session = this.sessionFactory.getCurrentSession();
		Location location = (Location) session.load(Location.class, id);
		logger.info("Location loaded successfully, Location details=" + location);
		return location;
	}

}
