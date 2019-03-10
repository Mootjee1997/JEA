package dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import backend.src.main.java.dao.KwetterDaoJPA;
import backend.src.main.java.domain.Kweet;
import backend.src.main.java.domain.Role;
import backend.src.main.java.domain.User;
import backend.src.main.test.util.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class KwetterDaoJPAIT {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("KwetterTest");
    private EntityManager em;
    private EntityTransaction et;
    private KwetterDaoJPA kwetterDao;

    public KwetterDaoJPAIT() {
    }

    @Before
    public void setUp() {
        new DatabaseCleaner(emf.createEntityManager()).clean();
        em = emf.createEntityManager();
        et = em.getTransaction();

        kwetterDao = new KwetterDaoJPA();
        kwetterDao.setEm(em);
    }

    @Test
    public void changeUsername() {
        String expected = "Mohamed";
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        et.begin();
        kwetterDao.changeUsername(user.getId(), expected);
        et.commit();
        String username = user.getUsername();
        assertEquals(expected, username);
    }

    @Test
    public void changeBio() {
        String expected = "My new bio";
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        et.begin();
        kwetterDao.changeBio(user.getId(), expected);
        et.commit();
        String bio = user.getBio();
        assertEquals(expected, bio);
    }

    @Test
    public void postKweet() {
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        Kweet kweet = new Kweet(user, new Date(), "My tweet!");
        et.begin();
        kwetterDao.postKweet(user, kweet);
        et.commit();
        assertTrue(user.getKweets().size() > 0);
    }

    @Test
    public void loadUserInformation() {
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        et.begin();
        User user1 = kwetterDao.loadUserInformation(user.getId());
        et.commit();
        assertEquals(user, user1);
    }

    @Test
    public void getLastTenKweets() {
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        Kweet kweet1 = new Kweet(user, new Date(), "My 1st tweet!");
        Kweet kweet2 = new Kweet(user, new Date(), "My 2nd tweet!");
        Kweet kweet3 = new Kweet(user, new Date(), "My 3rd tweet!");
        Kweet kweet4 = new Kweet(user, new Date(), "My 4th tweet!");
        Kweet kweet5 = new Kweet(user, new Date(), "My 5th tweet!");
        Kweet kweet6 = new Kweet(user, new Date(), "My 6th tweet!");
        Kweet kweet7 = new Kweet(user, new Date(), "My 7th tweet!");
        Kweet kweet8 = new Kweet(user, new Date(), "My 8th tweet!");
        Kweet kweet9 = new Kweet(user, new Date(), "My 9th tweet!");
        Kweet kweet10 = new Kweet(user, new Date(), "My 10th tweet!");
        Kweet kweet11 = new Kweet(user, new Date(), "My 11th tweet!");
        et.begin();
        kwetterDao.postKweet(user, kweet1);
        kwetterDao.postKweet(user, kweet2);
        kwetterDao.postKweet(user, kweet3);
        kwetterDao.postKweet(user, kweet4);
        kwetterDao.postKweet(user, kweet5);
        kwetterDao.postKweet(user, kweet6);
        kwetterDao.postKweet(user, kweet7);
        kwetterDao.postKweet(user, kweet8);
        kwetterDao.postKweet(user, kweet9);
        kwetterDao.postKweet(user, kweet10);
        kwetterDao.postKweet(user, kweet11);
        int result = kwetterDao.getLastTenKweets(user.getId()).size();
        et.commit();
        assertEquals(10, result);
    }

    @Test
    public void getFollowers() {
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        User user1 = new User("Frederik", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        User user2 = new User("Hendrik", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        et.begin();
        kwetterDao.followUser(user1, user);
        kwetterDao.followUser(user2, user);
        int result = kwetterDao.getFollowers(user).size();
        et.commit();
        assertEquals(2, result);
    }

    @Test
    public void getFollowings() {
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        User user1 = new User("Frederik", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        User user2 = new User("Hendrik", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        et.begin();
        kwetterDao.followUser(user, user1);
        kwetterDao.followUser(user, user2);
        int result = kwetterDao.getFollowings(user.getId()).size();
        et.commit();
        assertEquals(2, result);
    }

    @Test
    public void findKweet() {
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        Kweet kweet = new Kweet(user, new Date(), "My tweet!");
        et.begin();
        kwetterDao.postKweet(user, kweet);
        int result = kwetterDao.findKweet("My").size();
        et.commit();
        assertEquals(1, result);
    }

    @Test
    public void loadKweets() {
        User user = new User("Mo", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        User user1 = new User("Frederik", "wachtwoord123", Role.USER, null, null, null, null, null, "My bio.");
        Kweet kweet = new Kweet(user, new Date(), "Mo's tweet!");
        Kweet kweet1 = new Kweet(user, new Date(), "Hendrik's tweet!");
        et.begin();
        kwetterDao.followUser(user, user1);
        kwetterDao.postKweet(user, kweet);
        kwetterDao.postKweet(user1, kweet1);
        int result = kwetterDao.loadKweets(user.getId()).size();
        et.commit();
        assertEquals(2, result);
    }
}
