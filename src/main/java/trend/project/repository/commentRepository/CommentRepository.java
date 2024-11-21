package trend.project.repository.commentRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trend.project.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @EntityGraph(attributePaths = {"plan","member","company"})
    @Query("select c from Comment c where c.plan.id = :planId order by c.groups, c.orders")
    Optional<List<Comment>> findByPlanId(Long planId);
    
    //가장 큰 order 가져오기
    @EntityGraph(attributePaths = {"plan","member","company"})
    @Query("select max(c.orders) from Comment c where c.plan.id = :id and c.groups = :groups")
    Long findMaxCommentOrder(@Param("id") Long postId, @Param("groups") Long groups);
    
    @EntityGraph(attributePaths = {"plan","member","company"})
    @Query("SELECT COALESCE(MAX(c.groups), 0) FROM Comment c WHERE c.plan.id = :planId")
    Long findMaxCommentGroup(@Param("planId") Long postId);
}