package trend.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.MemberProfileImage;

public interface MemberProfileImageRepository extends JpaRepository<MemberProfileImage, Long> {
}
