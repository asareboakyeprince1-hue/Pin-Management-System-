package com.itconsortium.creditunion.chango.services;

import com.itconsortium.creditunion.chango.dtos.PinResponseDto;
import com.itconsortium.creditunion.chango.exception.PinException;
import com.itconsortium.creditunion.chango.exception.UserNotFoundException;
import com.itconsortium.creditunion.chango.models.User;
import com.itconsortium.creditunion.chango.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPinService {

    @Autowired
    private UserRepository userRepository;

    // Create Pin
    public PinResponseDto createPin(String msisdn, String pin) {
        User user = new User();
        user.setMsisdn(msisdn);

        if (user.getPin() != null) {
            throw new PinException("Invalid pin");
        }
        user.setPin(hashPin(pin));
        userRepository.save(user);

        return new PinResponseDto("Pin has been created");
    }


    //Change Pin
    public PinResponseDto changePin(String msisdn, String oldPin, String newPin) {
        User user = userRepository.findByMsisdn(msisdn)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean isValid = isPinEqual(oldPin, user.getPin());

        if (!isValid) {
            throw new PinException("New pin does not match old pin");
        }

        user.setPin(hashPin(newPin));
        user.setUpdated(true);
        userRepository.save(user);

        return new PinResponseDto("Pin has been updated");
    }

    // Reset Pin
    public PinResponseDto resetPin(String msisdn) {
        User user = userRepository.findByMsisdn(msisdn)
                .orElseThrow(() -> new PinException("User not found"));

        String tempPin = generateTempPin();

        user.setPin(hashPin(tempPin));
        userRepository.save(user);

        sendSms(msisdn, tempPin);
        return new PinResponseDto("Pin has been reset");
    }

    // Generate 4-digit PIN
    private String generateTempPin() {
        Random random = new Random();
        int pin = 1000 + random.nextInt(9000);
        return String.valueOf(pin);
    }

    private boolean isPinEqual(String enteredPin, String storedHash){
        enteredPin = hashPin(enteredPin);
        return storedHash.equals(enteredPin);
    }

    private String hashPin(String pin){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] pinBytes = digest.digest(pin.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexPin = new StringBuilder();

            for(byte b : pinBytes){
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexPin.append('0');
                }
                hexPin.append(hex);
            }
            return hexPin.toString();
        } catch(NoSuchAlgorithmException e){
            throw new PinException("Problem setting pin");
        }
    }

    private void sendSms(String msisdn, String tempPin) {
        // Replace with real SMS provider
        System.out.println("Sending SMS to " + msisdn + " Temp PIN: " + tempPin);
    }
}
