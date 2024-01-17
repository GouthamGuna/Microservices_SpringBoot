package in.dev.gmsk.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "dummy_student_tbl")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long primaryKey;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String admissionNo;
    private String address;
    private String mobileNo;
    private String EmailId;
    private String fatherName;
    private String motherName;
}
