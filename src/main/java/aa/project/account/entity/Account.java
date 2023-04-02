package aa.project.account.entity;

import aa.project.account.entity.type.Gender;
import aa.project.account.entity.type.Role;
import aa.project.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Where(clause = "deleted_at is null")
@NoArgsConstructor
public class Account extends BaseEntity {

    @Column(nullable = false)
    @ColumnDefault("false")
    private final Boolean isTerms = false;
    @Column(unique = true, nullable = false, length = 10)
    private String keyword;
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

    public Account(String keyword, String loginId, String password, String name, Gender gender) {
        this.keyword = keyword;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.gender = gender;
    }

    public void createdAccountRole(Role role) {
        this.role = role;
    }
}