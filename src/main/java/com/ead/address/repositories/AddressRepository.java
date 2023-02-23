package com.ead.address.repositories;

import com.ead.address.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, UUID>, JpaSpecificationExecutor<AddressModel> {


    @Query("SELECT a FROM AddressModel a WHERE a.userId =?1")
    AddressModel getAddressByUser(UUID userId);
}
