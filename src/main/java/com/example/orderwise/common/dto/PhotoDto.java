package com.example.orderwise.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoDto implements Serializable {
    private Long id;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
}
