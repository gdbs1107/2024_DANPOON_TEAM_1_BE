package trend.project.web.controller.followController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import trend.project.api.ApiResponse;
import trend.project.service.followService.FollowService;
import trend.project.web.dto.FollowDTO;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // 팔로우 기능
    @Operation(summary = "팔로우 토글 API", description = "토글 형식으로 팔로우를 구현하였습니다")
    @PostMapping("/follow")
    public ApiResponse<String> follow(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam Long targetId) {

        followService.toggleFollowMember(userDetails.getUsername(), targetId);

        return ApiResponse.onSuccess("팔로우가 성공적으로 수정되었습니다");
    }


    // 팔로워 조회
    @Operation(summary = "팔로우 조회 API", description = "본인의 팔로우를 조회하는 API 입니다")
    @GetMapping("/follow")
    public ApiResponse<List<FollowDTO.FollowResponseDTO>> getFollow(@AuthenticationPrincipal UserDetails userDetails) {

        List<FollowDTO.FollowResponseDTO> followedUsers = followService.getFollowedUsers(userDetails.getUsername());

        return ApiResponse.onSuccess(followedUsers);

    }


    // 팔로우 조회
    @Operation(summary = "팔로워 조회 API", description = "본인의 팔로워를 조회하는 API 입니다")
    @GetMapping("/follower")
    public ApiResponse<List<FollowDTO.FollowResponseDTO>> getFollower(@AuthenticationPrincipal UserDetails userDetails) {

        List<FollowDTO.FollowResponseDTO> followers = followService.getFollowers(userDetails.getUsername());

        return ApiResponse.onSuccess(followers);
    }
}
