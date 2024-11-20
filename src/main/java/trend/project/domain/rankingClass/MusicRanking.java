package trend.project.domain.rankingClass;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MusicRanking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "순위에 있는 플랜의 식별자 입니다")
    private Long planId;

    @Schema(description = "게시글 제목 입니다")
    private String title;

    @Schema(description = "게시글 작성자 닉네임 입니다")
    private String name;

    @Schema(description = "게시글 좋아요 수 입니다")
    private Integer likesCount;

    @Schema(description = "게시글 댓글 수 입니다")
    private Integer commentsCount;

    @Schema(description = "게시글 포스터 이미지 링크입니다")
    @Column(length = 1000)
    private String imageLink;


}