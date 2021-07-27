package by.it_academy.jd2.controller.rest;

import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.service.dto.AddressDTO;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Vitali Tsvirko
 * https://mkyong.com/spring-boot/spring-rest-spring-security-example/
 * Spring HATEOAS
 */
@RestController
@RequestMapping("/api/user")
public class UserProfileAddressRestController {

    //private static final String API_FULL_ACCESS_ROLES = String.format("hasAnyAuthority(%s,%s,%s)", UserRoles.ROOT, UserRoles.DOCTOR, UserRoles.ADMIN);

    private final IUserService userService;

    public UserProfileAddressRestController(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/address")
    public ResponseEntity<?> createAddress(@RequestBody AddressDTO addressDTO){
        Address address1 = userService.createAddress(userService.getAuthorizedUser(), addressDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> readCurrentUserAddress(){
        Address address = userService.getAuthorizedUser().getAddress();

        if (address != null){
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setCountryCode(address.getCountry().getCode());
            addressDTO.setCountryName(address.getCountry().getShotName());
            addressDTO.setCity(address.getCity());
            addressDTO.setStreet(address.getStreet());
            addressDTO.setHomeNo(address.getHomeNo());
            addressDTO.setCorpsNo(address.getCorpsNo());
            addressDTO.setFlatNo(address.getFlatNo());

            return new ResponseEntity<>(addressDTO, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @DeleteMapping("/address")
    public ResponseEntity<?> deleteAddress(){
        userService.deleteAddress(userService.getAuthorizedUser());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/address")
    public ResponseEntity<?> updateAddress(@RequestBody AddressDTO addressDTO){
        Address address1 = userService.updateAddress(userService.getAuthorizedUser(), addressDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
