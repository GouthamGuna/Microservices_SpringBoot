package in.gmsk.client;

import in.gmsk.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {

    @GetExchange("employee/findByDepartmentId/{departmentId}")
    public List< Employee > findByDepartment(@PathVariable Long departmentId);
}
