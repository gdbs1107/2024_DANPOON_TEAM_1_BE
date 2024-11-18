package trend.project.domain.enumClass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PlanStatus {
    ON_HOLD("대기 중"),
    IN_PROGRESS("진행 중"),
    COMPLETED("완료"),
    CANCELLED("취소됨");
    
    private final String description;
}
