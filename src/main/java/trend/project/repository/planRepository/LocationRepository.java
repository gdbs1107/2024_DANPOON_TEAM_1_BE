package trend.project.repository.planRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trend.project.domain.Location;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    
    @Query("SELECT l FROM Location l WHERE l.plan.id = :planId")
    Optional<Location> findByPlanId(Long planId);
    
}