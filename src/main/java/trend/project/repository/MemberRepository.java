package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trend.project.domain.Member;
import trend.project.domain.enumClass.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT m FROM Member m WHERE m.status = :status AND m.inactiveDate <= :cutoffDate")
    List<Member> findInactiveMembersForDeletion(@Param("status") Status status, @Param("cutoffDate") LocalDateTime cutoffDate);

    Member findByPhoneNumber(String phoneNumber);

    Member findByEmail(String email);

    Member findTopByOrderByFollowerCountDesc();

    List<Member> findAllByNameContainingIgnoreCase(String name);
}
