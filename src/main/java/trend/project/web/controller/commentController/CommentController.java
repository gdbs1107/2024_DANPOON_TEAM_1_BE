package trend.project.web.controller.commentController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import trend.project.api.ApiResponse;
import trend.project.service.commentService.CommentService;
import trend.project.web.dto.commentDTO.CommentDTO;

import java.util.List;

@RestController
@RequestMapping("/plans/{planId}/comments")
@RequiredArgsConstructor
@Tag(name = "댓글 API")
public class CommentController {
    
    private final CommentService commentService;
    
    @Operation(summary = "댓글 작성 API", description = "해당 API는 새로운 댓글을 작성합니다.")
    @PostMapping("/")
    public ApiResponse<CommentDTO.CommentCreateResponseDTO> createComment(
            @PathVariable Long planId,
            @RequestBody CommentDTO.CommentCreateRequestDTO requestDTO,
            @AuthenticationPrincipal UserDetails user) {
        CommentDTO.CommentCreateResponseDTO responseDTO = commentService.createComment(requestDTO, planId, user.getUsername());
        return ApiResponse.onSuccess(responseDTO);
    }
    
    @Operation(summary = "댓글 수정 API", description = "해당 API는 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    public ApiResponse<String> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentDTO.CommentUpdateRequestDTO requestDTO,
            @AuthenticationPrincipal UserDetails user) {
        commentService.updateComment(commentId, requestDTO, user.getUsername());
        return ApiResponse.onSuccess("수정 성공");
    }
    
    @Operation(summary = "기획서 댓글 조회 API", description = "해당 API는 기획서에 달린 댓글들의 정보를 조회합니다.")
    @GetMapping("/")
    public ApiResponse<List<CommentDTO.CommentResponseDTO>> getComments(@PathVariable Long planId) {
        List<CommentDTO.CommentResponseDTO> comments = commentService.getComments(planId);
        return ApiResponse.onSuccess(comments);
    }
    
    @Operation(summary = "댓글 삭제 API", description = "해당 API는 댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    public ApiResponse<String> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetails user) {
        commentService.deleteComment(commentId, user.getUsername());
        return ApiResponse.onSuccess("삭제 성공");
    }
}
