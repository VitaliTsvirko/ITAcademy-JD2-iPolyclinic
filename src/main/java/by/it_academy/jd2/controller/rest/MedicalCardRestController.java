package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.service.AppointmentDTO;
import by.it_academy.jd2.service.UserBasicDataDTO;
import by.it_academy.jd2.service.api.IMedicalCardService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mcard")
public class MedicalCardRestController {

    private final IMedicalCardService medicalCardService;
    private final IUserService userService;

    public MedicalCardRestController(IMedicalCardService medicalCardService, IUserService userService) {
        this.medicalCardService = medicalCardService;
        this.userService = userService;
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<UserBasicDataDTO> getMedicalCardUser(@PathVariable Long id){
        return new ResponseEntity<>(new UserBasicDataDTO(medicalCardService.getUserByMedicalCardId(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<AppointmentDTO>> readAllAppointmentById(@PathVariable Long id){
        return new ResponseEntity<>(medicalCardService.getAllAppointments(id)
                .stream()
                .map(a -> new AppointmentDTO(a))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }


}
