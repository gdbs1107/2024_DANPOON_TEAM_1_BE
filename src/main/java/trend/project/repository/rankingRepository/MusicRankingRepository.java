package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.MusicRanking;

public interface MusicRankingRepository extends JpaRepository<MusicRanking, Long> {
}
