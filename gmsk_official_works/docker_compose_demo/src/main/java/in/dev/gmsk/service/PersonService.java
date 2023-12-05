package in.dev.gmsk.service;

import in.dev.gmsk.model.Person;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PersonService {

    Map<String, Long> getEmployeeCountByGender() throws IOException;

    List<Person> getEmployeeByMailId(String empId) throws IOException;
}
