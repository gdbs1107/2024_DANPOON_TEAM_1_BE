package trend.project.web.dto.commentDTO;

import lombok.*;
import trend.project.domain.Member;

import java.time.LocalDateTime;

public class CommentDTO {
    
    @Getter
    public static class CommentCreateRequestDTO {
        
        private String body;
        private Long hierarchy;
        private Long group;
    }
    
    @Getter
    public static class CommentUpdateRequestDTO {
        
        private String body;
    }
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CommentCreateResponseDTO {
        
        private Long commentId;
    }
    
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CommentResponseDTO {
        
        private Long id;
        private String body;
        private int likesCount;
        private Long hierarchy;
        private Long orders;
        private Long groups;
        private String memberName;
        private String companyName;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;
        private boolean deletedTrue;
    }
}
