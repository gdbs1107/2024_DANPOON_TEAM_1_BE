package trend.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.CompanyProfileImage;

public interface CompanyProfileImageRepository extends JpaRepository<CompanyProfileImage, Long> {
}
