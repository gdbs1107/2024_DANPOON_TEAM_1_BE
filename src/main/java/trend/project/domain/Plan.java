package trend.project.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.PlanCategoryHandler;
import trend.project.domain.common.BaseEntity;
import trend.project.domain.enumClass.Category;
import trend.project.domain.enumClass.PlanStatus;
import trend.project.web.dto.planDTO.PlanDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Plan extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, length = 50)
    private String title;
    
    private LocalDate startDate;
    
    private LocalDate endDate;
    
    @Column(nullable = false, length = 50)
    private String target;
    
    @Column(nullable = false)
    @ColumnDefault("0")
    private int cost;
    
    private String bookingMethod;
    
    @Column(nullable = false, columnDefinition = "text")
    private String content;
    
    @Column(nullable = false, columnDefinition = "int default 0")
    private int budget;
    
    @Column(nullable = false, columnDefinition = "int default 0")
    private int likesCount;
    
    @Column(nullable = false)
    private int commentCount;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int bookmarkCount = 0;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlanStatus status;
    
    @Column(nullable = false)
    @ColumnDefault("0")
    private boolean contacted;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(mappedBy = "plan",cascade = CascadeType.ALL)
    private PlanPosterImage planPosterImage;

    @OneToMany(mappedBy = "plan",cascade = CascadeType.ALL)
    private List<PlanLikes> planLikes=new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Location location;
    
    public void setLocation(Location location) {
        this.location = location;
        location.setPlan(this);
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    
    public void update(PlanDTO.PlanUpdateRequestDTO dto) {
        Category category = switch (dto.getCategory()) {
            case 1 -> Category.MUSIC_PERFORMANCE;
            case 2 -> Category.ART_CRAFT;
            case 3 -> Category.LOCAL_CULTURE;
            case 4 -> Category.FOOD_MARKET;
            case 5 -> Category.TRADITION_HISTORY;
            case 6 -> Category.NATURE_AGRICULTURE;
            case 7 -> Category.SPORTS;
            case 8 -> Category.SEASONAL_EVENT;
            case 9 -> Category.COMMUNITY_FAMILY;
            default -> throw new PlanCategoryHandler(ErrorStatus.CATEGORY_INVALID);
        };
        this.title = dto.getTitle();
        this.category = category;
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.target = dto.getTarget();
        this.cost = dto.getCost();
        this.bookingMethod = dto.getBookingMethod();
        this.content = dto.getContent();
        this.budget = dto.getBudget();
    }
}
