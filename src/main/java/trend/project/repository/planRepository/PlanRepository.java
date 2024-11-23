package trend.project.repository.planRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    List<Plan> findTop4ByTitleContainingIgnoreCaseOrderByLikesCountDesc(String title);
    
    @Query("SELECT p FROM Plan p " +
            "JOIN p.location l " +
            "WHERE l.province = :province " +
            "AND p.id <> :planId " +
            "ORDER BY p.likesCount DESC")
    List<Plan> findTop5ByProvinceAndNotCurrentPlanOrderByLikesCountDesc(
            @Param("province") String province,
            @Param("planId") Long planId);

    List<Plan> findTop4ByCategoryOrderByLikesCountDesc(Category category);

    List<Plan> findTop5ByCategoryAndStartDateAfterOrderByLikesCountDesc(Category category, LocalDate startDate);

    List<Plan> findByCategory(Category category);

    // N+1 발생
    List<Plan> findByCategoryOrderByLikesCountDesc(Category category);

    List<Plan> findByTitleContainingIgnoreCaseAndLocationProvince(String title,String province);

    List<Plan> findByTitleContainingIgnoreCaseAndCategory(String title,Category category);

    List<Plan> findByTitleContainingIgnoreCaseAndBudget(String title,int budget);

    List<Plan> findByTitleContainingIgnoreCaseAndBudgetGreaterThan(String title, int budget);


    @Query("SELECT p FROM Plan p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')) AND " +
            "p.startDate <= :endDate AND p.endDate >= :startDate")
    List<Plan> findPlansByTitleAndPeriod(
            @Param("title") String title,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    List<Plan> findAllByTitleContainingIgnoreCase(String title);

    List<Plan> findByCategoryAndLocationProvince(Category category,String province);
}
