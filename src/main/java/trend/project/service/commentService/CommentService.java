package trend.project.service.commentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.web.dto.commentDTO.CommentDTO;

import java.util.List;

@Service
@Transactional
public interface CommentService {
    
    CommentDTO.CommentCreateResponseDTO createComment(CommentDTO.CommentCreateRequestDTO requestDTO, Long planId, String username);
    
    List<CommentDTO.CommentResponseDTO> getComments(Long planId);
    
    void updateComment(Long commentId, CommentDTO.CommentUpdateRequestDTO requestDTO, String username); // 수정
    
    void deleteComment(Long commentId, String username); // 삭제
    
}