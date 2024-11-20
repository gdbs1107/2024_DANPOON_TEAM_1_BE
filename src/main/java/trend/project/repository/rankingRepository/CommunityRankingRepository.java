package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.CommunityRanking;

public interface CommunityRankingRepository extends JpaRepository<CommunityRanking, Long> {
}
