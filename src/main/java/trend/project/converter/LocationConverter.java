package trend.project.converter;

import trend.project.domain.Location;
import trend.project.web.dto.planDTO.PlanDTO;

public class LocationConverter {
    
    public static Location toPlanLocation(PlanDTO.PlanCreateRequestDTO planCreateRequestDTO) {
        return Location.builder()
                .province(planCreateRequestDTO.getProvince())
                .city(planCreateRequestDTO.getCity())
                .town(planCreateRequestDTO.getTown())
                .build();
    }
}