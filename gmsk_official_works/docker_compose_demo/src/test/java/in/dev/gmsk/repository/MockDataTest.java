package in.dev.gmsk.repository;

import in.dev.gmsk.model.Car;
import in.dev.gmsk.model.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MockDataTest {

    @Test
    void getPeople() throws IOException {

        List<Person> people = MockData.getPeople();
        System.out.println("people = " + people);
    }

    @Test
    void getCars() throws IOException {

        List<Car> cars = MockData.getCars();
        System.out.println("cars = " + cars);
    }
}