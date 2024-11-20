package trend.project.converter;

import trend.project.domain.Comment;
import trend.project.web.dto.commentDTO.CommentDTO;

public class CommentConverter {
    public static CommentDTO.CommentResponseDTO toCommentResponseDTO(Comment comment) {
        return CommentDTO.CommentResponseDTO.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .likesCount(comment.getLikesCount())
                .memberName(comment.getMember() != null ? comment.getMember().getUsername() : null)
                .companyName(comment.getCompany() != null ? comment.getCompany().getUsername() : null)
                .groups(comment.getGroups())
                .orders(comment.getOrders())
                .hierarchy(comment.getHierarchy())
                .updatedAt(comment.getUpdatedAt())
                .deletedAt(comment.getDeletedAt())
                .deletedTrue(comment.isDeletedTrue())
                .build();
    }
}
