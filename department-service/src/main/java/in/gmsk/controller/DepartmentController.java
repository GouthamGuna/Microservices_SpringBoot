package in.gmsk.controller;

import in.gmsk.client.EmployeeClient;
import in.gmsk.entity.Department;
import in.gmsk.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(Department.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping("/save")
    public Department add(@RequestBody Department department){
        LOGGER.info("Department save : {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping("/fetchAll")
    public List<Department> findAll(){
        LOGGER.info("Department findAll");
        return repository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Department findById(@PathVariable Long id){
        LOGGER.info("Department findById : {}", id);
        return repository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees(){
        LOGGER.info("Department findAllWithEmployees");
        List<Department> departments
                = repository.findAll();
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(department.getId())));
        return departments;
    }
}
