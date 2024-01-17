package in.dev.gmsk.service;

import in.dev.gmsk.model.Student;

import java.util.stream.Stream;

public interface StudentService {

    Student saveStudent(Student student);

    Stream<Student> allStudentList();

    Student findByIdStudent(long studentId);

    Student updateStudent(long studentId, Student student);
}
