package in.dev.gmsk.controller;

import in.dev.gmsk.model.Student;
import in.dev.gmsk.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save-new-student")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        return new ResponseEntity<>( studentService.saveStudent( student ), HttpStatus.CREATED );
    }

    @GetMapping("/all-students")
    public ResponseEntity<List<Student>> allStudentList() {
        return new ResponseEntity<>( studentService
                .allStudentList()
                .collect( Collectors.toList() ), HttpStatus.OK );
    }

    @GetMapping("/student-by-id")
    public ResponseEntity<Student> findByIdStudent(@RequestParam("primaryKey") long studentId) {
        return new ResponseEntity<>( studentService.findByIdStudent( studentId ), HttpStatus.OK );
    }

    @PutMapping("/student-update/{primaryKey}")
    public ResponseEntity<Student> updateStudent(@PathVariable("primaryKey") long studentId,
                                                 @RequestBody Student student) {
        return new ResponseEntity<>( studentService.updateStudent( studentId, student ), HttpStatus.OK );
    }
}
