package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.RefreshEntity;

public interface RefreshRepository extends JpaRepository<RefreshEntity, Long> {
}
