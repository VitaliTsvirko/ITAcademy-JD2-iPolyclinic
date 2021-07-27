package by.it_academy.jd2.service;

import by.it_academy.jd2.core.exceptions.MedicalCardNotFoundException;
import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.repository.IMedicalCardRepository;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MedicalCardsService {

    private final IMedicalCardRepository medicalCardRepository;


    public MedicalCardsService(IMedicalCardRepository medicalCardRepository, IUserService userService) {
        this.medicalCardRepository = medicalCardRepository;
    }

    public MedicalCard getMedicalCardByUserId(Long userId){
        return medicalCardRepository.findByUserId(userId)
                .orElseThrow(() -> new MedicalCardNotFoundException("Medical card was not found"));
    }


    public void addAppointment(Long userId, Appointment appointments){
        MedicalCard medicalCard = getMedicalCardByUserId(userId);
        medicalCard.addAppointment(appointments);
    }




}
