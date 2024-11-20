package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.SeasonalRanking;

public interface SeasonalRankingRepository extends JpaRepository<SeasonalRanking, Long> {
}
