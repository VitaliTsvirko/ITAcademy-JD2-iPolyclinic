package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.service.dto.AppointmentDTO;
import by.it_academy.jd2.service.api.IAppointmentsService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentsRestController {

    private final IAppointmentsService appointmentsService;
    private final IUserService userService;

    public AppointmentsRestController(IAppointmentsService appointmentsService, IUserService userService) {
        this.appointmentsService = appointmentsService;
        this.userService = userService;
    }

    @PostMapping("/{medicalCardId}")
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<AppointmentDTO> createAppointment(@PathVariable Long medicalCardId){
        return new ResponseEntity<>(new AppointmentDTO(appointmentsService.createAppointment(userService.getAuthorizedUser(), medicalCardId)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> readAppointmentById(@PathVariable Long id){
        return new ResponseEntity<>(new AppointmentDTO(appointmentsService.readAppointmentById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<AppointmentDTO> updateAppointmentById(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO){
        return new ResponseEntity<>(new AppointmentDTO(appointmentsService.updateAppointment(appointmentDTO)), HttpStatus.OK);
    }

}
