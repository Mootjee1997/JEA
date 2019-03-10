package Mo.KwetterApp.services;
import Mo.KwetterApp.dao.JPA;
import Mo.KwetterApp.dao.KwetterDao;
import Mo.KwetterApp.dao.KwetterDaoJPA;
import Mo.KwetterApp.domain.Kweet;
import Mo.KwetterApp.domain.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;

@Stateless
public class KwetterService {
    @Inject
    @JPA
    private KwetterDaoJPA kwetterDao = new KwetterDaoJPA();

    public void registerUser(User user) {
        kwetterDao.registerUser(user);
    }

    public void changeUsername(int id, String username) {
        kwetterDao.changeUsername(id, username);
    }

    public void changeBio(int id, String bio) {
        kwetterDao.changeBio(id, bio);
    }

    public void postKweet(User user, Kweet kweet) {
        kwetterDao.postKweet(user, kweet);
    }

    public void followUser(User user, User userToFollow) {
        kwetterDao.followUser(user, userToFollow);
    }

    public User loadUserInformation(int id) {
        return kwetterDao.loadUserInformation(id);
    }

    public ArrayList<Kweet> getLastTenKweets(int id) {
        return kwetterDao.getLastTenKweets(id);
    }

    public ArrayList<User> getFollowers(User user) {
        return kwetterDao.getFollowers(user);
    }

    public ArrayList<User> getFollowings(int id) {
        return kwetterDao.getFollowings(id);
    }

    public ArrayList<Kweet> findKweet(String searchterm) {
        return kwetterDao.findKweet(searchterm);
    }

    public ArrayList<Kweet> loadKweets(int id) {
        return kwetterDao.loadKweets(id);
    }

    public KwetterService() {
    }
}
