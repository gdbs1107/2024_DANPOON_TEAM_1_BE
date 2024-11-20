package trend.project.service.planService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.api.exception.handler.PlanCategoryHandler;
import trend.project.converter.LocationConverter;
import trend.project.converter.PlanConverter;
import trend.project.domain.*;
import trend.project.repository.MemberRepository;
import trend.project.repository.planRepository.BannerImageRepository;
import trend.project.repository.planRepository.LocationRepository;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.repository.planRepository.PosterImageRepository;
import trend.project.web.dto.planDTO.PlanDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanServiceImpl implements PlanService {
    
    private final PlanRepository planRepository;
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;
    
    @Override
    public PlanDTO.PlanCreateResponseDTO planCreate(PlanDTO.PlanCreateRequestDTO req, String username) {
        
        Member findMember = getMemberByUsername(username);
        
        Plan newPlan = PlanConverter.toCreatePlan(req);
        newPlan.setMember(findMember);
        Plan savedPlan = planRepository.save(newPlan);
        
        Location newLocation = LocationConverter.toPlanLocation(req);
        newPlan.setLocation(newLocation);
        locationRepository.save(newLocation);
        
        return PlanDTO.PlanCreateResponseDTO.builder().planId(savedPlan.getId()).build();
    }
    
    @Override
    public PlanDTO.PlanUpdateResponseDTO planUpdate(PlanDTO.PlanUpdateRequestDTO req, Long planId, String username) {
        
        Plan findPlan = findPlanById(planId);
        Location findLocation = getLocation(planId);
        
        if (!findPlan.getMember().getUsername().equals(username)) {
            throw new PlanCategoryHandler(ErrorStatus.UNAUTHORIZED_ACTION);
        }
        
        findPlan.update(req);
        findLocation.update(req.getProvince(), req.getCity(), req.getTown());
        
        Plan savedPlan = planRepository.save(findPlan);
        locationRepository.save(findLocation);
        
        return PlanDTO.PlanUpdateResponseDTO.builder().planId(savedPlan.getId()).build();
    }
    
    @Override
    public void deletePlan(Long planId, String username) {
        
        Plan findPlan = findPlanById(planId);
        Location findLocation = getLocation(planId);
        
        if (!findPlan.getMember().getUsername().equals(username)) {
            throw new PlanCategoryHandler(ErrorStatus.UNAUTHORIZED_ACTION);
        }
        
        locationRepository.delete(findLocation);
        planRepository.delete(findPlan);
    }
    
    private Location getLocation(Long planId) {
        return locationRepository.findByPlanId(planId).orElseThrow(
                () -> new PlanCategoryHandler(ErrorStatus.PLAN_LOCATION_NOT_FOUND)
        );
    }
    
    private Plan findPlanById(Long planId) {
        return planRepository.findById(planId).orElseThrow(
                () -> new PlanCategoryHandler(ErrorStatus.PLAN_NOT_FOUND)
        );
    }
    
    private Member getMemberByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(()-> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
