package in.dev.gmsk.service.ServiceImpl;

import in.dev.gmsk.model.Person;
import in.dev.gmsk.repository.MockData;
import in.dev.gmsk.service.PersonService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public List<Person> getEmployeeByMailId(String empId) throws IOException {

        List<Person> people = MockData.getPeople();
        Predicate<? super Person> predicate =
                person -> person.getEmail().equalsIgnoreCase(empId);

        return people.stream().filter(predicate).toList();
    }

    @Override
    public Map<String, Long> getEmployeeCountByGender() throws IOException {

        List<Person> people = MockData.getPeople();

        return people.stream().collect(
                Collectors.groupingBy(Person::getGender,
                        Collectors.counting())
        );
    }
}
