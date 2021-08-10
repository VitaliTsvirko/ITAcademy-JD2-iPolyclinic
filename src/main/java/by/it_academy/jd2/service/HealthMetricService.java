package by.it_academy.jd2.service;

import by.it_academy.jd2.core.exceptions.MedicalCardNotFoundException;
import by.it_academy.jd2.core.healthmetrics.IHealthMetricHandler;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricAnalysisDTO;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.dto.UserHealthMetricDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.HealthMetrics;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.GenderType;
import by.it_academy.jd2.repository.IHealthMetricsRepository;
import by.it_academy.jd2.repository.IMedicalCardRepository;
import by.it_academy.jd2.repository.IUsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Vitali Tsvirko
 */
@Service
@Transactional
public class HealthMetricService {

    private final IUsersRepository usersRepository;

    private final IHealthMetricsRepository healthMetricsRepository;

    private final IHealthMetricHandler healthMetricHandler;

    private final IMedicalCardRepository medicalCardRepository;

    public HealthMetricService(IUsersRepository usersRepository, IHealthMetricsRepository healthMetricsRepository, IHealthMetricHandler healthMetricHandler, IMedicalCardRepository medicalCardRepository) {
        this.usersRepository = usersRepository;
        this.healthMetricsRepository = healthMetricsRepository;
        this.healthMetricHandler = healthMetricHandler;
        this.medicalCardRepository = medicalCardRepository;
    }


    @Transactional(readOnly = true)
    public List<HealthMetricAnalysisDTO> getUserAnalysisHealthMetricsByUserId(Long userId){
        GenderType genderType = Optional.ofNullable(usersRepository.getById(userId).getPassport())
                                                        .map(passport -> passport.getGenderType())
                                                        .orElse(null);

        List<HealthMetricEntityDTO> metrics = getUserHealthMetricsByUserId(userId);
        List<HealthMetricAnalysisDTO> analysisMetrics = healthMetricHandler.analysisMetrics(metrics, genderType);
        return analysisMetrics;
    }


    @Transactional(readOnly = true)
    public List<HealthMetricEntityDTO> getUserHealthMetricsByUserId(Long userId){
        return medicalCardRepository.findByUserId(userId)
                .map(medicalCard ->
                        entityMetricsMapper(
                                healthMetricsRepository.findLastUserMetricsByMedicalCardId(
                                        medicalCard.getId()
                                )
                        )
                )
                .orElseThrow(() -> new MedicalCardNotFoundException("Medical card of user with id = " + userId + " was nor found"));
    }



    public List<HealthMetricAnalysisDTO> addMetricsByUserId(List<HealthMetricEntityDTO> metricEntityDTOS, Long userId){
        return medicalCardRepository.findByUserId(userId).map(medicalCard -> {
            metricEntityDTOS.forEach(metric -> {
                ///valid ???????
                ////???????
                healthMetricsRepository.saveAndFlush(new HealthMetrics(metric.getType(),
                        metric.getValue(),
                        LocalDateTime.now(),
                        usersRepository.getById(userId),
                        medicalCard));

            });


            //Calculate metrics
            List<HealthMetricEntityDTO> calculateMetricsList =
                    healthMetricHandler.calculateMetrics(
                              userHealthMetricMapper(
                                      getUserHealthMetricsByUserId(userId),
                                      usersRepository.getById(userId)
                              )
            );

            //Save calculated metrics
            calculateMetricsList.forEach(metric ->
                    healthMetricsRepository.saveAndFlush(new HealthMetrics(metric.getType(),
                            metric.getValue(),
                            metric.getTimestamp(),
                            usersRepository.getById(userId),
                            medicalCard))
            );

            return getUserAnalysisHealthMetricsByUserId(userId);
        }).orElseThrow(() -> new MedicalCardNotFoundException("Medical card of user with id = " + userId + " was nor found"));

    }


    public List<HealthMetricEntityDTO> getMetricDataByUserIdAndMetricType(Long userId, HealthMetricsTypes metricType){
        return medicalCardRepository.findByUserId(userId).map(medicalCard ->
                        healthMetricsRepository.findHealthMetricsByTypesAndAndMedicalCardIdOrderByTimestampAsc(metricType, medicalCard.getId()).stream().map(
                                entity -> new HealthMetricEntityDTO(entity)).collect(Collectors.toList())
                        ).orElseThrow(() -> new MedicalCardNotFoundException("Medical card of user with id = " + userId + " was nor found"));
    }


    /**
     * Данный метод выполеят преобразование {@link List<HealthMetricEntityDTO>} в {@link UserHealthMetricDTO}
     * для дальнейшей работы с {@link by.it_academy.jd2.core.healthmetrics.HealthMetricsHandler}
     * @param entityMetrics спиок метрик пользователя
     * @return {@link UserHealthMetricDTO} с текущими метриками
     */
    private UserHealthMetricDTO userHealthMetricMapper(List<HealthMetricEntityDTO> entityMetrics, User user){
        UserHealthMetricDTO userHealthMetric = new UserHealthMetricDTO();

        //Mapping metricType and entity metric data
        Map<HealthMetricsTypes, HealthMetricEntityDTO> metricsMap = entityMetrics.stream()
                .collect(Collectors.toMap(item -> item.getType(), item -> item));


        //Mapping All available user health metric to UserHealthMetricDTO
        userHealthMetric.setHeight(Optional.ofNullable(metricsMap.get(HealthMetricsTypes.HEIGHT))
                .map(val -> val.getValue())
                .orElse(null));
        userHealthMetric.setWeight(Optional.ofNullable(metricsMap.get(HealthMetricsTypes.WEIGHT))
                .map(val -> val.getValue())
                .orElse(null));

        userHealthMetric.setHeartRate(Optional.ofNullable(metricsMap.get(HealthMetricsTypes.HEART_RATE))
                .map(val -> val.getValue().intValue())
                .orElse(null));

        userHealthMetric.setAdDia(Optional.ofNullable(metricsMap.get(HealthMetricsTypes.AD_DIA))
                .map(val -> val.getValue().intValue())
                .orElse(null));
        userHealthMetric.setAdSys(Optional.ofNullable(metricsMap.get(HealthMetricsTypes.AD_SYS))
                .map(val -> val.getValue().intValue())
                .orElse(null));

        userHealthMetric.setBodyTemperature(Optional.ofNullable(metricsMap.get(HealthMetricsTypes.BODY_TEMPERATURE))
                .map(val -> val.getValue().intValue())
                .orElse(null));

        userHealthMetric.setGenderType(Optional.ofNullable(user.getPassport())
                .map(passport -> passport.getGenderType())
                .orElse(null));

        userHealthMetric.setAge(Optional.ofNullable(user.getPassport())
                .map(passport -> Period.between(passport.getDateOfBirth(), LocalDate.now()).getYears())
                .orElse(null));

        return userHealthMetric;

    }


    /**
     * Данный метод выполеят преобразование данных полученный от репозитория {@link IHealthMetricsRepository} в
     * {@link List<HealthMetricEntityDTO>}.
     * Метод findLastUserMetricsByMedicalCardId возвращет массив String ["metric_type", "metric_value", "metric_timestamp"]
     * @param rawMetricsData
     * @return {@link List<HealthMetricEntityDTO>} метрик пользователя
     */
    private List<HealthMetricEntityDTO> entityMetricsMapper(List<String[]> rawMetricsData){
        return rawMetricsData.stream().map(
                        strArrElement -> new HealthMetricEntityDTO(strArrElement[1], strArrElement[2], strArrElement[0]))
                .collect(Collectors.toList());
    }


}
