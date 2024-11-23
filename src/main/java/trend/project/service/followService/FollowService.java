package trend.project.service.followService;

import org.springframework.stereotype.Service;

@Service
public interface FollowService {
    void toggleFollowMember(String username, Long targetId);
}
