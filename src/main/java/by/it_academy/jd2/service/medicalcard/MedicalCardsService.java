package by.it_academy.jd2.service.medicalcard;

import by.it_academy.jd2.core.exceptions.MedicalCardNotFoundException;
import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.repository.IAppointmentsRepository;
import by.it_academy.jd2.repository.IMedicalCardRepository;
import by.it_academy.jd2.service.api.IMedicalCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicalCardsService implements IMedicalCardService {

    private final IMedicalCardRepository medicalCardRepository;

    private final IAppointmentsRepository appointmentsRepository;

    public MedicalCardsService(IMedicalCardRepository medicalCardRepository, IAppointmentsRepository appointmentsRepository) {
        this.medicalCardRepository = medicalCardRepository;
        this.appointmentsRepository = appointmentsRepository;
    }

    public MedicalCard getMedicalCardByUserId(Long userId){
        return medicalCardRepository.findByUserId(userId)
                .orElseThrow(() -> new MedicalCardNotFoundException("Medical card was not found"));
    }

    @Override
    public User getUserByMedicalCardId(Long id){
        return medicalCardRepository.findById(id).map(mc -> mc.getUser())
                .orElseThrow(() -> new MedicalCardNotFoundException("Medical card was not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Appointment> getAllAppointments(Long id){
        return appointmentsRepository.findAllAppointmentsByMedicalCardIdOrderByDateTimeDesc(id);
    }

    @Override
    public MedicalCard getMedicalCardById(Long id) {
        return medicalCardRepository.findById(id)
                .orElseThrow(() -> new MedicalCardNotFoundException("Medical card was not found"));
    }


    public Integer getAppointmentCountByMedicalcardId(Long id){
        return medicalCardRepository.findById(id).map(md -> md.getAppointments().size())
                .orElseThrow(() -> new MedicalCardNotFoundException("Medical card was not found"));
    }


}
