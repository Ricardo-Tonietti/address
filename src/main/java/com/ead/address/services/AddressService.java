package com.ead.address.services;

import com.ead.address.dtos.AddressDto;
import com.ead.address.models.AddressModel;

import java.util.Optional;
import java.util.UUID;

public interface AddressService {

    AddressModel save(AddressModel addressModel);

    Optional<AddressModel> findByUserId (UUID userId);

}
