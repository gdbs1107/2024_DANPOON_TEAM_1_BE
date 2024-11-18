package trend.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trend.project.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
