package com.bhanu.ecommerce_backend.config;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter

public class TwilioConfigForSMS {

    @Value("${twilio.account.sid}")
    private String accountSID;
    @Value("${twilio.auth.token}")
    private String authToken;

    @PostConstruct
    public void init(){
        Twilio.init(accountSID, authToken);
    }
}
