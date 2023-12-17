package in.dev.gmsk.controller;

import in.dev.gmsk.model.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}