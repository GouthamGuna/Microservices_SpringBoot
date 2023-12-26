package in.dev.gmsk.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilControllerTest {
    @Autowired
    private final UtilController utilController;

    UtilControllerTest(UtilController utilController) {
        this.utilController = utilController;
    }

    @Test
    void fileDataReader() {

        String filePath = "D:\\work\\vs_code_ws\\data_reader_fn\\README.md";

        String url = "http://localhost:8080/api/v1/util/data-reader?filePath="+ filePath;

        ResponseEntity<String> stringResponseEntity =
                utilController.fileDataReader( url );

        System.out.println( "stringResponseEntity = " + stringResponseEntity );
    }
}