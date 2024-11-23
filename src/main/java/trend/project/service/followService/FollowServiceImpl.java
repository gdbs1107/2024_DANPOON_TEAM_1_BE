package trend.project.service.followService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trend.project.api.code.status.ErrorStatus;
import trend.project.api.exception.handler.MemberCategoryHandler;
import trend.project.domain.Member;
import trend.project.domain.MemberFollow;
import trend.project.domain.MemberFollower;
import trend.project.repository.FollowRepository;
import trend.project.repository.FollowerRepository;
import trend.project.repository.MemberRepository;
import trend.project.web.dto.FollowDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {


    private final FollowRepository followRepository;
    private final FollowerRepository followerRepository;
    private final MemberRepository memberRepository;


    @Override
    public void toggleFollowMember(String username, Long targetId) {

        // 팔로우 신청하는 사람 -> follower
        Member follower = getMemberByUsername(username);

        // 팔로우 받는 사람 -> target
        Member target = memberRepository.findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("타겟 멤버를 찾을 수 없습니다"));

        // 이미 팔로우하고 있는지 확인
        boolean isFollowing = followRepository.existsByMemberAndFollowUser(follower, target);

        if (isFollowing) {
            // 팔로우 중이라면 언팔로우
            followRepository.deleteByMemberAndFollowUser(follower, target);
            follower.minusFollowCount();

            followerRepository.deleteByMemberAndFollowerUser(target, follower);
            target.minusFollowerCount();
        } else {
            // 팔로우 중이 아니라면 팔로우
            MemberFollow follow = MemberFollow.builder()
                    .member(follower)
                    .followUser(target)
                    .build();
            follower.addFollowCount();
            followRepository.save(follow);

            MemberFollower followerEntity = MemberFollower.builder()
                    .member(target)
                    .followerUser(follower)
                    .build();
            target.addFollowerCount();
            followerRepository.save(followerEntity);
        }
    }


    @Override
    public List<FollowDTO.FollowResponseDTO> getFollowedUsers(String username) {
        UserEntity user = findUser(username);

        // 해당 유저가 팔로우하는 유저 목록 조회
        List<Follow> follows = followRepository.findByUserEntity(user);

        // Follow 객체에서 UserEntity를 추출하고, 이를 FollowResponseDTO로 변환
        return follows.stream()
                .map(follow -> FollowConverter.toFollowResponseDTO(follow.getFollowUser()))
                .collect(Collectors.toList());
    }





    // 회원 찾는 메서드
    public Member getMemberByUsername(String username){
        return memberRepository.findByUsername(username)
                .orElseThrow(()-> new MemberCategoryHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
