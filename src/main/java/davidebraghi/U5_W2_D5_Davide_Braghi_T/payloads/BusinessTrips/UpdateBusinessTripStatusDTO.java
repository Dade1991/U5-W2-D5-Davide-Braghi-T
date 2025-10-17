package davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads.BusinessTrips;

import davidebraghi.U5_W2_D5_Davide_Braghi_T.enums.BusinessTripStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateBusinessTripStatusDTO(
        @NotNull
        BusinessTripStatus status
) {
}
