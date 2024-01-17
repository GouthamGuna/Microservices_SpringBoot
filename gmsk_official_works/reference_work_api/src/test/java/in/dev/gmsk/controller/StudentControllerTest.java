package in.dev.gmsk.controller;

import in.dev.gmsk.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @Test
    void testSaveStudent() {

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

        ResponseEntity<Student> studentResponseEntity = studentController.saveStudent( student );

        System.out.println( "studentResponseEntity = " + studentResponseEntity );

        assertTrue( true );
    }

    @Test
    void allStudentList() {

        ResponseEntity<List<Student>> listResponseEntity = studentController.allStudentList();
        System.out.println( "listResponseEntity = " + listResponseEntity );
        assertTrue( true );
    }
}