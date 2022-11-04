package com.bashka.turnlightsnotifications.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;

@Service
public class TelegramApiClient {
    private final String url;
    private final String botToken;

    private final RestTemplate restTemplate;

    public TelegramApiClient(@Value("${telegram.api-url}") String url,
                             @Value("${telegram.bot-token}") String botToken) {
        this.url = url;
        this.botToken = botToken;
        this.restTemplate = new RestTemplate();
    }

    public void sendMessage(String chatId, String message) {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("chat_id", chatId);
        map.add("text", message);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);

        try {
            restTemplate.exchange(
                    MessageFormat.format("{0}bot{1}/sendMessage", url, botToken),
                    HttpMethod.POST,
                    requestEntity,
                    String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}