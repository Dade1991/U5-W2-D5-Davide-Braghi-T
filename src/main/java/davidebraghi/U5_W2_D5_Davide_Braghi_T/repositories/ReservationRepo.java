package davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByEmployeeId(Long employeeId);

    boolean existsByEmployeeIdAndDate(Long employeeId, LocalDate date);
}