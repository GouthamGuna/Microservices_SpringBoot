package in.dev.gmsk.service;

import in.dev.gmsk.model.Staff;

import java.util.stream.Stream;

public interface StaffService {

    Stream<Staff> getStaffPeriodsDetails();

    Stream<Staff> getStaffTimeSheetById(String staffId, String locationId, String academicYearId);

    Stream<Staff> getAllStaffListByTeacherClassTeacher();
}
