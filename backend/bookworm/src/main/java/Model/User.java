package Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    @Column(name = "register_date")
    private Date registerDate;
    private String image;

    public User(Integer id, String username, String email, String password, Date registerDate, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
        this.image = image;
    }
}
