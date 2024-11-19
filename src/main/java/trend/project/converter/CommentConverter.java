package trend.project.converter;

import trend.project.domain.Comment;
import trend.project.web.dto.commentDTO.CommentDTO;

public class CommentConverter {
    public static CommentDTO.CommentResponseDTO toCommentResponseDTO(Comment comment) {
        return CommentDTO.CommentResponseDTO.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .likesCount(comment.getLikesCount())
                .haveParentComment(comment.getHaveParentComment())
                .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
                .depth(comment.getDepth())
                .orderNumber(comment.getOrderNumber())
                .memberName(comment.getMember() != null ? comment.getMember().getUsername() : null)
                .companyName(comment.getCompany() != null ? comment.getCompany().getUsername() : null)
                .updatedAt(comment.getUpdatedAt())
                .deletedAt(comment.getDeletedAt())
                .deletedTrue(comment.isDeletedTrue())
                .build();
    }
}
