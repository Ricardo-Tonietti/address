package com.ead.address.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_ADDRESS")
public class AddressModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private UUID addressId;
    @Column(nullable = false, length = 10)
    private String zipCode;
    @Column(nullable = true)
    private String address;
    @Column(nullable = true)
    private String complement;
    @Column(nullable = true)
    private String neighborhood;
    @Column(nullable = false, length = 150)
    private String city;
    @Column(nullable = false, length = 2)
    private String state;


}
