package trend.project.service.planService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.api.exception.handler.PlanCategoryHandler;
import trend.project.converter.PlanDetailConverter;
import trend.project.domain.*;
import trend.project.repository.MemberRepository;
import trend.project.repository.planRepository.*;
import trend.project.web.dto.planDTO.PlanDetailDTO;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanDetailServiceImpl implements PlanDetailService {
    
    private final PlanRepository planRepository;
    private final LocationRepository locationRepository;
    private final PosterImageRepository posterImageRepository;
    private final BannerImageRepository bannerImageRepository;
    private final MemberRepository memberRepository;
    private final PlanLikesRepository planLikesRepository;
    
    @Override
    public PlanDetailDTO.PlanDetailResponseDTO getPlanDetail(Long planId, String username) {
        
        boolean checkLike;
        boolean checkPlanner;
        Plan findPlan = findPlanById(planId);
        Member findMember = findPlan.getMember();
        Location planLocation = getLocation(planId);
        PlanPosterImage posterImage = getPosterImage(planId);
        PlanBannerImage bannerImage = getBannerImage(planId);
        findPlan.updateCommentCount();
        
        if (username != null) {
            Member loginMember = getLoginMember(username);
            
            checkLike = planLikesRepository.existsByMemberAndPlanId(loginMember, planId);
            checkPlanner = loginMember.getUsername().equals(findMember.getUsername());
        } else {
            checkLike = false;
            checkPlanner = false;
        }
        
        return PlanDetailConverter.toPlanDetailResponseDTO(findPlan, findMember, checkPlanner, planLocation,
                posterImage, bannerImage, checkLike);
    }
    
    private Member getLoginMember(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new MemberCategoryHandler(ErrorStatus.LOGIN_MEMBER_NOT_FOUND)
        );
    }
    
    @Override
    public List<PlanDetailDTO.SameProvinceOtherPlanResponseDTO> getSameProvinceOtherPlans(Long planId) {
        Plan findPlan = findPlanById(planId);
        List<Plan> sameProvincePlans;
        try {
            sameProvincePlans = planRepository.findTop5ByProvinceAndNotCurrentPlanOrderByLikesCountDesc(
                    findPlan.getLocation().getProvince(),
                    planId);
        } catch (Exception e) {
            sameProvincePlans = Collections.emptyList();
        }
        
        return PlanDetailConverter.toSameProvinceOtherPlanResponseDTOList(sameProvincePlans);
    }
    
    private PlanBannerImage getBannerImage(Long planId) {
        return bannerImageRepository.findImagesByPlanId(planId).orElse(null);
    }
    
    private PlanPosterImage getPosterImage(Long planId) {
        return posterImageRepository.findImagesByPlanId(planId).orElse(null);
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
