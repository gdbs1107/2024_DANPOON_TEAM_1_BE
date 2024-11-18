package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
