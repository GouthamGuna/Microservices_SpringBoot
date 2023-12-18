package in.dev.gmsk.util;

public class StaffSQLConstant {

    public static final String getStaffPeriodsDetails = "SELECT DISTINCT ctpsd.`timetableId`, CONCAT(tr.`firstname`,' ',tr.`lastname`) AS staffName, ctd.dayname, cpd.`particular`, sc.`classname`, CASE WHEN ssp.`specializationname` IS NULL THEN ' - ' ELSE ssp.`specializationname` END AS spec , ssec.`sectionname`, ss.`subjectname` FROM `class_timetable_period_subject_details` ctpsd JOIN `teacher_classteacher` tc ON tc.`id`=ctpsd.`teacherId` JOIN `setup_subject_name` ss ON ss.`id`=ctpsd.`subjectId` JOIN `campus_timetable_day` ctd ON ctd.`id` = ctpsd.dayId JOIN `class_timetable` ctt ON ctt.`id` = ctpsd.`timetableId` JOIN `setup_class_period_details` cpd ON cpd.`id` = ctpsd.`periodId` JOIN `setup_class` sc ON sc.`id` = ctt.`classId` LEFT JOIN `setup_specialization` ssp ON ssp.`id` = ctt.`specId` JOIN `setup_section` ssec ON ssec.`id` = ctt.`sectionId` JOIN `teacher_registration` tr ON tr.`id` = tc.`teacherId` ORDER BY  ctd.`id`,cpd.`particular`;"; // WHERE ctpsd.`teacherId`='9' AND ctt.`locationId`='1' AND ctt.`accyearId`='2' AND sc.`isActive`='Y'
    public static final String getStaffTimeSheetById = "SELECT DISTINCT ctpsd.`timetableId`, CONCAT(tr.`firstname`,' ',tr.`lastname`) AS staffName, ctd.dayname, cpd.`particular`, sc.`classname`, CASE WHEN ssp.`specializationname` IS NULL THEN ' - ' ELSE ssp.`specializationname` END AS spec , ssec.`sectionname`, ss.`subjectname` FROM `class_timetable_period_subject_details` ctpsd JOIN `teacher_classteacher` tc ON tc.`id`=ctpsd.`teacherId` JOIN `setup_subject_name` ss ON ss.`id`=ctpsd.`subjectId` JOIN `campus_timetable_day` ctd ON ctd.`id` = ctpsd.dayId JOIN `class_timetable` ctt ON ctt.`id` = ctpsd.`timetableId` JOIN `setup_class_period_details` cpd ON cpd.`id` = ctpsd.`periodId` JOIN `setup_class` sc ON sc.`id` = ctt.`classId` LEFT JOIN `setup_specialization` ssp ON ssp.`id` = ctt.`specId` JOIN `setup_section` ssec ON ssec.`id` = ctt.`sectionId` JOIN `teacher_registration` tr ON tr.`id` = tc.`teacherId` ;"; // WHERE ctpsd.`teacherId`=? AND ctt.`locationId`=? AND ctt.`accyearId`=? AND sc.`isActive`='Y' ORDER BY  ctd.`id`,cpd.`particular`
}
