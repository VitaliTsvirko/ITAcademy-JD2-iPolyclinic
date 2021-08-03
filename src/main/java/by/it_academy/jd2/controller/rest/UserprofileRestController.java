package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.service.dto.AddressDTO;
import by.it_academy.jd2.service.dto.ApiErrorDTO;
import by.it_academy.jd2.service.dto.PassportDTO;
import by.it_academy.jd2.service.dto.UserBasicDataDTO;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vitali Tsvirko
 */
@RestController
@RequestMapping("/api/userprofile/")
public class UserprofileRestController {


    private final IUserService userService;

    public UserprofileRestController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{id}/basic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserBasicDataDTO> readUserBasicDataById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(new UserBasicDataDTO(userService.getUserById(id)), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<Object> createAddressByUserId(@RequestBody AddressDTO addressDTO, @PathVariable Long id) {
        try{
            return new ResponseEntity<>(new AddressDTO(userService.createAddress(userService.getUserById(id), addressDTO)), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(new ApiErrorDTO("Не заполнено одно из обязательных полей"), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AddressDTO> readUserAddressByUserId(@PathVariable Long id){
        try{
            Address address = userService.getUserById(id).getAddress();
            if (address == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(new AddressDTO(address), HttpStatus.OK);
            }
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping("/{id}/address")
    public ResponseEntity<Object> updateAddressByUserId(@RequestBody AddressDTO addressDTO, @PathVariable Long id) {
        try{
            return new ResponseEntity<>(new AddressDTO(userService.updateAddress(userService.getUserById(id), addressDTO)), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(new ApiErrorDTO("Не заполнено одно из обязательных полей"), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}/address")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id){
        try{
            userService.deleteAddress(userService.getUserById(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }






    @GetMapping(value = "/{id}/passport", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PassportDTO> readUserPassportByUserId(@PathVariable Long id){
        try{
            Passport passport = userService.getUserById(id).getPassport();
            if (passport == null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(new PassportDTO(passport), HttpStatus.OK);
            }
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/{id}/passport")
    public ResponseEntity<Object> createPassportByUserId(@RequestBody PassportDTO passportDTO, @PathVariable Long id){
        try{
            return new ResponseEntity<>(new PassportDTO(userService.createPassport(userService.getUserById(id), passportDTO)), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(new ApiErrorDTO("Не заполнено одно из обязательных полей"), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/{id}/passport")
    public ResponseEntity<Object> updatePassportByUserId(@RequestBody PassportDTO passportDTO, @PathVariable Long id){
        try{
            return new ResponseEntity<>(new PassportDTO(userService.updatePassport(userService.getUserById(id), passportDTO)), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (DataIntegrityViolationException e){
            return new ResponseEntity<>(new ApiErrorDTO("Не заполнено одно из обязательных полей"), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}/passport")
    public ResponseEntity<?> deletePassport(@PathVariable Long id){
        try{
            userService.deletePassport(userService.getUserById(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/{id}/passport/confirm")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR')")
    public ResponseEntity<UserBasicDataDTO> confirmPassportData(@PathVariable Long id){
        try{
            UserBasicDataDTO userBasicDataDTO = new UserBasicDataDTO(userService.confirmPassportDataByUserId(id));
            return new ResponseEntity<>(userBasicDataDTO, HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }



}
