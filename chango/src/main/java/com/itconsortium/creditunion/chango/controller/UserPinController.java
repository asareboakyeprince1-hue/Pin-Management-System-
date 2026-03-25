package com.itconsortium.creditunion.chango.controller;

import com.itconsortium.creditunion.chango.dtos.CreatePinRequestDto;
import com.itconsortium.creditunion.chango.dtos.PinResponseDto;
import com.itconsortium.creditunion.chango.dtos.ResetPinRequestDto;
import com.itconsortium.creditunion.chango.dtos.UpdatePinRequestDto;
import com.itconsortium.creditunion.chango.services.UserPinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ussd")
public class UserPinController {

    @Autowired
    private UserPinService userPinService;

    @PostMapping("/create")
    public PinResponseDto create(@RequestBody CreatePinRequestDto request) {
        return userPinService.createPin(request.getMsisdn(), request.getPin());
    }

    @PostMapping("/update")
    public PinResponseDto update(@RequestBody UpdatePinRequestDto request) {
        return userPinService.changePin(
                request.getMsisdn(),
                request.getOldPin(),
                request.getNewPin()
        );
    }

    @PostMapping("/reset")
    public PinResponseDto reset(@RequestBody ResetPinRequestDto request) {
        return userPinService.resetPin(request.getMsisdn());
    }

}