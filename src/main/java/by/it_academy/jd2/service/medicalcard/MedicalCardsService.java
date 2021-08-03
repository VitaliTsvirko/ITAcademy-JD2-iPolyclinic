package by.it_academy.jd2.service.medicalcard;

import by.it_academy.jd2.core.exceptions.MedicalCardNotFoundException;
import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.repository.IMedicalCardRepository;
import by.it_academy.jd2.service.api.IMedicalCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class MedicalCardsService implements IMedicalCardService {

    private final IMedicalCardRepository medicalCardRepository;

    public MedicalCardsService(IMedicalCardRepository medicalCardRepository) {
        this.medicalCardRepository = medicalCardRepository;
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
    public Set<Appointment> getAllAppointments(Long id){
        return medicalCardRepository.findById(id)
                            .map(mc -> mc.getAppointments())
                            .orElseThrow(() -> new MedicalCardNotFoundException("Medical card was not found"));
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
