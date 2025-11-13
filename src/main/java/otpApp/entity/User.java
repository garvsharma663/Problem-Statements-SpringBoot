package otpApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String otp;
    private boolean verified;


    public User() {}

    public User(Long id, String email, String password, String otp, boolean verified) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.otp = otp;
        this.verified = verified;
    }






}
