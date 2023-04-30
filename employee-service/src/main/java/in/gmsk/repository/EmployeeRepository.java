package in.gmsk.repository;

import in.gmsk.entity.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private List< Employee > employees = new ArrayList<>();

    @PostMapping("/save")
    public Employee add(@RequestBody Employee employee){
        employees.add(employee);
        return employee;
    }

    public Employee findById(Long id){
        return employees.stream()
                .filter(employee ->
                        employee.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Employee> findAll(){
        return employees;
    }

    public List<Employee> findByDepartmentId(Long departmentId){
        return employees.stream()
                .filter(depId ->
                        depId.departmentId().equals(departmentId))
                .toList();
    }

}
