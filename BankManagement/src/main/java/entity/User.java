package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "users")
@Builder
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
