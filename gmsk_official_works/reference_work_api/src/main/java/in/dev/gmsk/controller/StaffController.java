package in.dev.gmsk.controller;

import in.dev.gmsk.model.Staff;
import in.dev.gmsk.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        return staffService.getStaffPeriodsDetails().collect( Collectors.toList());
    }

    @GetMapping("/time-sheet")
    public ResponseEntity<?> getStaffTimeSheetById(@PathVariable String staffId,
                @PathVariable String locationId, @PathVariable String academicYearId){

        return new ResponseEntity<>(
                staffService.getStaffTimeSheetById(staffId,
                        locationId, academicYearId).collect( Collectors.toList()), HttpStatus.OK
        );
    }
    @GetMapping("/staff-list")
    public ResponseEntity<List<Staff>> getAllStaffListByTeacherClassTeacher(){
        return new ResponseEntity<>(
                staffService.getAllStaffListByTeacherClassTeacher().collect( Collectors.toList()), HttpStatus.OK );
    }
 }
