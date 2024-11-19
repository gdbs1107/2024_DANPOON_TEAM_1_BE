package trend.project.service.memberService;

import org.springframework.scheduling.annotation.Scheduled;
import trend.project.web.dto.memberDTO.MemberGetProfileDTO;
import trend.project.web.dto.memberDTO.MemberJoinDTO;
import trend.project.web.dto.memberDTO.MemberProfileFindDTO;

public interface MemberService {
    MemberJoinDTO.MemberJoinResponseDTO joinMember(MemberJoinDTO.MemberJoinRequestDTO request);

    MemberGetProfileDTO.MemberGetProfileResponseByRecentDTO getMemberProfileSortUpdateDate(Long userId);

    MemberGetProfileDTO.MemberGetProfileResponseByLikeCountDTO getMemberProfileSortLikeCount(Long userId);

    void deleteMember(String username);

    MemberProfileFindDTO.FindMemberUsernameResponseDTO getUsernamesWithPhone(MemberProfileFindDTO.FindMemberUsernameWithPhoneNumbersRequestDTO request);

    MemberProfileFindDTO.FindMemberUsernameResponseDTO getUsernamesWithEmail(MemberProfileFindDTO.FindMemberUsernameWithEmailsRequestDTO request);

    // 비밀번호 재설정 메서드
    MemberProfileFindDTO.FindMemberPasswordResponseDTO getPassword(MemberProfileFindDTO.FindMemberPasswordRequestDTO request);

    // 매일 자정에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    void deleteOldInactiveMembers();
}
