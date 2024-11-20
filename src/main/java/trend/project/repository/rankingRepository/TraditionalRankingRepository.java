package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.TranditionRanking;

public interface TraditionalRankingRepository extends JpaRepository<TranditionRanking,Long> {
}
