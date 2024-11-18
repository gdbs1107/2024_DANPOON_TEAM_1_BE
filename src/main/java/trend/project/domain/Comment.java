package trend.project.domain;

import jakarta.persistence.*;
import lombok.*;
import trend.project.domain.common.BaseEntity;
import trend.project.domain.enumClass.CommentType;

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
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
    
    @Column(nullable = false)
    private int likesCount = 0;
    
    @Column(nullable = false)
    private Boolean haveParentComment = false;
    
    private LocalDateTime deletedAt;
    
    @Column(nullable = false)
    private boolean deletedTrue = false;
    
    @Column(nullable = false)
    private int depth = 0;
    
    @Column(nullable = false)
    private int orderNumber;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CommentType type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
