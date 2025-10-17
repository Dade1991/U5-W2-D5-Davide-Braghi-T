package davidebraghi.U5_W2_D5_Davide_Braghi_T.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;
    private String nickname;
    private String surname;
    private String name;
    private String email;

    private String profilePicPath;
}
