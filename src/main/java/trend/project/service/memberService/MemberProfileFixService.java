package trend.project.service.memberService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.memberDTO.MemberProfileFixDTO;

@Service
public interface MemberProfileFixService {
    String fixMemberUsername(String memberUsername, String newUsername);

    void fixPassword(String username, String newPassword);

    String fixName(String username, String newName);

    String fixPhoneNumber(String username, String newNumber);

    String fixEmail(String username, String newEmail);

    void fixAddress(String username, MemberProfileFixDTO.MemberProfileRequestDTO request);
}
