package trend.project.web.dto.memberDTO;

import lombok.Getter;

public class MemberProfileFixDTO {

    @Getter
    public static class MemberProfileRequestDTO{

        String province;

        String city;

    }
}
