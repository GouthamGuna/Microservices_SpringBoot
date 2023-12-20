package in.dev.gmsk.controller;

import in.dev.gmsk.model.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StaffControllerTest {
    @Autowired
    private StaffController staffController;

    @Test
    void getStaffPeriodsDetails() {

        List<Staff> staffPeriodsDetails =
                staffController.getStaffPeriodsDetails();

        assertNotNull( staffPeriodsDetails );

        System.out.println( "staffPeriodsDetails = " + staffPeriodsDetails );
    }

    @Test
    void getStaffTimeSheetById() {

        String staffId = "9";
        String locationId = "1";
        String academicYearId = "2";

        ResponseEntity<?> staffTimeSheetById =
                staffController.getStaffTimeSheetById( staffId, locationId, academicYearId );

        System.out.println( "staffTimeSheetById = " + staffTimeSheetById );
    }

    @Test
    void getAllStaffListByTeacherClassTeacher() {

        ResponseEntity<List<Staff>> allStaffListByTeacherClassTeacher =
                staffController.getAllStaffListByTeacherClassTeacher();

        assertNotNull( allStaffListByTeacherClassTeacher );

        System.out.println( "allStaffListByTeacherClassTeacher = " +
                allStaffListByTeacherClassTeacher );
    }
}