package in.dev.gmsk.repository;

import in.dev.gmsk.model.Staff;
import in.dev.gmsk.util.StaffRowMapper;
import in.dev.gmsk.util.StaffSQLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class StaffRepo {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StaffRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Stream<Staff> getStaffPeriodDetails() {

        String query = StaffSQLConstant.getStaffPeriodsDetails;

        List<Staff> returnList = jdbcTemplate.query( query, (rs, rn) -> {

            Staff staff = new Staff();
            staff.setTimeTableId( rs.getInt( 1 ) );
            staff.setStaffName( rs.getString( "staffName" ) );
            staff.setDayName( rs.getString( "dayname" ) );
            staff.setParticular( rs.getString( "particular" ) );
            staff.setClassName( rs.getString( "classname" ) );
            staff.setSpecializationName( rs.getString( "spec" ) );
            staff.setSectionName( rs.getString( "sectionname" ) );
            staff.setSubjectName( rs.getString( "subjectname" ) );

            return staff;
        } );

        return returnList.parallelStream();
    }

    public Stream<Staff> getStaffTimeSheetById(String staffId, String locationId, String academicYearId) {

        String baseQuery = StaffSQLConstant.getStaffTimeSheetById;

        StringBuilder dynamicQuery = new StringBuilder( baseQuery );

        if (locationId != null) {
            dynamicQuery.append( " AND locationId = ?" );
        }
        if (academicYearId != null) {
            dynamicQuery.append( " AND academicYearId = ?" );
        }
        if (staffId != null) {
            dynamicQuery.append( " AND staffId = ? ORDER BY  ctd.`id`,cpd.`particular`" );
        }

        Map<String, Object> paramMap = new HashMap<>();
        if (locationId != null) {
            paramMap.put( "locationId", locationId );
        }
        if (academicYearId != null) {
            paramMap.put( "academicYearId", academicYearId );
        }
        if (staffId != null) {
            paramMap.put( "staffId", staffId );
        }

        List<Staff> staffList =
                jdbcTemplate.query( dynamicQuery.toString(), new Map[]{paramMap}, new StaffRowMapper());

        return staffList.parallelStream();
    }
}
