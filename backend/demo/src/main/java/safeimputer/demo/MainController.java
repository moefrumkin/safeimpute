package safeimputer.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    
    @GetMapping("/yeo")
    public String sayYeo(){
        return "YEOOO";
    }
}
