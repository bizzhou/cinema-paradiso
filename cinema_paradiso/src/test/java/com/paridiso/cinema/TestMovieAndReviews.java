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
        UserProfile up1 = new UserProfile();
        Movie m1 = new Movie();
        m1.setImdbId("tt111");

        Review r1 = new Review();
        Review r2 = new Review();
        List<Review> reviews = new ArrayList();
        reviews.add(r1);
        reviews.add(r2);

        r1.setMovie(m1);
        r2.setMovie(m1);
        r1.setUserProfile(up1);
        r2.setUserProfile(up1);

        // add reviews to movie 1
        m1.setReviews(reviews);

        // add reviews to user 1
        up1.setReviews(reviews);

        session.save(m1);
        session.save(up1);
        session.save(r1);
        session.save(r2);
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        // get m1 reviews
        assertNotNull(session.get(Movie.class, m1.getImdbId()));
        Movie testm1 = session.get(Movie.class, m1.getImdbId());
        assertEquals(2, testm1.getReviews().size());

        // get user 1's reviews
        assertNotNull(session.get(UserProfile.class, up1.getId()));
        UserProfile testUp1 = session.get(UserProfile.class, up1.getId());
        assertEquals(2, testUp1.getReviews().size());
    }

}
