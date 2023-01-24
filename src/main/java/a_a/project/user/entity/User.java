package a_a.project.user.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String keyWord;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String name;

    private String role;

    private String gender;
}
