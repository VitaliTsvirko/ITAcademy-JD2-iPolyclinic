package by.it_academy.jd2.controller.rest.metrics;

import by.it_academy.jd2.service.api.IUserHealthMetricsService;
import by.it_academy.jd2.service.dto.metrics.UserHealthMetricsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api2/user")
public class UserHealthMetricsRestController {


    public final IUserHealthMetricsService userHealthMetricsService;


    public UserHealthMetricsRestController(IUserHealthMetricsService userHealthMetricsService) {
        this.userHealthMetricsService = userHealthMetricsService;
    }

    @GetMapping("/{id}/healthmetrics/all")
    public ResponseEntity<?> readAllUserHealthMetricsByUserId(@PathVariable Long id){
        return new ResponseEntity<>(userHealthMetricsService.getAllHealthMetricByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/healthmetrics")
    public ResponseEntity<?> readLastUserHealthMetricsByUserId(@PathVariable Long id){
        return new ResponseEntity<>(userHealthMetricsService.getLastHealthMetricByUserId(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/healthmetrics")
    public ResponseEntity<?> createUserHealthMetricsByUserId(@RequestBody UserHealthMetricsDTO metricsDTO,  @PathVariable Long id){
        return new ResponseEntity<>(userHealthMetricsService.addHealthMetric(metricsDTO, id), HttpStatus.OK);
    }

}
