//package com.paridiso.cinema;
//
//import com.paridiso.cinema.entity.*;
//import com.paridiso.cinema.service.implementation.RegUserServiceImpl;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.paridiso.cinema.Constants.hibernatePassword;
//import static com.paridiso.cinema.Constants.hibernateUsername;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//@Component
//public class TestInitUser {
//
//    private static SessionFactory sessionFactory;
//
//    private Session session;
//
//    @Autowired
//    RegUserServiceImpl regUserService;
//
//    @BeforeClass
//    public static void beforeTests() {
//        Configuration configuration = new Configuration().addAnnotatedClass(Movie.class)
//                .addAnnotatedClass(Trailer.class)
//                .addAnnotatedClass(Celebrity.class)
//                .addAnnotatedClass(Review.class)
//                .addAnnotatedClass(User.class)
//                .addAnnotatedClass(UserProfile.class)
//                .addAnnotatedClass(WishList.class)
//                .addAnnotatedClass(WatchList.class)
//                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
//                .setProperty("com.mysql.jdbc.Driver", " com.mysql.jdbc.Driver")
//                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test?useSSL=false")
//                .setProperty("hibernate.connection.username", hibernateUsername)
//                .setProperty("hibernate.connection.password", hibernatePassword)
//                .setProperty("hibernate.hbm2ddl.auto", "create-drop");
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//    }
//
//    @Before
//    public void setUp() {
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//    }
//
//    @Test
//    public void testInitUser() {
//        // create user
//        User user = new User();
//        user.setUsername("melanie");
//        user.setEmail("mel@gmail.com");
//        user = regUserService.initUserProfile(user);
//
//        User user2 = new User();
//        user2.setUsername("hello");
//        user2.setEmail("hello@gmail.com");
//        user2 = regUserService.initUserProfile(user2);
//
//        // add movie to wishlist
//        Movie m1 = new Movie();
//        m1.setImdbId("tt001");
//        Movie m2 = new Movie();
//        m2.setImdbId("tt002");
//        Movie m3 = new Movie();
//        m3.setImdbId("tt003");
//        List<Movie> movies = new ArrayList<>();
//        movies.add(m1);
//        movies.add(m2);
//        movies.add(m3);
//        user.getUserProfile().getWishList().setMovies(movies);
//        user2.getUserProfile().getWishList().setMovies(movies);
//
//        assertEquals(3, user.getUserProfile().getWishList().getMovies().size());
//        assertEquals(3, user2.getUserProfile().getWishList().getMovies().size());
//        session.save(m1);
//        session.save(m2);
//        session.save(m3);
//
//        regUserService.saveUser(user);
//        regUserService.saveUser(user2);
//
//        session.getTransaction().commit();
//        session.close();
//
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        // get the user's wishlist
//        User testUser = session.get(User.class, user.getUserID());
//        User testUser2 = session.get(User.class, user2.getUserID());
//        assertEquals(3, testUser.getUserProfile().getWishList().getMovies().size());
//        assertEquals(3, testUser2.getUserProfile().getWishList().getMovies().size());
//
//    }
//
//}
