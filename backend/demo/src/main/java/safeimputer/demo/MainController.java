package safeimputer.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MainController {

    HashMap<String, Double> dataStore = new HashMap<String, Double>();
    
    @GetMapping("/yeo")
    public Object sayYeo(){
        return "YEOOO";
    }

    @GetMapping("/NumberOfPoints")
    public int getNumberOfPoints() {
        return 1; // this will be subbed out for number of points for graph
    }

    @GetMapping("/Points")
    public HashMap<Double, Double> getPoints() { // points on graph
        return new HashMap<Double, Double>();
    }

    @GetMapping("/Summary")
    public HashMap<String, Double> getSummaryStatistics() { // could be used for important percentage points to be mentioned
        return new HashMap<String, Double>();
    }

    @PostMapping("/parametersUploadDouble") 
    public ResponseEntity<String> handleRequestDouble(@RequestBody Map<String, Double> requestPayload) {
        double p1 = requestPayload.get("minPrivacy");
        double p2 = requestPayload.get("maxPrivacy");
        double p3 = requestPayload.get("numSteps");

        if (p1 < 0 || p1 >= 100 || p1 > p2 || p2 < 0 || p2 >= 100 || p3 < 0) {
            return ResponseEntity.ok("Fail");
        }
        dataStore.put("lowerBound", p1);
        dataStore.put("upperBound", p2);
        dataStore.put("steps", p3);

        return ResponseEntity.ok("Success"); // can include more parameters
    }

    @PostMapping("/parametersUploadString") 
    public ResponseEntity<String> handleRequestString(@RequestBody Map<String, String> requestPayload) {
        String parameter1 = requestPayload.get("parameter1");
        String parameter2 = requestPayload.get("parameter2");

        return ResponseEntity.ok("Parameters received: " + parameter1 + ", " + parameter2); // can include more parameters
    }

    @PostMapping("/parametersUploadInteger") 
    public ResponseEntity<String> handleRequestInteger(@RequestBody Map<String, Integer> requestPayload) {
        int parameter1 = requestPayload.get("parameter1");
        int parameter2 = requestPayload.get("parameter2");

        return ResponseEntity.ok("Parameters received: " + parameter1 + ", " + parameter2); // can include more parameters
    }

    @PostMapping("/parametersUploadFloorValue") 
    public ResponseEntity<String> handleRequestForFloorValue(@RequestBody Map<String, Double> requestPayload) {
        double p1 = requestPayload.get("floorValue");
        if (p1 < 0 || p1 >= 100) {
            return ResponseEntity.ok("Fail");
        }
        dataStore.put("floorValue", p1);
        return ResponseEntity.ok("Success"); // can include more parameters
    }

    @GetMapping("/nearest")
    public double getNearest() {
        SafeimputeApplication i = new SafeimputeApplication();
        HashMap<Double, Double> t = i.test1();
        //return i.nearestToFloorValue(t);
        return 1;
    }

    @GetMapping("/best")
    public double getBest() {
        return 2.0;
    }
}
