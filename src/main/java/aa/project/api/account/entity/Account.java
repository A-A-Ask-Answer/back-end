package aa.project.api.account.entity;

import aa.project.BaseEntity;
import aa.project.api.account.entity.type.Gender;
import aa.project.api.account.entity.type.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@NoArgsConstructor
public class Account extends BaseEntity {

    @Column(unique = true, nullable = false, length = 10)
    private String keyWord;

    @Column(unique = true, nullable = false, length = 15)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isActive; // 탈퇴여부

    public Account(String keyWord, String loginId, String password, String name, Gender gender) {
        this.keyWord = keyWord;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public void createdAccountRole(Role role) {
        this.role = role;
    }
}