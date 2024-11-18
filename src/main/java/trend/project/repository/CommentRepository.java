package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    Optional<List<Comment>> findByPlanId(Long planId);
}