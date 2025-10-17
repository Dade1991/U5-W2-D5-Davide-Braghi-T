package davidebraghi.U5_W2_D5_Davide_Braghi_T.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorList;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> errorList) {
        super("Errors into body");
        this.errorList = errorList;
    }
}
