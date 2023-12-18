package in.dev.gmsk.service.Impl;

import in.dev.gmsk.model.Staff;
import in.dev.gmsk.repository.StaffRepo;
import in.dev.gmsk.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private final StaffRepo staffRepo;

    public StaffServiceImpl(StaffRepo staffRepo) {
        this.staffRepo = staffRepo;
    }

    @Override
    public Stream<Staff> getStaffPeriodsDetails() {
        return staffRepo.getStaffPeriodDetails();
    }

    @Override
    public Stream<Staff> getStaffTimeSheetById(String staffId, String locationId, String academicYearId) {
        return staffRepo.getStaffTimeSheetById( staffId, locationId, academicYearId);
    }
}
