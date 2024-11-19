package trend.project.repository.planRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import trend.project.domain.Member;
import trend.project.domain.Plan;
import trend.project.domain.enumClass.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    
    Optional<Plan> findById(Long id);

    @EntityGraph(attributePaths = {"member"})
    List<Plan> findTop4ByOrderByLikesCountDesc();

    @EntityGraph(attributePaths = {"member"})
    List<Plan> findTop5ByOrderByCommentCountDesc();

    List<Plan> findTop5ByStartDateAfterOrderByLikesCountDesc(LocalDate startDate);

    @EntityGraph(attributePaths = {"member"})
    Plan findTopByCategoryOrderByLikesCountDesc(Category category);

    List<Plan> findTop5ByMember(Member member);

}
