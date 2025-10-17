package davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.BusinessTrip;
import davidebraghi.U5_W2_D5_Davide_Braghi_T.entities.Employee;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NewReservationDTO(
        @NotEmpty(message = "Mandatory field: insert date")
        LocalDate reservationDate,
        String notes,
        Employee employee,
        BusinessTrip businessTrip
) {
}