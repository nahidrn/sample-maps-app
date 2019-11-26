package com.mm.app.dao;

import java.util.List;
import com.mm.app.model.Location;

/**
 * @author nahid
 *
 */
public interface LocationDAO {
	
	public void addLocation(Location loc);
	public void updateLocation(Location loc);
	public void removeLocation(Location loc);
	public List<Location> getLocationList();
	public List<Location> getLocationListByType(String type);
	public Location getLocationById(Long id);
}
