package safeimputer.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/yeo")
    public Object sayYeo(){
        return "YEOOO";
    }
}
