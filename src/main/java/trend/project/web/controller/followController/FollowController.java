package trend.project.web.controller.followController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class FollowController {



    // 팔로우 기능
    @Operation(summary = "팔로우 토글", description = "토글 형식으로 팔로우를 구현하였습니다")
    @PostMapping("/follow")
    public void follow(@AuthenticationPrincipal UserDetails userDetails,
                       @RequestParam Long targetId) {

    }


    // 팔로워 조회
    @Operation(summary = "팔로우 조회")
    @GetMapping("/follow")
    public void getFollow(@AuthenticationPrincipal UserDetails userDetails) {

    }


    // 팔로우 조회
    @Operation(summary = "팔로워 조회")
    @GetMapping("/follower")
    public void getFollower(@AuthenticationPrincipal UserDetails userDetails) {

    }
}
