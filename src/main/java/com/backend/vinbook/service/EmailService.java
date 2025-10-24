package com.backend.vinbook.service;

import jakarta.mail.MessagingException;

import java.util.Map;

public interface EmailService {
    void sendDefault(String to, String subject, String content, Map<String, Object> model) throws MessagingException;

}
