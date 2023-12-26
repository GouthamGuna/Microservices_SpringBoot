package in.dev.gmsk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

@Service
public class DataReader {
    private final Logger logger = LoggerFactory.getLogger( DataReader.class );

    public String fileDataReader(String filePath) {
        logger.info( "Starting" );
        String returnResult = null;
        BufferedReader bufferedReader = null;
        try {

            File file = new File( filePath );
            if (file.exists()) {

                bufferedReader = new BufferedReader( new FileReader( file ) );
                while (bufferedReader.readLine() != null) {
                    returnResult = bufferedReader.readLine();
                }
            } else returnResult = "Invalid File Path!";

        } catch (Exception e) {
            logger.error( "method catch block : { }", e );
        } finally {
            try {
                if (null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                logger.error( "finally catch block : { }", e );
            }
        }
        logger.info( "Ending" );
        return (returnResult != null) ? returnResult : "Nothing Found!";
    }
}
