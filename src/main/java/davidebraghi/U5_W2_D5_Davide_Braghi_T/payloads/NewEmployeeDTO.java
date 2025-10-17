package davidebraghi.U5_W2_D5_Davide_Braghi_T.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record NewEmployeeDTO(
        @NotEmpty(message = "Mandatory field: insert username")
        String username,
        @NotEmpty(message = "Mandatory field: insert surname")
        String surname,
        @NotEmpty(message = "Mandatory field: insert name")
        String name,
        @NotEmpty(message = "Mandatory field: insert email")
        @Email
        String email
) {
}
