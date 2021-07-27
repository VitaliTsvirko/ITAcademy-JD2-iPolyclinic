package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.dto.AppointmentDTO;

public interface IAppointmentsService {
    Appointment createAppointment(User doctor, Long medicalCardId);
    Appointment updateAppointment(AppointmentDTO appointmentDTO);
    Appointment readAppointmentById(Long id);
}
