package in.dev.gmsk.response;

public class EmployeeNotFound {

    private String empId;

    private String message;

    public EmployeeNotFound(String empId, String message) {
        this.empId = empId;
        this.message = message;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
