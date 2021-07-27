package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.service.AppointmentDTO;
import by.it_academy.jd2.service.api.IAppointmentsService;
import by.it_academy.jd2.service.api.IMedicalCardService;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentsRestController {

    private final IAppointmentsService appointmentsService;
    private final IUserService userService;

    public AppointmentsRestController(IAppointmentsService appointmentsService, IUserService userService) {
        this.appointmentsService = appointmentsService;
        this.userService = userService;
    }

    @PostMapping("/{id}")
    //@PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<AppointmentDTO> createAppointment(@PathVariable Long id){
        return new ResponseEntity<>(new AppointmentDTO(appointmentsService.createAppointment(userService.getAuthorizedUser(), id)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<AppointmentDTO> readAppointmentById(@PathVariable Long id){
        return new ResponseEntity<>(new AppointmentDTO(appointmentsService.readAppointmentById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('DOCTOR')")
    public ResponseEntity<AppointmentDTO> updateAppointmentById(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO){
        return new ResponseEntity<>(new AppointmentDTO(appointmentsService.updateAppointment(appointmentDTO)), HttpStatus.OK);
    }

}