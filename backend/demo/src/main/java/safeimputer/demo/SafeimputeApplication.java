package safeimputer.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SafeimputeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeimputeApplication.class, args);
	}

	public static double bestRatio(HashMap<Double, Double> map, double floorValue) {
		// function should just return the x coordinate of the best ratio point
		double best = Double.MAX_VALUE;
		for (Map.Entry<Double, Double> entry : map.entrySet()) {
            Double key = entry.getKey();
            Double value = entry.getValue();
            if (value > floorValue && key / value < best) {
				best = key / value;
			}
        }
		return best;
	}

	public static double nearestToFloorValue(HashMap<Double, Double> map, double floorValue) {
		// function should just return the x coordinate of the best ratio point
		Iterator it = map.keySet().iterator();
		double nearest;
		double difference = Double.MAX_VALUE;
		for (Map.Entry<Double, Double> entry : map.entrySet()) {
            Double key = entry.getKey();
            Double value = entry.getValue();
            if (true) {
				difference = Math.abs(key - floorValue);
			}
        }
		return best;
	}
	
}
