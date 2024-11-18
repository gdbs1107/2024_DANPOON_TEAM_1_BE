package trend.project.service.commentService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.converter.CommentConverter;
import trend.project.domain.Comment;
import trend.project.repository.CommentRepository;
import trend.project.web.dto.commentDTO.CommentDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    
    private final CommentRepository commentRepository;
    
    @Override
    public List<CommentDTO.CommentResponseDTO> getComments(Long planId) {
        List<Comment> comments = commentRepository.findByPlanId(planId).orElse(null);
        return comments.stream()
                .map(CommentConverter::toCommentResponseDTO)
                .toList();
    }
    
}