package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.service.dto.AppointmentDTO;
import by.it_academy.jd2.service.dto.UserBasicDataDTO;
import by.it_academy.jd2.service.api.IMedicalCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mcard")
public class MedicalCardRestController {

    private final IMedicalCardService medicalCardService;

    public MedicalCardRestController(IMedicalCardService medicalCardService) {
        this.medicalCardService = medicalCardService;
    }

    @GetMapping("/{id}/user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR') or @securityAccessHandler.isAuthenticationUserIdEqualsRequestUserMedicalId(#id)")
    public ResponseEntity<UserBasicDataDTO> getMedicalCardUser(@PathVariable Long id){
        return new ResponseEntity<>(new UserBasicDataDTO(medicalCardService.getUserByMedicalCardId(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/appointments")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR') or @securityAccessHandler.isAuthenticationUserIdEqualsRequestUserMedicalId(#id)")
    public ResponseEntity<List<AppointmentDTO>> readAllAppointmentById(@PathVariable Long id){
        return new ResponseEntity<>(medicalCardService.getAllAppointments(id)
                .stream()
                .map(a -> new AppointmentDTO(a))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }


}
