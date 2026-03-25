package com.itconsortium.creditunion.chango.dtos;

import lombok.Data;

@Data
public class CreatePinRequestDto {
    private String msisdn;
    private String pin;
}
