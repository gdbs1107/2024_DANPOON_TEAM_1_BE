package trend.project.web.dto.companyDTO;

import lombok.*;

public class CompanyProfileUpdateDTO {



    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Getter
    public static class CompanyProfileUpdateRequestDTO{

        private String companyName;


    }
}
