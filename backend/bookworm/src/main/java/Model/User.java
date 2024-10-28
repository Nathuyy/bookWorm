package Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;


import java.util.Date;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(unique = true)
    private String email;

    @Size(min = 4)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "register_date")
    private Date registerDate;
    private String image;

    @PrePersist
    protected void onCreate() {
        this.registerDate = new Date();
    }
}