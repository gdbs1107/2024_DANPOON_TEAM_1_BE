package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Member;
import trend.project.domain.MemberFollower;

public interface FollowerRepository extends JpaRepository<MemberFollower,Long> {

    void deleteByMemberAndFollowerUser(Member member, Member followerUser);

    boolean existsByMemberAndFollowerUser(Member member, Member followerUser);

}
