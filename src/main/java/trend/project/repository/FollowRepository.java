package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Member;
import trend.project.domain.MemberFollow;
import trend.project.domain.MemberFollower;

import java.util.List;

public interface FollowRepository extends JpaRepository<MemberFollow,Long> {

    void deleteByMemberAndFollowUser(Member member, Member followUser);

    boolean existsByMemberAndFollowUser(Member member, Member followUser);

    List<MemberFollow> findByMember(Member member);
}
