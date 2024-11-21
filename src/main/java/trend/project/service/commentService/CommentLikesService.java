package trend.project.service.commentService;

import trend.project.web.dto.commentDTO.CommentDTO;

public interface CommentLikesService {
    
    CommentDTO.CommentLikesCountResponseDTO toggleCommentLike(Long commentId, String username);
    
}
