package com.rushil.Memoir.Service;

import com.rushil.Memoir.service.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Disabled
    @Test
    void testSendMain(){
        emailService.sendEmail("rushil.kakadiya332@gmail.com","Testing the mail service","Hi, aap kaise hai?");
    }
}
