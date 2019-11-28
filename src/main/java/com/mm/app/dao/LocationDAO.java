package com.mm.app.dao;

import java.util.List;
import com.mm.app.model.Location;

/**
 * @author nahid
 *
 */
public interface LocationDAO {
	
	public void createLocation(Location loc);
	public void deleteLocationById(Long id);
	public List<Location> findAllLocations();
	public List<Location> findLocationsByType(String type);
	public Location findLocationById(Long id);
	public Location findLocationByName(String name);
}
