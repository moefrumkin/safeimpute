package safeimputer.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SafeimputeApplication {

	HashMap<Double, Double> t = new HashMap<Double, Double>();
	
	public static void main(String[] args) {
		SpringApplication.run(SafeimputeApplication.class, args);
		
	}

	public HashMap<Double, Double> test1() {
		t.put(0.1, 0.9);
		t.put(0.2, 0.8);
		t.put(0.3, 0.7);
		t.put(0.4, 0.6);
		t.put(0.5, 0.5);

		return t;
	}

	public static double bestRatio(HashMap<Double, Double> map, double floorValue) {
		// function should just return the x coordinate of the best ratio point
		double best = Double.MAX_VALUE;
		for (Map.Entry<Double, Double> entry : map.entrySet()) {
            Double key = entry.getKey();
            Double value = entry.getValue();
            if (value != 1 && value > floorValue && key / value < best) {
				best = key / value;
			}
        }
		return best;
	}

	public HashMap<String, Double> sumStats(Map<Double, Double> map, double floorValue) {
		// function should just return the x coordinate of the best near floor point
		HashMap<String, Double> ans = new HashMap<String, Double>();
		double nearest = -1;
		double difference = Double.MAX_VALUE;
		double sum = 0;
		double total = 0.0;
		ArrayList<Double> list = new ArrayList<Double>();
		for (Map.Entry<Double, Double> entry : map.entrySet()) {
            Double key = entry.getKey();
            Double value = entry.getValue();
            if (difference > value - floorValue && value - floorValue > 0) {
				difference = value - floorValue;
				nearest = key;
			}
			if (value > floorValue) {
				sum += value;
				total++;
				list.add(value);
			}
        }
		Collections.sort(list);
		ans.put("nearest", nearest);
		ans.put("mean", sum / total);
		ans.put("median", list.size() % 2 == 1 ? list.get(list.size() / 2) : (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2);
		ans.put("high", list.get(list.size() - 1));
		ans.put("low", list.get(0));
		return ans;
	}
	
}
