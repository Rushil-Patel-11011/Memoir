package com.rushil.Memoir.service;

import com.rushil.Memoir.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "weekly-sentiments", groupId = "weekly-sentiments-group")
    public void consume(SentimentData sentimentData) {
        sendMail(sentimentData);
    }

    public void sendMail(SentimentData sentimentData) {
        emailService.sendEmail(sentimentData.getEmail(), "Weekly Sentiment Report", sentimentData.getSentiment());
    }

}
