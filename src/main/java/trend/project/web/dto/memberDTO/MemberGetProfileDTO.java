package trend.project.web.dto.memberDTO;

import lombok.*;
import trend.project.domain.Plan;

import java.util.List;

public class MemberGetProfileDTO {

    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class MemberGetProfileResponseDTO{

        String name;

        Integer planCount;

        Integer followerCount;

        Integer followingCount;

        List<Plan> planList;



    }


    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class MemberGetProfilePlanResponseDTO{

        String title;





    }
}
