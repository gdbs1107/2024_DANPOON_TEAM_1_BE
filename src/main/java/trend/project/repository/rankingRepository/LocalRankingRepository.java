package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.LocalRanking;

public interface LocalRankingRepository extends JpaRepository<LocalRanking, Long> {
}
