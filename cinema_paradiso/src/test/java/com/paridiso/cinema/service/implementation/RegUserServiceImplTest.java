package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.entity.WatchList;
import com.paridiso.cinema.entity.WishList;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.persistence.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class RegUserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private UtilityServiceImpl utilityService;

    @InjectMocks
    private RegUserServiceImpl userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this) ;
    }

    @Test
    public void signup() throws Exception{

        User user = new User();
        user.setUsername("bizzhou");
        user.setEmail("123@gmail.com");
        user.setRole(Role.ROLE_USER);
        user.setAccountSuspended(false);
        user.setPassword("123");

        User user2 = new User();
        user2.setUserID(1);
        user2.setUserProfile(new UserProfile());
        user2.setAccountSuspended(false);
        user2.setUsername("bizzhou");
        user2.setEmail("123@gmail.com");
        user2.setRole(Role.ROLE_USER);
        user2.setPassword("234");

        when(userRepository.save(user)).thenReturn(user2);
        when(utilityService.getHashedPassword("123", "v,@eJ.9zBg9z@!*s")).thenReturn("234");


        Optional<User> returnUser = userService.signup(user);
        assertEquals("234", returnUser.get().getPassword());
        assertNotNull(returnUser.get().getUserProfile());
        assertEquals(Role.ROLE_USER, returnUser.get().getRole());

    }

    @Test(expected = ResponseStatusException.class)
    public void testDuplicateSignUp() throws Exception{

        User user = new User();
        user.setUsername("bizzhou");
        user.setEmail("123@gmail.com");
        user.setRole(Role.ROLE_USER);
        user.setAccountSuspended(false);
        user.setPassword("123");

        User user2 = new User();
        user2.setUserID(1);
        user2.setUserProfile(new UserProfile());
        user2.setAccountSuspended(false);
        user2.setUsername("bizzhou");
        user2.setEmail("123@gmail.com");
        user2.setRole(Role.ROLE_USER);
        user2.setPassword("234");

        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user2);
        when(utilityService.getHashedPassword("123", "v,@eJ.9zBg9z@!*s")).thenReturn("234");

        userService.signup(user);
        userService.signup(user);

    }


    @Test
    public void updateProfile() {

        UserProfile userProfile = new UserProfile();
        userProfile.setBiography("John Doe");
        userProfile.setCritic(false);
        userProfile.setId(1);
        userProfile.setWatchList(new WatchList());
        userProfile.setWishList(new WishList());
        userProfile.setName("John of arc");

        UserProfile userProfile2 = new UserProfile();
        userProfile2.setBiography("Hello world");
        userProfile2.setCritic(false);
        userProfile2.setId(1);
        userProfile2.setWatchList(new WatchList());
        userProfile2.setWishList(new WishList());
        userProfile2.setName("Peter Vim");

        when(userProfileRepository.findById(userProfile.getId())).thenReturn(java.util.Optional.ofNullable(userProfile));
        when(userProfileRepository.save(userProfile)).thenReturn(userProfile2);

        UserProfile userProfile3 = userService.updateProfile(userProfile);

        assertEquals(false, userProfile3.getCritic());
        assertEquals("Peter Vim", userProfile3.getName());

    }

    @Test
    public void makeSummaryPrivate() {
        UserProfile userProfile = new UserProfile();
        userProfile.setBiography("John Doe");
        userProfile.setCritic(false);
        userProfile.setId(1);
        userProfile.setWatchList(new WatchList());
        userProfile.setWishList(new WishList());
        userProfile.setName("John of arc");
        userProfile.setPrivate(false);

        UserProfile userProfile2 = new UserProfile();
        userProfile2.setBiography("John Doe");
        userProfile2.setCritic(false);
        userProfile2.setId(1);
        userProfile2.setWatchList(new WatchList());
        userProfile2.setWishList(new WishList());
        userProfile2.setName("John of arc");
        userProfile2.setPrivate(true);

        when(userProfileRepository.findById(userProfile.getId())).thenReturn(java.util.Optional.ofNullable(userProfile));
        when(userProfileRepository.save(userProfile)).thenReturn(userProfile2);

        assertEquals("John of arc", userService.makeSummaryPrivate(1).getName());
        assertEquals(true, userService.makeSummaryPrivate(1).getPrivate());
    }

    @Test
    public void updatePassword() throws Exception{
        User user = new User();
        user.setUsername("bizzhou");
        user.setEmail("123@gmail.com");
        user.setRole(Role.ROLE_USER);
        user.setAccountSuspended(false);
        user.setPassword("123");

        User user2 = new User();
        user2.setUserID(1);
        user2.setUserProfile(new UserProfile());
        user2.setAccountSuspended(false);
        user2.setUsername("bizzhou");
        user2.setEmail("123@gmail.com");
        user2.setRole(Role.ROLE_USER);
        user2.setPassword("halo");

//        when(utilityService.getHashedPassword("123", "v,@eJ.9zBg9z@!*s")).thenReturn("234");
        when(userRepository.save(user)).thenReturn(user2);
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        doReturn("234").when(utilityService).getHashedPassword("123", "v,@eJ.9zBg9z@!*s");


        User user1 = userService.updatePassword(1, "123", "halo");
        assertEquals("halo", user1.getPassword());
    }

    @Test
    public void checkName() {
        User user = new User();
        user.setUsername("bizzhou");
        user.setEmail("123@gmail.com");
        user.setRole(Role.ROLE_USER);
        user.setAccountSuspended(false);
        user.setPassword("123");

        when(userRepository.findUserByUsername("bizzhou")).thenReturn(user);

        boolean result = userService.checkUserNameTaken("bizzhou");
        assertEquals(true, result);
    }

    @Test
    public void checkEmailTaken() {
        when(userRepository.findUserByEmail("123@gmail.com")).thenReturn(null);
        boolean result = userService.checkEmailTaken("123@gmail.com");
        assertEquals(false, result);
    }

}