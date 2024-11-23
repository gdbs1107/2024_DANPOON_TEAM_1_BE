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
    
    @Column(nullable = false, columnDefinition = "text")
    private String imageLink;
    
    @Column(nullable = false, columnDefinition = "text")
    private String fileName;
    
    @Column(nullable = false, length = 50)
    private String imageName;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Plan plan;
    
    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}
