package trend.project.service.commentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.web.dto.commentDTO.CommentDTO;

import java.util.List;

@Service
@Transactional
public interface CommentService {
    
    List<CommentDTO.CommentResponseDTO> getComments(Long planId);
    
}