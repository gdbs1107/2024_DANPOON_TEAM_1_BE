
package trend.project.service.commentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.CommentCategoryHandler;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.domain.Comment;
import trend.project.domain.CommentLikes;
import trend.project.domain.Member;
import trend.project.repository.MemberRepository;
import trend.project.repository.commentRepository.CommentLikesRepository;
import trend.project.repository.commentRepository.CommentRepository;
import trend.project.web.dto.commentDTO.CommentDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentLikesServiceImpl implements CommentLikesService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final CommentLikesRepository commentLikesRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    
    @Override
    public CommentDTO.CommentLikesCountResponseDTO toggleCommentLike(Long commentId, String username) {
        
        Member findMember = getFindMember(username);
        Comment findComment = getFindComment(commentId);
        
        CommentLikes likes = commentLikesRepository.findByMemberAndComment(findMember, findComment).orElse(null);
        
        if (likes == null) {
            
            CommentLikes newCommentLikes = CommentLikes.builder()
                    .member(findMember)
                    .comment(findComment)
                    .build();
            
            commentLikesRepository.save(newCommentLikes);
            // 좋아요 수 즉시 반영을 위해 명시적으로 flush 호출
            entityManager.flush();
            
            findComment.updateLikesCount();
        } else {
            
            commentLikesRepository.delete(likes);
            // 좋아요 수 즉시 반영을 위해 명시적으로 flush 호출
            entityManager.flush();
            
            findComment.updateLikesCount();
            
        }
        return CommentDTO.CommentLikesCountResponseDTO.builder()
                .likesCount(findComment.getLikesCount())
                .build();
    }
    
    private Comment getFindComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(
                () -> new CommentCategoryHandler(ErrorStatus.COMMENT_NOT_FOUND)
        );
    }
    
    private Member getFindMember(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );
    }
}