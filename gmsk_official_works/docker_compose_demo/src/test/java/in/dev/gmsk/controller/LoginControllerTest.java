package in.dev.gmsk.controller;

import in.dev.gmsk.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.Map;

// import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginControllerTest {

    // Integration Test :-)
    @Autowired
    private LoginController loginController;

    @Test
    void sayGreetingsByUsers() {

        String result =
                loginController.sayGreetingsByUsers("Gowtham Sankar");

        System.out.println("result = " + result);
    }

    @Test
    void requestToLogin() {
        String userName = "admin", password = "admin@demo";

        ResponseEntity<?> responseEntity =
                loginController.requestToLogin(userName, password);

        System.out.println("responseEntity = " + responseEntity);
    }

    @Test
    void getEmployeeList() throws IOException {

        ResponseEntity<List<Person>> employeeList =
                loginController.getEmployeeList();

        System.out.println("employeeList = " + employeeList);
    }

    @Test
    void getEmployeeCountByGender() throws IOException {

        ResponseEntity<Map<String, Long>> employeeCountByGender =
                loginController.getEmployeeCountByGender();

        System.out.println("employeeCountByGender = " + employeeCountByGender);
    }

    @Test
    void getEmployeeById() throws IOException {

        String employeeId = "dofinan0@huffingtonpost.com";

        ResponseEntity<?> getEmployeeByMailId =
                loginController.getEmployeeByMailId(employeeId);

        System.out.println("employeeById = " + getEmployeeByMailId);
    }
}