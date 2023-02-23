package com.ead.address.services.Impl;

import com.ead.address.models.AddressModel;
import com.ead.address.repositories.AddressRepository;
import com.ead.address.services.AddressService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    @Override
    public AddressModel save(AddressModel addressModel) {
        return addressRepository.save(addressModel);
    }

    @Override
    public Optional<AddressModel> findByUserId(UUID userId) {
        return Optional.ofNullable(addressRepository.getAddressByUser(userId));
    }


}
