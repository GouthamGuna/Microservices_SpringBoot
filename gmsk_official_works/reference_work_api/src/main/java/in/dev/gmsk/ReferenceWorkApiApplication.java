package in.dev.gmsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReferenceWorkApiApplication {

    public static void main(String[] args) {
        SpringApplication.run( ReferenceWorkApiApplication.class, args );
    }

	/*  StaffRepo staffRepo = context.getBean( StaffRepo.class );
		Stream<Staff> staffPeriodDetails = staffRepo.getStaffPeriodDetails();
		System.out.println( "staffPeriodDetails = " + staffPeriodDetails.toList() );
	*/
}
