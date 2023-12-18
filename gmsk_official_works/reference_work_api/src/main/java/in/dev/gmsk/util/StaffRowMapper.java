package in.dev.gmsk.util;

import in.dev.gmsk.model.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffRowMapper implements RowMapper<Staff> {
    @Override
    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {

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
    }
}
