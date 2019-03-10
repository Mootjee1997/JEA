package backend.src.main.java.domain;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity()
@XmlRootElement
public class Kweet implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private User sendBy;
    private Date timeSend;
    private String message;

    public Kweet(User sendBy, Date timeSend, String message) {
        this.sendBy = sendBy;
        this.timeSend = timeSend;
        this.message = message;
    }

    public Kweet() {

    }
}
