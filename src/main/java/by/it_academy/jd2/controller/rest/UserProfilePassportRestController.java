package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.PassportDTO;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vitali Tsvirko
 */
@RestController
@RequestMapping("/api/user")
public class UserProfilePassportRestController {

    private final IUserService userService;

    public UserProfilePassportRestController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/passport")
    public ResponseEntity<?> createPassport(@RequestBody PassportDTO passportDTO){
        Passport passport = userService.createPassport(userService.getAuthorizedUser(), passportDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/passport", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PassportDTO> readCurrentUserPassport(){
        Passport passport = userService.getAuthorizedUser().getPassport();

        if (passport != null){
            PassportDTO passportDTO = new PassportDTO();

            passportDTO.setName(passport.getName());
            passportDTO.setSurname(passport.getSurname());
            passportDTO.setPatronymic(passport.getPatronymic());
            passportDTO.setDateOfBirth(passport.getDateOfBirth());
            passportDTO.setPlaceOfBirth(passport.getPlaceOfBirth());
            passportDTO.setNationality(passport.getNationality());
            passportDTO.setPersonalNo(passport.getPersonalNo());
            passportDTO.setPassportNo(passport.getPassportNo());
            passportDTO.setCountryOfIssue(passport.getCountryOfIssue().getShotName());
            passportDTO.setIssueDate(passport.getIssueDate());
            passportDTO.setExpirationDate(passport.getExpirationDate());

            return new ResponseEntity<>(passportDTO, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @DeleteMapping("/passport")
    public ResponseEntity<?> deletePassport(){
        userService.deletePassport(userService.getAuthorizedUser());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/passport")
    public ResponseEntity<?> updatePassport(@RequestBody PassportDTO passportDTO){
        Passport passport = userService.updatePassport(userService.getAuthorizedUser(), passportDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
