package trend.project.repository.commentRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Comment;
import trend.project.domain.CommentLikes;
import trend.project.domain.Member;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {
    
    Optional<CommentLikes> findByMemberAndComment(Member member, Comment comment);
    
}