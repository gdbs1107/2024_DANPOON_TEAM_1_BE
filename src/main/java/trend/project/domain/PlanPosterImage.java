package trend.project.domain;

import jakarta.persistence.*;
import lombok.*;
import trend.project.domain.common.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class PlanPosterImage extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String imageLink;
    
    @Column(nullable = false, length = 50)
    private String imageName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
    
}
