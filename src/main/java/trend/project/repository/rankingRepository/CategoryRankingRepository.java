package trend.project.repository.rankingRepository;

import trend.project.domain.Ranking;

public interface CategoryRankingRepository {

    void deleteAll();

    Ranking save(Ranking ranking);
}
