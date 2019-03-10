package backend.src.main.java.domain;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity()
@NamedQueries({
        @NamedQuery(name = "user.changeUsername", query = "UPDATE User set username =: newUsername WHERE id =: id"),
        @NamedQuery(name = "user.changeBio", query = "UPDATE User set bio =: bio WHERE id =: id"),
        @NamedQuery(name = "user.getLastTenKweets", query = "SELECT k.id FROM Kweet k WHERE k.sendBy =: user ORDER BY k.timeSend DESC"),
        @NamedQuery(name = "user.getFollowers", query = "SELECT u.id FROM User u WHERE u MEMBER OF u.following"),
        @NamedQuery(name = "user.getFollowers", query = "SELECT u.id FROM User u WHERE u MEMBER OF u.following"),
        @NamedQuery(name = "user.getFollowings", query = "SELECT u.id FROM User u WHERE u MEMBER OF u.following"),
})
@XmlRootElement
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String username;
    private String password;
    private Role role;
    @ManyToMany
    private Collection<User> following;
    @OneToMany
    private Collection<Kweet> kweets;
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String location;
    private String bio;

    public User(String username, String password, Role role, String email, String firstName, String lastName, Date dateOfBirth, String location, String bio){
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.bio = bio;
    }

    public User() {
    }

    public Long getId() { return this.id; }

    public String getUsername() { return this.username; }

    public String getBio() { return this.bio; }

    public Collection<Kweet> getKweets() { return this.kweets; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void postKweet(Kweet kweet) {
        this.kweets.add(kweet);
    }

    public void followUser(User user) {
        this.following.add(user);
    }
}
