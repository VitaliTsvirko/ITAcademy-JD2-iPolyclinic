package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.User;

import java.util.List;

public interface IMedicalCardService {

    User getUserByMedicalCardId(Long id);
    List<Appointment> getAllAppointments(Long id);
    MedicalCard getMedicalCardById(Long id);
}
