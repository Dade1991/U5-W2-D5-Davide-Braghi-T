package davidebraghi.U5_W2_D5_Davide_Braghi_T.repositories;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    boolean existsByEmployee_EmployeeIdAndReservationDate(Long employeeId, LocalDate reservationDate);
}