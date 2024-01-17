package in.dev.gmsk.service.Impl;

import in.dev.gmsk.exception.ResourceNotFound;
import in.dev.gmsk.model.Student;
import in.dev.gmsk.repository.StudentRepo;
import in.dev.gmsk.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo repo;

    public StudentServiceImpl(StudentRepo repo) {
        this.repo = repo;
    }

    @Override
    public Student saveStudent(Student student) {
        return repo.save( student );
    }

    @Override
    public Stream<Student> allStudentList() {
        return repo.findAll().parallelStream();
    }

    @Override
    public Student findByIdStudent(long studentId) {
        return repo.findById( studentId ).orElseThrow( () ->
                new ResourceNotFound( "student", "Id", studentId ) );
    }

    @Override
    public Student updateStudent(long studentId, Student student) {

        Student exitingstudent = repo.findById( studentId ).orElseThrow( () ->
                new ResourceNotFound( "student", "Id", studentId ) );

        exitingstudent.setFirstName( student.getFirstName() );
        exitingstudent.setLastName( student.getLastName() );
        exitingstudent.setDob( student.getDob() );
        exitingstudent.setGender( student.getGender() );
        exitingstudent.setAdmissionNo( student.getAdmissionNo() );
        exitingstudent.setEmailId( student.getEmailId() );
        exitingstudent.setAddress( student.getAddress() );
        exitingstudent.setMobileNo( student.getMobileNo() );
        exitingstudent.setFatherName( student.getFatherName() );
        exitingstudent.setMotherName( student.getMotherName() );

        repo.save( exitingstudent );

        return exitingstudent;
    }
}
