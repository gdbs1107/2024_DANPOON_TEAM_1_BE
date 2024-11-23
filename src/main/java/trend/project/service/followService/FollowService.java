package trend.project.service.followService;

import org.springframework.stereotype.Service;
import trend.project.web.dto.FollowDTO;

import java.util.List;

@Service
public interface FollowService {
    void toggleFollowMember(String username, Long targetId);

    List<FollowDTO.FollowResponseDTO> getFollowedUsers(String username);

    List<FollowDTO.FollowResponseDTO> getFollowers(String username);
}
