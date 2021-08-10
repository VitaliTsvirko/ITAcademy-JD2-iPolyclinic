package by.it_academy.jd2.repository;

import by.it_academy.jd2.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppointmentsRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllAppointmentsByMedicalCardIdOrderByDateTimeDesc(Long id);

/*
    @Query(value = "SELECT date_trunc( :interval, appointments.date_time), count(*) \n" +
            "FROM polyclinic.appointments\n" +
            "WHERE appointments.date_time >= :date_from and appointments.date_time <= :date_to \n" +
            "GROUP BY date_trunc(:interval, appointments.date_time)", nativeQuery = true)
    List<String[]> countAppointmentsByDateRange(@Param("interval") String interval, @Param("date_from") String from, @Param("date_to") String to);
*/


    @Query(value = "SELECT date_trunc('day', appointments.date_time), count(*)\n" +
            "FROM polyclinic.appointments\n" +
            "WHERE appointments.date_time >= :date_from and appointments.date_time <= :date_to\n" +
            "GROUP BY date_trunc('day', appointments.date_time)",
            nativeQuery = true)
    List<String[]> countAppointmentsByDateRange(@Param("date_from") String from, @Param("date_to") String to);


}
