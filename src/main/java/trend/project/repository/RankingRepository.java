package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Ranking;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
}
