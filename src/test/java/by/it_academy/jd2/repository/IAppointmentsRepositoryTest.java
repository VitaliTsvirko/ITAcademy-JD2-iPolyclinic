package by.it_academy.jd2.repository;

import by.it_academy.jd2.config.JacksonConfig;
import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.service.api.IAppointmentsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class, JacksonConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class IAppointmentsRepositoryTest {

    @Autowired
    private IAppointmentsRepository appointmentsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getMetrics(){

        //Map<Long, String> day = appointmentsRepository.countAppointmentsByDateRange("day", "2021-01-01", "2021-12-31");


        LocalDate start1 = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1);
        LocalDate end1 = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().lengthOfMonth());


        //List<String[]> data = appointmentsRepository.countAppointmentsByDateRange("day", start1.toString(), end1.toString());
        List<String[]> data = appointmentsRepository.countAppointmentsByDateRange(start1.toString(), end1.toString());


        List<LocalDate> collectDays = start1.datesUntil(end1, Period.ofDays(1)).collect(Collectors.toList());

        Map<LocalDate, Long> result = new LinkedHashMap<>();

        for (String[] item : data) {
                String count = item[0];
                String[] dateString = item[1].split("-");
                result.put(LocalDate.of(Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]), Integer.parseInt(dateString[2])),
                        Long.valueOf(count));
        }

        for (LocalDate day : collectDays) {
            result.put(day, 0L);
        }


        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), 12,31);

        List<LocalDate> collectMonths = start.datesUntil(end, Period.ofMonths(1)).collect(Collectors.toList());






        System.out.println(collectMonths.size());


    }


    @Test
    void getJson() throws JsonProcessingException {

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate end = LocalDate.of(LocalDate.now().getYear(), 12,31);

        List<LocalDate> collectMonths = start.datesUntil(end, Period.ofMonths(1)).collect(Collectors.toList());

        Map<String, List<String>> data = new HashMap<>();

        data.put("date", collectMonths.stream().map(dt -> dt.toString()).collect(Collectors.toList()));

        data.put("count", LongStream.range(0,100).limit(collectMonths.size()).boxed().map(v -> v.toString()).collect(Collectors.toList()));


        String s = objectMapper.writeValueAsString(data);

        System.out.println(s);


    }







}