package davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.BusinessTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessTripRepo extends JpaRepository<BusinessTrip, Long> {
    Optional<BusinessTrip> findByDestination(String destination);
}
