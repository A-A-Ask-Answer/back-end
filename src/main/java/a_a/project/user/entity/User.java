package a_a.project.user.entity;

import a_a.project.user.entity.type.Gender;
import a_a.project.user.entity.type.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private boolean isActive; // 탈퇴여부

    public User(String keyWord, String loginId, String password, String name) {
        this.keyWord = keyWord;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = Role.USER;
    }

    public void gender(Gender gender) {
        this.gender = gender;
    }
}
