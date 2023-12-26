package in.dev.gmsk.controller;

import in.dev.gmsk.util.DataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/util")
public class UtilController {
    @Autowired
    private final DataReader dataReader;

    public UtilController(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    @GetMapping("/data-reader")
    public ResponseEntity<String> fileDataReader(@RequestParam("filePath") String filePath) {
        return new ResponseEntity<>( dataReader.fileDataReader( filePath ), HttpStatus.OK );
    }
}
