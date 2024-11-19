package trend.project.repository.planRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trend.project.domain.PlanBannerImage;
import trend.project.domain.PlanPosterImage;

import java.util.Optional;

public interface BannerImageRepository extends JpaRepository<PlanBannerImage, Long> {
    
    @Query("SELECT bi FROM PlanBannerImage bi WHERE bi.plan.id = :planId")
    Optional<PlanBannerImage> findImagesByPlanId(@Param("planId") Long planId);
    
}
