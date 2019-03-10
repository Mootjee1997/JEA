package Mo.KwetterApp.dao;
import Mo.KwetterApp.domain.Kweet;
import Mo.KwetterApp.domain.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;

@Stateless
@JPA
public class KwetterDaoJPA implements KwetterDao {
    @PersistenceContext(unitName = "KwetterPU")
    private EntityManager em;

    public KwetterDaoJPA() {
    }

    public void registerUser(User user) {
        em.persist(user);
    }

    public void changeUsername(int id, String username) {
        User user = em.find(User.class, id);
        user.setUsername(username);
    }

    public void changeBio(int id, String bio) {
        User user = em.find(User.class, id);
        user.setBio(bio);
    }

    public void postKweet(User user, Kweet kweet) {
        user.postKweet(kweet);
        em.persist(kweet);
    }

    public void followUser(User user, User userToFollow) {
        user.followUser(userToFollow);
    }

    public User loadUserInformation(int id) {
        User user = em.find(User.class, id);
        return user;
    }

    public ArrayList<Kweet> getLastTenKweets(int id) {
        Query query = em.createQuery("SELECT k FROM Kweet k WHERE k.sendBy.id =: id");
        query.setParameter("id", id);
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<User> getFollowers(User user) {
        Query query = em.createQuery("SELECT u FROM User u WHERE :user MEMBER OF u.following");
        query.setParameter("user", user);
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<User> getFollowings(int id) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u IN (SELECT u.following FROM User u WHERE u.id =: id)");
        query.setParameter("id", id);
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<Kweet> findKweet(String searchterm) {
        Query query = em.createQuery("SELECT k FROM Kweet k WHERE k.message LIKE CONCAT('%',:searchterm,'%')");
        query.setParameter("searchterm", searchterm);
        return new ArrayList<>(query.getResultList());
    }

    public ArrayList<Kweet> loadKweets(int id) {
        Query query = em.createQuery("SELECT k FROM Kweet k WHERE k.sendBy IN (SELECT u.following FROM User u WHERE u.id =: id) OR k.sendBy.id =: id");
        query.setParameter("id", id);
        return new ArrayList<>(query.getResultList());
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
