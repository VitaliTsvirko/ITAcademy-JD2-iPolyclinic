package by.it_academy.jd2.controller.rest.metrics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@RestController
@RequestMapping("/api/management")
public class AppointmentMetricsRestController {

    private final ObjectMapper objectMapper;

    public AppointmentMetricsRestController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<?> getMetrics() throws JsonProcessingException {

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), 12,31);

        List<LocalDate> collectMonths = start.datesUntil(end, Period.ofMonths(1)).collect(Collectors.toList());

        Map<String, List<String>> data = new HashMap<>();

        data.put("date", collectMonths.stream().map(dt -> dt.toString()).collect(Collectors.toList()));

        data.put("count", new Random().ints(0,500).limit(collectMonths.size()).boxed().map(v -> v.toString()).collect(Collectors.toList()));

        String s = objectMapper.writeValueAsString(data);

        return new ResponseEntity<Object>(s, HttpStatus.OK);
    }
}
