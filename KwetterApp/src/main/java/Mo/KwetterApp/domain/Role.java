package Mo.KwetterApp.domain;
import java.io.Serializable;

public enum Role implements Serializable {
    USER {
        public String toString() {
            return "User";
        }
    },

    ADMIN {
        public String toString() {
            return "Admin";
        }
    }
}
