package safeimputer.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
public class MainController {

    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/yeo")
    public Object sayYeo(){
        return "YEOOO";
    }

    @GetMapping("/NumberOfPoints")
    public int getNumberOfPoints() {
        return 1; // this will be subbed out for number of points for graph
    }

    @GetMapping("/Points")
    public HashMap<Double, Double> getPoints() {
        return new HashMap<Double, Double>();
    }

    @PostMapping("/parametersUpload") 
    public ResponseEntity<String> handleRequest(@RequestBody Map<String, String> requestPayload) {
        String parameter1 = requestPayload.get("parameter1");
        String parameter2 = requestPayload.get("parameter2");

        return ResponseEntity.ok("Parameters received: " + parameter1 + ", " + parameter2); // can include more parameters
    }
}
