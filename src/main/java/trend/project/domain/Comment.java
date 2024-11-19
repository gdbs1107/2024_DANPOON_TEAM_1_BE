package trend.project.domain;

import jakarta.persistence.*;
import lombok.*;
import trend.project.domain.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Comment extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String body;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
    
    @Column(nullable = false)
    private int likesCount = 0;
    
    private LocalDateTime deletedAt;
    
    @Column(nullable = false)
    private boolean deletedTrue = false;
    
    @Column(nullable = false)
    private Long hierarchy = 0L;
    
    @Column(nullable = false)
    private Long orders = 0L;
    
    @Column(name = "comment_group", nullable = false)
    private Long groups = 0L;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public void setDeletedTrue(boolean deletedTrue) {
        this.deletedTrue = deletedTrue;
    }
}
