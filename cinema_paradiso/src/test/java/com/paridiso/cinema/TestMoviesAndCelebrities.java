//package com.paridiso.cinema;
//
//import com.paridiso.cinema.entity.Celebrity;
//import com.paridiso.cinema.entity.Film;
//import com.paridiso.cinema.entity.Movie;
//import com.paridiso.cinema.entity.Trailer;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//import static com.paridiso.cinema.Constants.hibernatePassword;
//import static com.paridiso.cinema.Constants.hibernateUsername;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertNotNull;
//
//public class TestMoviesAndCelebrities {
//
//    private static SessionFactory sessionFactory;
//    private Session session;
//
//    @BeforeClass
//    public static void beforeTests() {
//        Configuration configuration = new Configuration().addAnnotatedClass(Movie.class)
//                .addAnnotatedClass(Trailer.class)
//                .addAnnotatedClass(Celebrity.class)
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
//    public void testOneToOneMoviesAndDirector(){
//        Celebrity c1 = new Celebrity();
//        c1.setCelebrityId("nm000001");
//
//        session.save(c1);
//        assertNotNull(session.get(Celebrity.class, c1.getCelebrityId()));
//
//        Film m1 = new Movie();
//        m1.setImdbId("tt000002");
//
//        // set to c1
//        m1.setDirector(c1);
//
//        session.save(m1);
//        session.save(c1);
//
//        session.getTransaction().commit();
//        session.close();
//
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        assertNotNull(session.get(Movie.class, m1.getImdbId()));
//        Movie m2 = (Movie) session.get(Movie.class, m1.getImdbId());
//        Celebrity director = m2.getDirector();
//        assertNotNull(director);
//    }
//
//    @Test
//    public void testManyToManyMoviesAndCelebrities() {
//        // create 2 movies
//        Movie m1 = new Movie();
//        Movie m2 = new Movie();
//        m1.setImdbId("tt001");
//        m2.setImdbId("tt002");
//
//        // create 2 cels, insert to m1
//        List<Celebrity> cels1 = new ArrayList<>();
//        Celebrity c1 = new Celebrity();
//        Celebrity c2 = new Celebrity();
//        c1.setCelebrityId("nm001");
//        c2.setCelebrityId("nm002");
//        cels1.add(c1);
//        cels1.add(c2);
//        m1.setCast(cels1);
//
//        // create 2 cels, insert to m2
//        List<Celebrity> cels2 = new ArrayList<>();
//        Celebrity c3 = new Celebrity();
//        Celebrity c4 = new Celebrity();
//        c3.setCelebrityId("nm003");
//        c4.setCelebrityId("nm004");
//        cels2.add(c3);
//        cels2.add(c4);
//        m2.setCast(cels2);
//
//        session.save(m1);
//        session.save(m2);
//        session.save(c1);
//        session.save(c2);
//        session.save(c3);
//        session.save(c4);
//        session.getTransaction().commit();
//        session.close();
//
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        // test existence
//        assertNotNull(session.get(Movie.class, m1.getImdbId()));
//        assertNotNull(session.get(Movie.class, m2.getImdbId()));
//        assertNotNull(session.get(Celebrity.class, c1.getCelebrityId()));
//        assertNotNull(session.get(Celebrity.class, c2.getCelebrityId()));
//        assertNotNull(session.get(Celebrity.class, c3.getCelebrityId()));
//        assertNotNull(session.get(Celebrity.class, c4.getCelebrityId()));
//
//        // get celebrities from a movie
//        Movie mTest1 = session.get(Movie.class, m1.getImdbId());
//        List<Celebrity> celsTest1 = mTest1.getCast();
//        assertEquals(2, celsTest1.size());
//        assertEquals(celsTest1.get(0).getCelebrityId(), "nm001");
//        assertEquals(celsTest1.get(1).getCelebrityId(), "nm002");
//
//        // get movies from a celebrity
//        Celebrity cTest1 = session.get(Celebrity.class, c1.getCelebrityId());
//        Set<Movie> msTest1 = cTest1.getFilmography();
//        assertEquals(1, msTest1.size());
//
//    }
//
//
//
//}
