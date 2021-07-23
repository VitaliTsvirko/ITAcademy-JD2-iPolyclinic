package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.AddressDTO;
import by.it_academy.jd2.service.PassportDTO;
import by.it_academy.jd2.service.UserBasicDataDTO;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vitali Tsvirko
 */
@RestController
@RequestMapping("/api/manager/user")
public class UserRestController {


    private final IUserService userService;

    public UserRestController(IUserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{id}/basic", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserBasicDataDTO> readUserBasicDataById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(new UserBasicDataDTO(userService.getUserById(id)), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping("/{id}/confirm/passport")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserBasicDataDTO> confirmPassportData(@PathVariable Long id){
        //check user not found
        try{
            UserBasicDataDTO userBasicDataDTO = new UserBasicDataDTO(userService.confirmPassportDataByUserId(id));
            return new ResponseEntity<>(userBasicDataDTO, HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @GetMapping(value = "/{id}/address", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AddressDTO> readUserAddressByUserId(@PathVariable Long id){
        try{
            return new ResponseEntity<>(new AddressDTO(userService.getUserById(id).getAddress()), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}/passport", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PassportDTO> readUserPassportByUserId(@PathVariable Long id){
        try{
            return new ResponseEntity<>(new PassportDTO(userService.getUserById(id).getPassport()), HttpStatus.OK);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
