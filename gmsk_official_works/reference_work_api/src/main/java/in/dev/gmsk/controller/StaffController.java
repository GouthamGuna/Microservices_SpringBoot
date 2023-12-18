package in.dev.gmsk.controller;

import in.dev.gmsk.model.Staff;
import in.dev.gmsk.response.WarningMessage;
import in.dev.gmsk.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    @Autowired
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
    @GetMapping("/period-list")
    public List<Staff> getStaffPeriodsDetails(){
        return staffService.getStaffPeriodsDetails().toList();
    }

    @GetMapping("/time-sheet")
    public ResponseEntity<?> getStaffTimeSheetById(@PathVariable String staffId,
                @PathVariable String locationId, @PathVariable String academicYearId){

        return new ResponseEntity<>(
                staffService.getStaffTimeSheetById(staffId,
                        locationId, academicYearId).toList(), HttpStatus.OK
        );
    }
}
