package com.ead.address.controllers;

import com.ead.address.dtos.AddressDto;
import com.ead.address.dtos.UserDto;
import com.ead.address.models.AddressModel;
import com.ead.address.models.UserModel;
import com.ead.address.services.AddressService;
import com.ead.address.services.UserService;
import com.ead.address.services.ViaCep;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
public class AddressController {

    private final ViaCep viaCep;
    private final UserService userService;
    private final AddressService addressService;
    public AddressController(ViaCep viaCep, UserService userService, AddressService addressService) {
        this.viaCep = viaCep;
        this.userService = userService;
        this.addressService = addressService;
    }

     @PostMapping ("/zipcode/{zipcode}")
     public ResponseEntity<Object> getZipCode(@PathVariable("zipcode") String zipcode ,
                                             @RequestBody @Valid UserDto userDto) {

         log.debug("POST saveAddress userDTO {}", userDto.toString());
         Optional<UserModel> userModelOptional = this.userService.findById(userDto.getUserId());
         if (!userModelOptional.isPresent()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user Not Found!");
         } else {
             AddressDto dto = viaCep.getAddress(zipcode);
             System.out.println(dto);
             var addressModel = new AddressModel();

             addressModel.setZipCode(dto.getCep());
             addressModel.setAddress(dto.getLogradouro());
             addressModel.setComplement(dto.getComplemento());
             addressModel.setNeighborhood(dto.getBairro());
             addressModel.setCity(dto.getLocalidade());
             addressModel.setState(dto.getUf());
             addressModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
             addressModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
             addressModel.setUserId(userDto.getUserId());
             this.addressService.save(addressModel);

             log.debug("POST saveAddress saved {}", addressModel.toString());
             log.info("Address saved sucessfully userId {}", userDto.getUserId().toString());
             return ResponseEntity.status(HttpStatus.CREATED).body(dto);
         }

     }
         @GetMapping("/{userId}/user")
         public ResponseEntity<Object> getAddressByUser(@PathVariable(value = "userId") UUID userId) {
             Optional<AddressModel> addressModelOptional = addressService.findByUserId(userId);
             if(!addressModelOptional.isPresent()){
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address Not Found");
             }
             return ResponseEntity.status(HttpStatus.OK).body(addressModelOptional);
         }
    }

