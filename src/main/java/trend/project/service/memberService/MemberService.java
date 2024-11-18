package trend.project.service.memberService;

import trend.project.web.dto.memberDTO.MemberJoinDTO;

public interface MemberService {
    MemberJoinDTO.MemberJoinResponseDTO joinMember(MemberJoinDTO.MemberJoinRequestDTO request);
}
