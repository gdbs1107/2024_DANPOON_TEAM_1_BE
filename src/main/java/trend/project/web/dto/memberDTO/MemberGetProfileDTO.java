package trend.project.web.dto.memberDTO;

import lombok.*;

public class MemberGetProfileDTO {

    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class MemberGetProfileRequestDTO{

        String name;

        


    }
}
