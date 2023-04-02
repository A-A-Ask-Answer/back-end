package aa.project.ask.entity;

import aa.project.account.entity.Account;
import aa.project.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Where(clause = "deleted_at is null")
@Getter
public class AskRoom extends BaseEntity {

    @OneToMany(mappedBy = "askRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Ask> askList = new ArrayList<>();
    @Column(nullable = false, length = 255)
    private String title;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Account account;


    public AskRoom(String title, Account account) {
        this.title = title;
        this.account = account;
    }

    public void deleteAskRoom() {
        this.account = null;

    }
}
