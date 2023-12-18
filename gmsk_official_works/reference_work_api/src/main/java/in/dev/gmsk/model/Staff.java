package in.dev.gmsk.model;

import lombok.Data;

@Data
public class Staff {

    private String staffId;
    private String staffName;
    private int timeTableId;
    private String dayName;
    private String particular;
    private String className;
    private String specializationName;
    private String sectionName;
    private String subjectName;
    private String locationId;
    private String academicYearId;
}
