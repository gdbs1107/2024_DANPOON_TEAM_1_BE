package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.SportsRanking;

public interface SportsRankingRepository extends JpaRepository<SportsRanking, Long> {
}
