package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.ArtRanking;

public interface ArtRankingRepository extends JpaRepository<ArtRanking, Long> {
}
