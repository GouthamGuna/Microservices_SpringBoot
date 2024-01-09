package in.dev.gmsk.repository;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "dummy-student-tbl")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long primaryKey;
    @NotBlank(message = "vehicle reg.no shouldn't be empty")
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String admissionNo;
    private String mobileNo;
    private String EmailId;
    private String fatherName;
    private String motherName;
}
