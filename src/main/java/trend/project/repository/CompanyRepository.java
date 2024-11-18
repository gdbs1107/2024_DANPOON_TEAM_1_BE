package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trend.project.domain.Company;
import trend.project.domain.enumClass.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByUsername(String username);

    Optional<Company> findByName(String name);

    Optional<Company> findByCompanyName(String companyName);

    Boolean existsByUsername(String username);

    @Query("SELECT c FROM Company c WHERE c.status = :status AND c.inactiveDate <= :cutoffDate")
    List<Company> findInactiveCompaniesForDeletion(@Param("status") Status status, @Param("cutoffDate") LocalDateTime cutoffDate);
}
