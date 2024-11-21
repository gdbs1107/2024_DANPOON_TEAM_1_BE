package trend.project.web.controller.commentController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trend.project.api.ApiResponse;
import trend.project.service.commentService.CommentLikesService;
import trend.project.web.dto.commentDTO.CommentDTO;

@RestController
@RequestMapping("/plans/{planId}/comments/likes")
@RequiredArgsConstructor
@Tag(name = "댓글 좋아요 API")
public class CommentLikesController {
    
    private final CommentLikesService commentLikesService;
    
    @Operation(summary = "댓글 좋아요 API", description = "해당 API는 댓글을 토글 형식으로 좋아요를 합니다.")
    @PostMapping("/{commentId}")
    public ApiResponse<CommentDTO.CommentLikesCountResponseDTO> toggleLike(@PathVariable Long commentId,
                                                                           @AuthenticationPrincipal UserDetails user) {
        CommentDTO.CommentLikesCountResponseDTO res = commentLikesService.toggleCommentLike(commentId, user.getUsername());
        return ApiResponse.onSuccess(res);
    }
    
}
