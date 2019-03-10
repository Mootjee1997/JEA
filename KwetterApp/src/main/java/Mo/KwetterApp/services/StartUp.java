package Mo.KwetterApp.services;
import Mo.KwetterApp.domain.Kweet;
import Mo.KwetterApp.domain.Role;
import Mo.KwetterApp.domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

@Startup
@Singleton
public class StartUp {

    @Inject
    private KwetterService service = new KwetterService();

    public StartUp() {
    }

    @PostConstruct
    private void intData(){
        User user1 = new User("Mohamed Ali", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        User user2 = new User("Wessel vd Palen", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        User user3 = new User("Glenn Hendriks", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        User user4 = new User("Ali Al-Jaliel", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        User user5 = new User("Mickey Jones", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        User user6 = new User("Hessel Zijlstra", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        User user7 = new User("Hicham Laboudi", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        User user8 = new User("Stijn Scheepers", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        User user9 = new User("Mike de Wit", "wachtwoord123", Role.ADMIN, null, null, null, null, null, "My bio.");
        service.registerUser(user1);
        service.registerUser(user2);
        service.registerUser(user3);
        service.registerUser(user4);
        service.registerUser(user5);
        service.registerUser(user6);
        service.registerUser(user7);
        service.registerUser(user8);
        service.registerUser(user9);
        service.followUser(user1, user2);
        service.followUser(user1, user3);
        service.followUser(user2, user3);
        service.followUser(user2, user4);
        service.followUser(user3, user4);
        service.followUser(user3, user5);
        service.followUser(user4, user5);
        service.followUser(user4, user6);
        service.followUser(user5, user6);
        service.followUser(user5, user7);
        service.followUser(user6, user7);
        service.followUser(user6, user8);
        service.followUser(user7, user8);
        service.followUser(user7, user9);
        service.postKweet(user1, new Kweet(user1, new Date(), "My 1st tweet!"));
        service.postKweet(user2, new Kweet(user2, new Date(), "My 1st tweet!"));
        service.postKweet(user3, new Kweet(user3, new Date(), "My 1st tweet!"));
        service.postKweet(user4, new Kweet(user4, new Date(), "My 1st tweet!"));
        service.postKweet(user5, new Kweet(user5, new Date(), "My 1st tweet!"));
        service.postKweet(user6, new Kweet(user6, new Date(), "My 1st tweet!"));
        service.postKweet(user7, new Kweet(user7, new Date(), "My 1st tweet!"));
        service.postKweet(user8, new Kweet(user8, new Date(), "My 1st tweet!"));
        service.postKweet(user9, new Kweet(user9, new Date(), "My 1st tweet!"));
    }
}