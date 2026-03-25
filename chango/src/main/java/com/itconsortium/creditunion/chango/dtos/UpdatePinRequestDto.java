package com.itconsortium.creditunion.chango.dtos;

import jdk.jfr.DataAmount;
import lombok.Data;

@Data
public class UpdatePinRequestDto {
    private String msisdn;
    private String oldPin;
    private String newPin;
}
