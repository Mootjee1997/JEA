package Mo.KwetterApp.dao;
import Mo.KwetterApp.domain.Kweet;
import Mo.KwetterApp.domain.User;
import java.util.ArrayList;

public interface KwetterDao {
    void registerUser(User user);
    void changeUsername(int id, String username);
    void changeBio(int id, String bio);
    void postKweet(User user, Kweet kweet);
    void followUser(User user, User userToFollow);
    User loadUserInformation(int id);
    ArrayList<Kweet> getLastTenKweets(int id);
    ArrayList<User> getFollowers(User user);
    ArrayList<User> getFollowings(int id);
    ArrayList<Kweet> findKweet(String searchterm);
    ArrayList<Kweet> loadKweets(int id);
}
