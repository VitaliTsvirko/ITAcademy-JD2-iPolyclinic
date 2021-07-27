package by.it_academy.jd2.service;

import by.it_academy.jd2.core.exceptions.AppointmentNotFoundException;
import by.it_academy.jd2.core.exceptions.MedicalCardNotFoundException;
import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.repository.IAppointmentsRepository;
import by.it_academy.jd2.repository.IMedicalCardRepository;
import by.it_academy.jd2.service.api.IAppointmentsService;
import by.it_academy.jd2.service.api.IMedicalCardService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Transactional
public class AppointmentsService implements IAppointmentsService {

    private final IMedicalCardRepository medicalCardRepository;

    private final IAppointmentsRepository appointmentsRepository;

    public AppointmentsService(IMedicalCardRepository medicalCardRepository, IUserService userService, IAppointmentsRepository appointmentsRepository) {
        this.medicalCardRepository = medicalCardRepository;
        this.appointmentsRepository = appointmentsRepository;
    }

    @Override
    public Appointment createAppointment(User doctor, Long medicalCardId){
        return medicalCardRepository.findById(medicalCardId)
                .map(md -> {
                            Appointment appointment = new Appointment();
                            appointment.setDateTime(LocalDateTime.now());
                            appointment.setDoctor(doctor);
                            appointment.setMedicalCard(md);
                            appointmentsRepository.save(appointment);

                            return appointment;
        }).orElseThrow(() -> new MedicalCardNotFoundException("Medical card with " + medicalCardId + "was not found"));
    }


    @Override
    public Appointment updateAppointment(AppointmentDTO appointmentDTO) {
        return appointmentsRepository.findById(appointmentDTO.getId())
                .map(record -> {
                                record.setComplaints(appointmentDTO.getComplaints());
                                record.setTemperature(appointmentDTO.getTemperature());
                                record.setSystolicBloodPressure(appointmentDTO.getSystolicBloodPressure());
                                record.setDiastolicBloodPressure(appointmentDTO.getDiastolicBloodPressure());
                                record.setDiagnosis(appointmentDTO.getDiagnosis());
                                record.setTherapy(appointmentDTO.getTherapy());
                                record.setHealthStatus(appointmentDTO.getHealthStatus());

                                return record;
        }).orElseThrow(() -> new AppointmentNotFoundException("Appointment with id " + appointmentDTO.getId() + " was not found"));
    }


    @Override
    @Transactional(readOnly = true)
    public Appointment readAppointmentById(Long id){
        return appointmentsRepository.findById(id)
                                    .orElseThrow(() -> new AppointmentNotFoundException("Appointment with id " + id + " was not found"));
    }
}
