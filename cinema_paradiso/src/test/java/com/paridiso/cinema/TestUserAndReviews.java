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
import static org.junit.Assert.*;

public class TestUserAndReviews {

    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeClass
    public static void beforeTests() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class)
                .addAnnotatedClass(Review.class)
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
    public void testOneToManyUserAndReviews() {
        User u1 = new User();
        u1.setUserID(234);
        u1.setEmail("mel@gmail.com");
        u1.setUsername("Mel");

        List<Review> reviewList = new ArrayList();
        Review r1 = new Review();
        r1.setReviewId(new Long(1));
        r1.setUser(u1);
        Review r2 = new Review();
        r2.setReviewId(new Long(2));
        r2.setUser(u1);
        reviewList.add(r1);
        reviewList.add(r2);

        u1.setReviews(reviewList);

        session.save(u1);
        session.save(r1);
        session.save(r2);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        User uTest1 = session.get(User.class, u1.getUserID());
        List<Review> u1Reviews = uTest1.getReviews();
        assertEquals(2, u1Reviews.size());

    }

}
