package com.cinemaapp.backend.service.impl;

import com.cinemaapp.backend.service.UploadcareService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UploadcareServiceImpl implements UploadcareService {

    @Value("${uploadcare.secret.key}")
    private String uploadcareSecretKey;

    @Value("${uploadcare.public.key}")
    private String uploadcarePublicKey;

    @Override
    public boolean verifyPhotoUrl(String photoUrl) {
        String fileUuid = extractUuidFromUrl(photoUrl);
        String apiUrl = "/files/" + fileUuid + "/";

        try {
            // Create headers with the signed Authorization
            HttpHeaders headers = createSecureHeaders(apiUrl, "GET");
            HttpEntity<String> request = new HttpEntity<>(null, headers);
            RestTemplate restTemplate = new RestTemplate();

            // Send the request
            restTemplate.exchange("https://api.uploadcare.com" + apiUrl, HttpMethod.GET, request, String.class);
            return true; // File is valid
        } catch (HttpClientErrorException.Unauthorized e) {
            throw new IllegalArgumentException("Unauthorized: Invalid Uploadcare credentials", e);
        } catch (Exception e) {
            throw new IllegalStateException("Error verifying photo URL", e);
        }
    }

    private HttpHeaders createSecureHeaders(String uri, String method) throws Exception {
        // RFC2822 Date Header
        String dateHeader = java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME
                .format(java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC));

        // Create the sign string
        String contentMd5 = ""; // For GET requests
        String contentType = ""; // For GET requests
        String signString = String.join("\n", method, contentMd5, contentType, dateHeader, uri);

        // Generate HMAC-SHA1 signature
        SecretKeySpec keySpec = new SecretKeySpec(uploadcareSecretKey.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(keySpec);
        String signature = Base64.getEncoder().encodeToString(mac.doFinal(signString.getBytes()));

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.uploadcare-v0.7+json");
        headers.set("Date", dateHeader);
        headers.set("Authorization", "Uploadcare " + uploadcarePublicKey + ":" + signature);

        return headers;
    }

    private String extractUuidFromUrl(String url) {
        // Extract UUID from the URL
        Pattern pattern = Pattern.compile("ucarecdn\\.com/([a-zA-Z0-9-]+)/");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Invalid Uploadcare URL: " + url);
    }

}
