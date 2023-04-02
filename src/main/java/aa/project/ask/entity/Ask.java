package aa.project.ask.entity;

import aa.project.account.entity.Account;
import aa.project.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@DynamicUpdate
public class Ask extends BaseEntity {

    @Column(nullable = false)
    @ColumnDefault("false")
    private final Boolean isRealNameOpen = false;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    private AskRoom askRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ask askParent;

    private String randomNickname;

    public Ask(String content, Account account, AskRoom askRoom, String randomNickname) {
        this.content = content;
        this.account = account;
        this.askRoom = askRoom;
        this.randomNickname = randomNickname;
    }

    public void inputAskParentId(Ask askParent) {
        this.askParent = askParent;
    }

}
