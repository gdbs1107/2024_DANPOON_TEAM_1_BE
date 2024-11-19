package trend.project.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Ranking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "게시글 제목 입니다")
    private String title;

    @Schema(description = "게시글 작성자 닉네임 입니다")
    private String nickName;

    @Schema(description = "게시글 좋아요 수 입니다")
    private Integer likesCount;

    @Schema(description = "게시글 댓글 수 입니다")
    private Integer commentsCount;

    
}