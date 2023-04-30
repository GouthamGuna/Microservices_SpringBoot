package in.gmsk.controller;

import in.gmsk.entity.Employee;
import in.gmsk.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository repository;

    @PostMapping("/save")
    public Employee add(@RequestBody Employee employee){
        LOGGER.info("Employee save : {}", employee);
        return repository.add(employee);
    }

    @GetMapping("/fetchAll")
    public List<Employee> findAll(){
        LOGGER.info("Employee findAll");
        return repository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Employee findById(@PathVariable Long id){
        LOGGER.info("Employee findById : {}", id);
        return repository.findById(id);
    }

    @GetMapping("/findByDepartmentId/{departmentId}")
    public List<Employee> findByDepartmentId(@PathVariable Long departmentId){
        LOGGER.info("Employee findByDepartmentId : {}", departmentId);
        return repository.findByDepartmentId(departmentId);
    }

}
