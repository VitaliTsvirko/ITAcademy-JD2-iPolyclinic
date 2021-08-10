package by.it_academy.jd2.controller.rest.metrics;

import by.it_academy.jd2.config.Constants;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.service.HealthMetricService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@RestController
@RequestMapping("/api/user")
public class HealthMetricRestController {

    private final HealthMetricService healthMetricService;

    private final ObjectMapper objectMapper;

    public HealthMetricRestController(HealthMetricService healthMetricService, ObjectMapper objectMapper) {
        this.healthMetricService = healthMetricService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{userId}/healthmetrics")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR') or @securityAccessHandler.isAuthenticationUserIdEqualsRequestId(#userId)")
    public ResponseEntity<?> readAllMetrics(@PathVariable Long userId){
        return new ResponseEntity<>(healthMetricService.getUserAnalysisHealthMetricsByUserId(userId), HttpStatus.OK);
    }


    @PostMapping("/{userId}/healthmetrics")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR') or @securityAccessHandler.isAuthenticationUserIdEqualsRequestId(#userId)")
    public ResponseEntity<?> writeMetrics(@RequestBody HealthMetricEntityDTO[] metricsDTO, @PathVariable Long userId){
        return new ResponseEntity<>(healthMetricService.addMetricsByUserId(Arrays.stream(metricsDTO).collect(Collectors.toList()), userId), HttpStatus.OK);
    }




    @GetMapping("/{userId}/healthmetrics/{type}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR') or @securityAccessHandler.isAuthenticationUserIdEqualsRequestId(#userId)")
    public ResponseEntity<?> readMetricByTypeAndByUserId(@PathVariable Long userId, @PathVariable HealthMetricsTypes type) throws JsonProcessingException {
        List<HealthMetricEntityDTO> data = healthMetricService.getMetricDataByUserIdAndMetricType(userId, type);

        Map<String, List<String>> chartData = new HashMap<>();

        chartData.put("date", data.stream().map(dto -> dto.getTimestamp().format(DateTimeFormatter.ofPattern(Constants.dateTimeFormat))).collect(Collectors.toList()));
        chartData.put("values", data.stream().map(dto -> dto.getValue().toString()).collect(Collectors.toList()));

        String chartDataString = objectMapper.writeValueAsString(chartData);

        return new ResponseEntity<Object>(chartDataString, HttpStatus.OK);
    }

}
