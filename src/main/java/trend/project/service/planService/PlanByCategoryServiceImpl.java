package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.repository.planRepository.PlanRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanByCategoryServiceImpl implements PlanByCategoryService {

    private final PlanRepository planRepository;

}
