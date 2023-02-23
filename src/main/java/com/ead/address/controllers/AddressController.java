package com.ead.address.controllers;

import com.ead.address.dtos.AddressDto;
import com.ead.address.dtos.UserDto;
import com.ead.address.models.UserModel;
import com.ead.address.services.UserService;
import com.ead.address.services.ViaCep;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/address")
public class AddressController {

    private final ViaCep viaCep;
    private final UserService userService;
    public AddressController(ViaCep viaCep, UserService userService) {
        this.viaCep = viaCep;
        this.userService = userService;
    }

     @PostMapping ("/zipcode/{zipcode}")
     public ResponseEntity<Object> getZipCode(@PathVariable("zipcode") String zipcode ,
                                             @RequestBody @Valid UserDto userDto) {

        Optional<UserModel> userModelOptional = this.userService.findById(userDto.getUserId());
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user Not Found!");
        }else{
            System.out.println(zipcode);
            AddressDto dto = viaCep.getAddress(zipcode);
            System.out.println(dto);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
    }
}
