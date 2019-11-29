package com.mm.app.serviceTest;


import com.mm.app.config.AppConfig;
import com.mm.app.dao.LocationDao;
import com.mm.app.model.Address;
import com.mm.app.model.Location;
import com.mm.app.service.impl.LocationServiceImpl;
import org.junit.*;
import static org.mockito.Mockito.doNothing;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {

    @InjectMocks
    private LocationServiceImpl locationService;

    @Mock
    private LocationDao dao;

    private Location location;

    private Address address;

    @Before
    public void init(){
        initLocatiobnObjects();
    }

    @Test
    public void createLocationTest(){
        doNothing().when(dao).createLocation(location);
        locationService.createLocation(location);
        verify(dao, times(1)).createLocation(location);
    }

    @Test
    public void updateLocationTest(){
        when(dao.findLocationById(location.getId())).thenReturn(location);
        locationService.updateLocation(location);
        verify(dao, times(1)).findLocationById(location.getId());
    }

    @Test
    public void deleteLocationByIdTest(){
        doNothing().when(dao).deleteLocationById(location.getId());
        locationService.deleteLocationById(location.getId());
        verify(dao, times(1)).deleteLocationById(location.getId());
    }

    @Test
    public void findAllLocationsTest(){
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        when(dao.findAllLocations()).thenReturn(locations);
        List<Location> actualResultList = locationService.findAllLocations();
        verify(dao, times(1)).findAllLocations();
        Assert.assertEquals(locations.size(), actualResultList.size());
        Assert.assertEquals(locations.get(0).getName(), actualResultList.get(0).getName());
    }

    @Test
    public void findLocationByTypeTest(){
        List<Location> locationsByType = new ArrayList<>();
        locationsByType.add(location);
        when(dao.findLocationsByType(location.getType())).thenReturn(locationsByType);
        List<Location> actualResultList = locationService.findLocationByType(location.getType());
        verify(dao, times(1)).findLocationsByType(location.getType());
        Assert.assertEquals(locationsByType.size(), actualResultList.size());
        Assert.assertEquals(locationsByType.get(0).getName(), actualResultList.get(0).getName());
    }

    @Test
    public void findLocationByIdTest(){
        when(dao.findLocationById(location.getId())).thenReturn(location);
        Location actualResult = locationService.findLocationById(location.getId());
        verify(dao, times(1)).findLocationById(location.getId());
        Assert.assertEquals(location.getName(), actualResult.getName());
    }

    @Test
    public void findLocationByNameTest(){
        when(dao.findLocationByName(location.getName())).thenReturn(location);
        Location actualResult = locationService.findLocationByName(location.getName());
        verify(dao, times(1)).findLocationByName(location.getName());
        Assert.assertEquals(location.getName(), actualResult.getName());
    }

    @Test
    public void isLocationNameUniqueTest(){
        when(dao.findLocationByName(location.getName())).thenReturn(location);
        boolean actualResult = locationService.isLocationNameUnique(location.getId(), location.getName());
        verify(dao, times(1)).findLocationByName(location.getName());
        Assert.assertEquals(true, actualResult);
    }

    private Address initAddressObject(){
        address = new Address();
        address.setId(new Long(1));
        address.setStreet("testStreet");
        address.setNumber("testNumber");
        address.setZipCode("testZip");
        address.setCity("testCity");
        address.setCountry("testCountry");
        address.setLongitude(12.00);
        address.setLongitude(12.00);
        return address;
    }

    private void initLocatiobnObjects(){
        location = new Location();
        location.setId(new Long(1));
        location.setName("testName");
        location.setType("testType");
        location.setAddress(initAddressObject());
    }
}
