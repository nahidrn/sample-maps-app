package com.mm.app.converter;

import com.mm.app.model.User;
import com.mm.app.model.UserProfile;
import com.mm.app.service.UserProfileService;
import com.mm.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);
	
	@Autowired
	UserProfileService userProfileService;

	/**
	 * Gets UserProfile by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(Object)
	 */
	public UserProfile convert(Object element) {
		Long id = Long.parseLong((String)element);
		UserProfile profile= userProfileService.findById(id);
		logger.info("User : {}",profile);
		return profile;
	}
	
}