package trend.project.web.dto.memberDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import trend.project.domain.Location;
import trend.project.domain.Plan;

import java.time.LocalDate;
import java.util.List;

public class MemberGetProfileDTO {



    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class MemberGetProfileResponseDTO{

        String name;

        @Schema(description = "게시물 갯수 입니다")
        Integer planCount;

        Integer followerCount;

        Integer followingCount;

        @Schema(description = "최신순 게시글 입니다")
        List<MemberGetProfilePlanResponseDTO> planListByUpdateDate;

        @Schema(description = "좋아요 갯수순 게시글 입니다")
        List<MemberGetProfilePlanResponseDTO> planListByLikeCount;


    }




    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class MemberGetProfilePlanResponseDTO{

        String title;

        Location location;

        LocalDate startDate;

        LocalDate endDate;

        Integer likeCount;

        Integer commentCount;


    }
}
