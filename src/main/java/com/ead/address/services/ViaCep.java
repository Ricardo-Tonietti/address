package com.ead.address.services;

import com.ead.address.dtos.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="ViaCep", url="https://viacep.com.br/ws/")
public interface ViaCep {
	
	@GetMapping(value="{zipcode}/json", produces = "application/Json")
	AddressDto getAddress(@PathVariable("zipcode") String zipcode);

}
