package backend.src.main.java.dao;
import backend.src.main.java.domain.Kweet;
import backend.src.main.java.domain.User;
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

    @Override
    public void changeUsername(Long id, String username) {
        User user = em.find(User.class, id);
        user.setUsername(username);
    }

    @Override
    public void changeBio(Long id, String bio) {
        User user = em.find(User.class, id);
        user.setBio(bio);
    }

    @Override
    public void postKweet(User user, Kweet kweet) {
        user.postKweet(kweet);
        em.persist(kweet);
    }

    @Override
    public void followUser(User user, User userToFollow) {
        user.followUser(userToFollow);
    }

    @Override
    public User loadUserInformation(Long id) {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public ArrayList<Kweet> getLastTenKweets(Long id) {
        Query query = em.createQuery("SELECT k FROM Kweet k WHERE k.sendBy.id =: id");
        query.setParameter("id", id);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<User> getFollowers(User user) {
        Query query = em.createQuery("SELECT u FROM User u WHERE :user MEMBER OF u.following");
        query.setParameter("user", user);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<User> getFollowings(Long id) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u IN (SELECT u.following FROM User u WHERE u.id =: id)");
        query.setParameter("id", id);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<Kweet> findKweet(String searchterm) {
        Query query = em.createQuery("SELECT k FROM Kweet k WHERE k.message LIKE CONCAT('%',:searchterm,'%')");
        query.setParameter("searchterm", searchterm);
        return new ArrayList<>(query.getResultList());
    }

    @Override
    public ArrayList<Kweet> loadKweets(Long id) {
        Query query = em.createQuery("SELECT k FROM Kweet k WHERE k.sendBy IN (SELECT u.following FROM User u WHERE u.id =: id) OR k.sendBy.id =: id");
        query.setParameter("id", id);
        return new ArrayList<>(query.getResultList());
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
