package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
