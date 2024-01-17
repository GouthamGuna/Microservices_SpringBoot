package in.dev.gmsk.repository;

import in.dev.gmsk.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentTest {
    @Autowired
    private StudentRepo StudentRepo;

    @Test
    void testStudent() {

        Student student = Student.builder().build();
        student.setFirstName( "First Name" );
        student.setLastName( "Last Name" );
        student.setDob( "DOB" );
        student.setGender( "Male" );
        student.setAdmissionNo( "12345" );
        student.setEmailId( "name@gmsk.in" );
        student.setAddress( "SLM" );
        student.setMobileNo( "9876543210" );
        student.setFatherName( "Father Name" );
        student.setMotherName( "Mother Name" );

        Student save = StudentRepo.save( student );
        System.out.println( "save = " + save );

        assertTrue( true );
    }
}