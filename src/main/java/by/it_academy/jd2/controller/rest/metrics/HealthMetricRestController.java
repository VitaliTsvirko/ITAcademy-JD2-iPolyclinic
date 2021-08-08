package by.it_academy.jd2.controller.rest.metrics;

import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.dto.UserHealthMetricDTO;
import by.it_academy.jd2.service.HealthMetricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@RestController
@RequestMapping("/api/user")
public class HealthMetricRestController {

    private final HealthMetricService healthMetricService;

    public HealthMetricRestController(HealthMetricService healthMetricService) {
        this.healthMetricService = healthMetricService;
    }

    @GetMapping("/{userId}/healthmetrics")
    public ResponseEntity<?> readAllMetrics(@PathVariable Long userId){
        return new ResponseEntity<>(healthMetricService.getUserAnalysisHealthMetricsByUserId(userId), HttpStatus.OK);
    }


    @PostMapping("/{userId}/healthmetrics")
    public ResponseEntity<?> writeMetrics(@RequestBody HealthMetricEntityDTO[] metricsDTO, @PathVariable Long userId){
        return new ResponseEntity<>(healthMetricService.addMetricsByUserId(Arrays.stream(metricsDTO).collect(Collectors.toList()), userId), HttpStatus.OK);
    }

}
