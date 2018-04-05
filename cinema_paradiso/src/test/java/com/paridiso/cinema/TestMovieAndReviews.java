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
import java.util.Set;

import static com.paridiso.cinema.Constants.hibernatePassword;
import static com.paridiso.cinema.Constants.hibernateUsername;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class TestMovieAndReviews {
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
    public void testOneToManyMovieAndReviews() {
        User u1 = new User();
        User u2 = new User();

        List<Review> reviewList1 = new ArrayList();
        Review r1 = new Review();
        Review r2 = new Review();

        Film m1 = new Movie();

        // set movie data
        m1.setImdbId("tt000001");

        // set user data
        u1.setUserID(234);
        u1.setEmail("mel@gmail.com");
        u1.setUsername("Mel");

        u2.setUserID(456);
        u2.setEmail("melanie@gmail.com");
        u2.setUsername("Melanie");

        // set review data
        r1.setReviewId(new Long(1));
        r1.setUser(u1);
        r1.setMovie((Movie)m1);

        r2.setReviewId(new Long(2));
        r2.setUser(u2);
        r2.setMovie((Movie)m1);

        reviewList1.add(r1);
        reviewList1.add(r2);

        // add to the movie
        m1.setReviews(reviewList1);

        session.save(m1);
        session.save(u1);
        session.save(u2);
        session.save(r1);
        session.save(r2);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        // able to get reviews
        Movie testMovie = (Movie) session.get(Movie.class, m1.getImdbId());
        List<Review> testReviews = testMovie.getReviews();
        assertEquals(2, testReviews.size());

        // able to get user 1
        assertNotNull(testReviews.get(0).getUser());
        User testUser1 = testReviews.get(0).getUser();
        assertEquals("Mel", testUser1.getUsername());

        assertNotNull(testReviews.get(1).getUser());
        User testUser2 = testReviews.get(1).getUser();
        assertEquals("Melanie", testUser2.getUsername());

        // get reviews from a user
        assertNotNull(testUser1.getReviews());
        List<Review> testReviews2 = testUser1.getReviews();
        assertEquals(testReviews2.size(), 1);

    }

}
