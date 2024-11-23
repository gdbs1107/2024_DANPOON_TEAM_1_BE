package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Member;
import trend.project.domain.MemberFollow;
import trend.project.domain.MemberFollower;

import java.util.List;

public interface FollowerRepository extends JpaRepository<MemberFollower,Long> {

    void deleteByMemberAndFollowerUser(Member member, Member followerUser);

    boolean existsByMemberAndFollowerUser(Member member, Member followerUser);

    List<MemberFollower> findByMember(Member member);

}
