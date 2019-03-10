package Mo.KwetterApp.domain;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity()
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true, nullable = false)
    private String username;
    private String password;
    private Role role;
    @ManyToMany
    private List<User> following;
    @ManyToMany
    private List<User> followers;
    @OneToMany
    private List<Kweet> kweets;
    @Column(unique=true, nullable = false)
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String location;
    @Column(length = 140)
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
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.kweets = new ArrayList<>();
    }

    public User() {
    }

    public Long getId() { return this.id; }

    public String getUsername() { return this.username; }

    public String getBio() { return this.bio; }

    public List<Kweet> getKweets() { return this.kweets; }

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
