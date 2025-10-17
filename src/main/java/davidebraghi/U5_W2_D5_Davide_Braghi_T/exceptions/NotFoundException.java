package davidebraghi.U5_W2_D5_Davide_Braghi_T.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("ID: " + id + " was not found. Try again.");
    }
}
