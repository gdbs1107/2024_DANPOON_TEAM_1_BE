package trend.project.repository.planRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trend.project.domain.PlanPosterImage;

import java.util.Optional;

public interface PosterImageRepository extends JpaRepository<PlanPosterImage, Long> {
    
    @Query("SELECT pi FROM PlanPosterImage pi WHERE pi.plan.id = :planId")
    Optional<PlanPosterImage> findImagesByPlanId(@Param("planId") Long planId);
    
}
