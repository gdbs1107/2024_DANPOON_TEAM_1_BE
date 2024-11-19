package trend.project.domain;

import jakarta.persistence.*;
import lombok.*;
import trend.project.domain.common.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /* 시/도 */
    @Column(nullable = false)
    private String province;

    /* 시/군/구 */
    @Column(nullable = false)
    private String city;

    /* 읍/면/동 */
    @Column(nullable = false)
    private String town;
    
    @OneToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;
    
    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
    public void update(String province, String city, String town) {
        this.province = province;
        this.city = city;
        this.town = town;
    }
}
