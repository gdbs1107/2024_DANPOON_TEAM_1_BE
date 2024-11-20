package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Plan;
import trend.project.domain.Ranking;
import trend.project.domain.enumClass.RankingCategory;

import java.time.LocalDate;
import java.util.List;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    void deleteAll();

    void deleteByRankingCategory(RankingCategory rankingCategory);

    List<Ranking> findByRankingCategory(RankingCategory rankingCategory);

}
