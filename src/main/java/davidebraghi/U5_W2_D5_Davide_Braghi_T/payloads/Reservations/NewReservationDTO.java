package davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads.Reservations;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.BusinessTrip;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.Employee;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewReservationDTO(
        @NotNull(message = "Mandatory field: insert date")
        LocalDate reservationDate,
        String notes,
        @NotNull
        Employee employee,
        @NotNull
        BusinessTrip businessTrip
) {
}