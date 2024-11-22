package trend.project.service.planService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.api.exception.handler.PlanCategoryHandler;
import trend.project.domain.CommentLikes;
import trend.project.domain.Member;
import trend.project.domain.Plan;
import trend.project.domain.PlanLikes;
import trend.project.repository.MemberRepository;
import trend.project.repository.planRepository.PlanLikesRepository;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.planDTO.PlanDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class PlanLikesServiceImpl implements PlanLikesService {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final PlanLikesRepository planLikesRepository;
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;
    
    @Override
    public PlanDTO.PlanLikesCountResponseDTO togglePlanLike(Long planId, String username) {
        
        Member findMember = getFindMember(username);
        Plan findPlan = getFindPlan(planId);
        
        PlanLikes likes = planLikesRepository.findByMemberAndPlan(findMember, findPlan).orElse(null);
        
        Boolean checkLike = false;
        if (likes == null) {
            
            PlanLikes newPlanLikes = PlanLikes.builder()
                    .member(findMember)
                    .plan(findPlan)
                    .build();
            
            planLikesRepository.save(newPlanLikes);
            // 좋아요 수 즉시 반영을 위해 명시적으로 flush 호출
            entityManager.flush();
            
            findPlan.updateLikesCount();
            checkLike = true;
        } else {
            
            planLikesRepository.delete(likes);
            // 좋아요 수 즉시 반영을 위해 명시적으로 flush 호출
            entityManager.flush();
            
            findPlan.updateLikesCount();
            checkLike = false;
        }
        return PlanDTO.PlanLikesCountResponseDTO.builder()
                .likesCount(findPlan.getLikesCount())
                .checkLike(checkLike)
                .build();
    }
    
    private Plan getFindPlan(Long planId) {
        return planRepository.findById(planId).orElseThrow(
                () -> new PlanCategoryHandler(ErrorStatus.PLAN_NOT_FOUND)
        );
    }
    
    private Member getFindMember(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );
    }
}