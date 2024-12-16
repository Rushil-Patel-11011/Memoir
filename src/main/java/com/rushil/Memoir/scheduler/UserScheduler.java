package com.rushil.Memoir.scheduler;

import com.rushil.Memoir.cache.AppCache;
import com.rushil.Memoir.entity.JournalEntry;
import com.rushil.Memoir.entity.User;
import com.rushil.Memoir.enums.Sentiment;
import com.rushil.Memoir.repository.UserRepositoryImpl;
import com.rushil.Memoir.service.EmailService;
import com.rushil.Memoir.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSAMail(){
        List<User> users = userRepository.getUserForSA();
        for (User user: users)
        {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment: sentiments)
            {
                if(sentiment!=null)
                {
                    sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+1);
                }
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment,Integer> entry: sentimentCounts.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount= entry.getValue();
                    mostFrequentSentiment=entry.getKey();
                }
            }
            if(mostFrequentSentiment!=null)
            {
                emailService.sendEmail(user.getEmail(),
                        "Sentiment for last 7 days ",
                        "the sentiment analysis based on your entries in Memoir app is here...\n dear "
                                +user.getUserName()
                                +",\n You were "
                                +mostFrequentSentiment.toString()
                                +" last week.\n TAKE CARE");
            }
        }
    }

    @Scheduled(cron="0 */10 * ? * *")
    public void clearAppCache()
    {
        appCache.init();
    }
}