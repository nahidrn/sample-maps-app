package com.mm.app.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mm.app.model.Location;
import com.mm.app.service.LocationService;

@Controller
@RequestMapping("/")
public class LocationController {

    @Autowired
	LocationService locationService;

    @Autowired
    MessageSource messageSource;
	
	/*
     * This method will list all existing locations.
     */
    @RequestMapping(value = { "/", "/locations" }, method = RequestMethod.GET)
    public String getLocationList(ModelMap model) {

        List<Location> locations = locationService.findAllLocations();
        for (Location loc : locations) {
			System.out.println(loc.getAddress());
		}
        model.addAttribute("locations", locations);
        return "locations";
    }

    @RequestMapping(value = { "/", "/createlocation" }, method = RequestMethod.GET)
	public String newLocation(ModelMap model) {
		Location location = new Location();
		model.addAttribute("location", location);
		model.addAttribute("edit", false);
		return "createlocation";
	}

	@RequestMapping(value = { "/createlocation" }, method = RequestMethod.POST)
	public String saveLocation(@Valid Location location, BindingResult result,
						   ModelMap model) {

		System.out.println(result);
		if (result.hasErrors()) {
			return "createlocation";
		}

        /*
         * Preferred way to achieve uniqueness of field [name] should be implementing custom @Unique annotation
         * and applying it on field [name] of Model class [User].
         *
         *
         */
		if(!locationService.isLocationNameUnique(location.getId(), location.getName())){
			FieldError nameUniqueError =new FieldError("location","name",messageSource.getMessage("non.unique.locationname", new String[]{location.getName()}, Locale.getDefault()));
			result.addError(nameUniqueError);
			return "createlocation";
		}

		locationService.createLocation(location);

		model.addAttribute("success", "Location " + location.getName() + " created successfully");
		return "redirect:/locations";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{id}-location" }, method = RequestMethod.GET)
	public String editUser(@PathVariable Long id, ModelMap model) {
		Location location = locationService.findLocationById(id);
		model.addAttribute("location", location);
		model.addAttribute("edit", true);
		return "createlocation";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating location in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{id}-location" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Location location, BindingResult result,
								 ModelMap model, @PathVariable Long id) {

		if (result.hasErrors()) {
			return "createlocation";
		}

		if(!locationService.isLocationNameUnique(location.getId(), location.getName())){
			FieldError nameUniqueError =new FieldError("location","name",messageSource.getMessage("non.unique.locationname", new String[]{location.getName()}, Locale.getDefault()));
			result.addError(nameUniqueError);
			return "createlocation";
		}

		locationService.updateLocation(location);

		model.addAttribute("success", "Location " + location.getName()  + " updated successfully");
		return "redirect:/locations";
	}


	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{id}-location" }, method = RequestMethod.GET)
	public String deleteLocation(@PathVariable Long id) {
		locationService.deleteLocationById(id);
		return "redirect:/locations";
	}

}
