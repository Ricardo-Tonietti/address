package com.ead.address.dtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class AddressDto {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

}
