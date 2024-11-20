package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.NatureRanking;

public interface NatureRankingRepository extends JpaRepository<NatureRanking, Long> {
}
