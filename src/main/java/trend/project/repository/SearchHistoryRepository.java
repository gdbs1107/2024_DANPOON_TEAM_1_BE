package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
}
