package com.mm.app.serviceTest;

import com.mm.app.config.AppConfig;
import com.mm.app.constants.UserProfileType;
import com.mm.app.dao.UserDao;
import com.mm.app.model.User;
import com.mm.app.model.UserProfile;
import com.mm.app.service.impl.UserServiceImpl;
import org.junit.*;
import static org.mockito.Mockito.doNothing;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = AppConfig.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao dao;

    private UserProfile userProfile;

    private User user;

    @Before
    public void init(){
        initUserObject();
    }

    @Test
    public void findUserByIdTest(){
        when(dao.findUserById(user.getId())).thenReturn(user);
        User actualResult = userService.findUserById(user.getId());
        verify(dao, times(1)).findUserById(user.getId());
        Assert.assertEquals(user.getId(), actualResult.getId());
        Assert.assertEquals(user.getName(), actualResult.getName());
        Assert.assertEquals(user.getPassword(), actualResult.getPassword());
        Assert.assertEquals(user.get_csrf(), actualResult.get_csrf());
        Assert.assertEquals(user.get_userProfiles(), actualResult.get_userProfiles());
        Assert.assertEquals(user.isActive(), actualResult.isActive());
        Assert.assertEquals(user.getUserProfiles().size(), actualResult.getUserProfiles().size());
    }

    @Test
    public void createUserTest(){
        doNothing().when(dao).createUser(user);
        userService.createUser(user);
        verify(dao, times(1)).createUser(user);
    }

    @Test
    public void updateUserTest(){
        when(dao.findUserById(user.getId())).thenReturn(user);
        userService.updateUser(user);
        verify(dao, times(1)).findUserById(user.getId());
    }

    @Test
    public void deleteUserByIdTest(){
        doNothing().when(dao).deleteUserById(user.getId());
        userService.deleteUserById(user.getId());
        verify(dao, times(1)).deleteUserById(user.getId());
    }

    @Test
    public void findAllUsersTest(){
        List<User> users = new ArrayList<>();
        users.add(user);
        when(dao.findAllUsers()).thenReturn(users);
        List<User> actualResultList = userService.findAllUsers();
        verify(dao, times(1)).findAllUsers();
        Assert.assertEquals(users.size(), actualResultList.size());
        Assert.assertEquals(users.get(0).getName(), actualResultList.get(0).getName());
        Assert.assertEquals(users.get(0).getUserProfiles().size(), actualResultList.get(0).getUserProfiles().size());
    }

    @Test
    public void findUserByNameTest(){
        when(dao.findUserByName(user.getName())).thenReturn(user);
        User actualResult = userService.findUserByName(user.getName());
        verify(dao, times(1)).findUserByName(user.getName());
        Assert.assertEquals(user.getName(), actualResult.getName());
    }

    @Test
    public void isUserNameUniqueTest(){
        when(dao.findUserByName(user.getName())).thenReturn(user);
        boolean actualResult = userService.isUserNameUnique(user.getId(), user.getName());
        verify(dao, times(1)).findUserByName(user.getName());
        Assert.assertEquals(true, actualResult);
    }

    private UserProfile initUserProfileObjec(){
        userProfile = new UserProfile();
        userProfile.setId(new Long(1));
        userProfile.setType(UserProfileType.USER.getUserProfileType());
        return userProfile;
    }

    private void initUserObject(){
        user = new User();
        user.setId(new Long(2));
        user.setName("testName");
        user.setPassword("testPassword");
        user.set_csrf("testCsrf");
        user.set_userProfiles(3);
        user.setActive(true);
        Set<UserProfile> userProfileSet = new HashSet<>();
        userProfileSet.add(initUserProfileObjec());
        user.setUserProfiles(userProfileSet);
    }
}
