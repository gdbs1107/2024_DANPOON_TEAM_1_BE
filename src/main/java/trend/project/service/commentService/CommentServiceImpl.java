package trend.project.service.commentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.CompanyCategoryHandler;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.api.exception.handler.PlanCategoryHandler;
import trend.project.converter.CommentConverter;
import trend.project.domain.Comment;
import trend.project.domain.Company;
import trend.project.domain.Member;
import trend.project.domain.Plan;
import trend.project.repository.CommentRepository;
import trend.project.repository.CompanyRepository;
import trend.project.repository.MemberRepository;
import trend.project.repository.planRepository.PlanRepository;
import trend.project.web.dto.commentDTO.CommentDTO;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    
    private final CommentRepository commentRepository;
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    
    @Override
    public CommentDTO.CommentCreateResponseDTO createComment(CommentDTO.CommentCreateRequestDTO requestDTO, Long planId, String username) {
        
        Plan findPlan = findPlanById(planId);
        
        boolean isCompany = isValidEmail(username);
        Object entity = isCompany
                ? companyRepository.findByUsername(username).orElseThrow(() -> new CompanyCategoryHandler(ErrorStatus.COMPANY_NOT_FOUND))
                : memberRepository.findByUsername(username).orElseThrow(() -> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));
        
        Long order = requestDTO.getHierarchy() == 1
                ? commentRepository.findMaxCommentOrder(findPlan.getId(), requestDTO.getGroup()) + 1
                : 0;
        
        Long group = requestDTO.getHierarchy() == 1
                ? requestDTO.getGroup()
                : commentRepository.findMaxCommentGroup(findPlan.getId()) + 1;
        
        Comment newComment = Comment.builder()
                .body(requestDTO.getBody())
                .plan(findPlan)
                .member(!isCompany ? (Member) entity : null)
                .company(isCompany ? (Company) entity : null)
                .groups(group)
                .orders(order)
                .hierarchy(requestDTO.getHierarchy())
                .build();
        
        return getBuild(newComment);
    }
    
    private CommentDTO.CommentCreateResponseDTO getBuild(Comment newComment) {
        return CommentDTO.CommentCreateResponseDTO.builder()
                .commentId(commentRepository.save(newComment).getId())
                .build();
    }
    
    @Override
    public List<CommentDTO.CommentResponseDTO> getComments(Long planId) {
        List<Comment> comments = commentRepository.findByPlanId(planId).orElse(null);
        return comments.stream()
                .map(CommentConverter::toCommentResponseDTO)
                .toList();
    }
    
    private Plan findPlanById(Long planId) {
        return planRepository.findById(planId).orElseThrow(
                () -> new PlanCategoryHandler(ErrorStatus.PLAN_NOT_FOUND)
        );
    }
    
    // 이메일 유효성 검사 메서드
    public static boolean isValidEmail(String email) {
        // 이메일 정규식
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        
        // 문자열이 null이 아니고 정규식과 일치하는지 확인
        return email != null && email.matches(emailRegex);
    }
    
}