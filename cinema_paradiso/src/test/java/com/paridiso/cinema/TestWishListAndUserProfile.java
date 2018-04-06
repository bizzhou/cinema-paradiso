package com.paridiso.cinema;

import com.paridiso.cinema.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.paridiso.cinema.Constants.hibernatePassword;
import static com.paridiso.cinema.Constants.hibernateUsername;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestWishListAndUserProfile {
    private static SessionFactory sessionFactory;

    private Session session;

    @BeforeClass
    public static void beforeTests() {
        Configuration configuration = new Configuration().addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Trailer.class)
                .addAnnotatedClass(Celebrity.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(UserProfile.class)
                .addAnnotatedClass(WishList.class)
                .addAnnotatedClass(WatchList.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                .setProperty("com.mysql.jdbc.Driver", " com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test?useSSL=false")
                .setProperty("hibernate.connection.username", hibernateUsername)
                .setProperty("hibernate.connection.password", hibernatePassword)
                .setProperty("hibernate.hbm2ddl.auto", "create-drop");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Before
    public void setUp() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @Test
    public void testOneToOneUserProfileAndWishlist() {
        UserProfile up1 = new UserProfile();
        UserProfile up2 = new UserProfile();

        WishList wl1 = new WishList();
        WishList wl2 = new WishList();
        up1.setWishList(wl1);
        WatchList watchList = new WatchList();
        up1.setWatchList(watchList);

        up2.setWishList(wl2);

        // save movies to wl1
        Movie m1 = new Movie();
        m1.setImdbId("tt001");
        Movie m2 = new Movie();
        m2.setImdbId("tt002");
        List<Movie> movies = new ArrayList<>();
        movies.add(m1);
        movies.add(m2);
        wl1.setMovies(movies);
        wl2.setMovies(movies);

        session.save(up1);
        session.save(up2);
        session.save(wl1);
        session.save(wl2);
        session.save(m1);
        session.save(m2);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        // get user 1's wishlist and watchlist
        assertNotNull(session.get(UserProfile.class, up1.getId()));
        UserProfile testUp1 = session.get(UserProfile.class, up1.getId());
        UserProfile testUp2 = session.get(UserProfile.class, up2.getId());
        assertNotNull(testUp1.getWatchList());
        assertNotNull(testUp1.getWishList());

        assertNotNull(testUp2.getWishList());

        // get movies from wishlist 1
        assertNotNull(testUp1.getWishList().getMovies());
        assertEquals(2, testUp1.getWishList().getMovies().size());
        assertEquals(2, testUp2.getWishList().getMovies().size());

    }


}
