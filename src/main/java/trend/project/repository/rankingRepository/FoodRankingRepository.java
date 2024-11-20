package trend.project.repository.rankingRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.rankingClass.FoodRanking;

public interface FoodRankingRepository extends JpaRepository<FoodRanking, Long> {
}
