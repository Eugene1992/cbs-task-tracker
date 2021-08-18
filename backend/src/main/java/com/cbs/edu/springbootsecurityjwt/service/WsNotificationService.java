package com.cbs.edu.springbootsecurityjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class WsNotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    public void notifyUser(String userId, String msg) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        template.convertAndSendToUser(userId, "/notifications", msg);
    }

}
