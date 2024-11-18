package trend.project.web.dto.memberDTO;

import lombok.*;

public class MemberProfileUpdateDTO {


    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class MemberProfileUpdateRequestDTO{

        /*
        아직 어떤 정보가 업데이트 될 지 기획이 되지 않아서 일단 비워두겠습니다
        */

        String name;
    }
}
