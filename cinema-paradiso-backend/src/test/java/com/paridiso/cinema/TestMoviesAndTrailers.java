package com.paridiso.cinema;

import com.paridiso.cinema.entity.*;
import org.junit.Test;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.HSQLDialect;
import org.hibernate.service.ServiceRegistry;
import org.junit.*;

import java.util.Set;

import static com.paridiso.cinema.Constants.hibernatePassword;
import static com.paridiso.cinema.Constants.hibernateUsername;
import static org.junit.Assert.*;

public class TestMoviesAndTrailers {

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
    public void testManyToOneMoviesAndTrailers() {
        Film m1 = new Movie();
//        m1(1);
        m1.setImdbId("tt000001");

        Trailer t1 = new Trailer();
        t1.setTrailerId(1);
        t1.setFilm((Movie)m1);

        session.save(m1);
        session.save(t1);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        assertNotNull(session.get(Movie.class, m1.getImdbId()));
        Movie m2 = (Movie) session.get(Movie.class, m1.getImdbId());
        Set<Trailer> trailerSet = m2.getTrailers();
        assertNotEquals(0, trailerSet.size());
    }



}
