package trend.project.domain.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommentType {
    GENERNAL("일반 댓글"),
    QURESTION("질문 댓글");
    
    private final String description;
}
