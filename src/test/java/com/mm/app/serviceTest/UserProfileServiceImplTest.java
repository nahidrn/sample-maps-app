package com.mm.app.serviceTest;


import com.mm.app.config.AppConfig;
import com.mm.app.constants.UserProfileType;
import com.mm.app.dao.UserProfileDao;
import com.mm.app.model.UserProfile;
import com.mm.app.service.impl.UserProfileServiceImpl;
import org.junit.*;
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
public class UserProfileServiceImplTest {

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @Mock
    private UserProfileDao dao;

    private UserProfile userProfile;

    @Before
    public void init(){
        initUserProfileObjec();
    }

    @Test
    public void findByIdTest(){
        when(dao.findById(userProfile.getId())).thenReturn(userProfile);
        UserProfile actualResult = userProfileService.findById(userProfile.getId());
        verify(dao, times(1)).findById(userProfile.getId());
        Assert.assertEquals(userProfile.getId(), actualResult.getId());
        Assert.assertEquals(userProfile.getType(), actualResult.getType());
    }

    @Test
    public void findByTypeTest(){
        when(dao.findByType(userProfile.getType())).thenReturn(userProfile);
        UserProfile actualResult = userProfileService.findByType(userProfile.getType());
        verify(dao, times(1)).findByType(userProfile.getType());
        Assert.assertEquals(userProfile.getId(), actualResult.getId());
        Assert.assertEquals(userProfile.getType(), actualResult.getType());
    }

    @Test
    public void findAllTest(){
        List<UserProfile> userProfiles = new ArrayList<>();
        userProfiles.add(userProfile);
        when(dao.findAll()).thenReturn(userProfiles);
        List<UserProfile> actualResultList = userProfileService.findAll();
        verify(dao, times(1)).findAll();
        Assert.assertEquals(userProfiles.size(), actualResultList.size());
        Assert.assertEquals(userProfiles.get(0).getId(), actualResultList.get(0).getId());
        Assert.assertEquals(userProfiles.get(0).getType(), actualResultList.get(0).getType());
    }

    private void initUserProfileObjec(){
        userProfile = new UserProfile();
        userProfile.setId(new Long(1));
        userProfile.setType(UserProfileType.USER.getUserProfileType());
    }
}
