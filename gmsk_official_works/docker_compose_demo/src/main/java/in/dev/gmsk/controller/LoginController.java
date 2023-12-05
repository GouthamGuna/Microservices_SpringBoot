package in.dev.gmsk.controller;

import in.dev.gmsk.model.Person;
import in.dev.gmsk.repository.MockData;
import in.dev.gmsk.response.EmployeeNotFound;
import in.dev.gmsk.response.UserNotFound;
import in.dev.gmsk.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/api/v1")
public class LoginController {

    @Autowired
    private PersonService personService;

    @GetMapping("/loginUser")
    public String sayGreetingsByUsers(@RequestParam String userName) {
        return "Hello " + userName;
    }

    @PostMapping("/login")
    public ResponseEntity<?> requestToLogin(@RequestParam String userName, String password) {

        if (userName.equalsIgnoreCase("admin")
                && password.equalsIgnoreCase("admin@demo")) {

            return new ResponseEntity<>(sayGreetingsByUsers(userName), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(new UserNotFound("User Not Found."), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employeeList")
    public ResponseEntity<List<Person>> getEmployeeList() throws IOException {

        List<Person> people = MockData.getPeople();
        return new ResponseEntity<>(people, HttpStatus.OK);
    }

    @GetMapping("/employeeCountByGender")
    public ResponseEntity<Map<String, Long>> getEmployeeCountByGender() throws IOException {

        Map<String, Long> employeeCountByGender = personService.getEmployeeCountByGender();
        return new ResponseEntity<>(employeeCountByGender, HttpStatus.OK);
    }

    @GetMapping("/empMailID")
    public ResponseEntity<?> getEmployeeByMailId(@RequestParam String empId) throws IOException {

        List<Person> employeeById = personService.getEmployeeByMailId(empId);

        if (employeeById.isEmpty()) {
            return new ResponseEntity<>(new EmployeeNotFound(empId, "Employee Id not found!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }
}
