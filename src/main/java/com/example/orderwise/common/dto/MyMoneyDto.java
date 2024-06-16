package com.example.orderwise.common.dto;

import com.example.orderwise.entity.enums.EtatDemande;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyMoneyDto implements Serializable {
    private Long id;
    private Date dateDeDemande;
    private Double montant;
    private EtatDemande etatDemande;

    private UserDto user;
}
