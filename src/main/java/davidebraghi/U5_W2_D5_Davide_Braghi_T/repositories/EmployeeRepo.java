package davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
}
