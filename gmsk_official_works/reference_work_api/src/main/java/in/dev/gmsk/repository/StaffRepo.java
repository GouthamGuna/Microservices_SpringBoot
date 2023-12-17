package in.dev.gmsk.repository;

import in.dev.gmsk.model.Staff;
import in.dev.gmsk.util.StaffSQLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public class StaffRepo {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StaffRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Stream<Staff> getStaffPeriodDetails(){

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
}
