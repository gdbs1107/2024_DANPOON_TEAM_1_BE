package trend.project.service.memberService;

import org.springframework.scheduling.annotation.Scheduled;
import trend.project.web.dto.memberDTO.MemberGetProfileDTO;
import trend.project.web.dto.memberDTO.MemberJoinDTO;
import trend.project.web.dto.memberDTO.MemberProfileFindDTO;
import trend.project.web.dto.memberDTO.MemberSearchDTO;

import java.util.List;

public interface MemberService {
    MemberJoinDTO.MemberJoinResponseDTO joinMember(MemberJoinDTO.MemberJoinRequestDTO request);

    MemberGetProfileDTO.MemberGetProfileResponseByRecentDTO getMemberProfileSortUpdateDate(String username);

    MemberGetProfileDTO.MemberGetProfileResponseByLikeCountDTO getMemberProfileSortLikeCount(String username);

    void deleteMember(String username);

    Long deleteRollBackMember(String username);

    MemberProfileFindDTO.FindMemberUsernameResponseDTO getUsernamesWithPhone(MemberProfileFindDTO.FindMemberUsernameWithPhoneNumbersRequestDTO request);

    MemberProfileFindDTO.FindMemberUsernameResponseDTO getUsernamesWithEmail(MemberProfileFindDTO.FindMemberUsernameWithEmailsRequestDTO request);

    // 비밀번호 재설정 메서드
    MemberProfileFindDTO.FindMemberPasswordResponseDTO getPassword(MemberProfileFindDTO.FindMemberPasswordRequestDTO request);

    List<MemberSearchDTO.MemberSearchResponseDTO> searchMember(String name);

    // 매일 자정에 실행
    @Scheduled(cron = "0 0 0 * * ?")
    void deleteOldInactiveMembers();
}
