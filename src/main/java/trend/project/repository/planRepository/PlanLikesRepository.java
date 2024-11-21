package trend.project.repository.planRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.*;

import java.util.Optional;

public interface PlanLikesRepository extends JpaRepository<PlanLikes, Long> {
    
    Optional<PlanLikes> findByMemberAndPlan(Member member, Plan plan);
    
}