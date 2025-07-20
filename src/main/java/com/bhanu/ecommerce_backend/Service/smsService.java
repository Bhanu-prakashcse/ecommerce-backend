package com.bhanu.ecommerce_backend.Service;
import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class smsService {
    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public void sendSms(String toNumber, String message){
        Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(fromPhoneNumber),
                message
        ).create();
    }


}
