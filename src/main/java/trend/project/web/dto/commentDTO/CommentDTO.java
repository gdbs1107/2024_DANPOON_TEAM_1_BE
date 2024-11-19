package trend.project.web.dto.commentDTO;

import lombok.*;

import java.time.LocalDateTime;

public class CommentDTO {
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CommentResponseDTO {
        
        private Long id;
        private String body;
        private int likesCount;
        private boolean haveParentComment;
        private Long parentCommentId;
        private int depth;
        private int orderNumber;
        private String memberName;
        private String companyName;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;
        private boolean deletedTrue;
    }
}
