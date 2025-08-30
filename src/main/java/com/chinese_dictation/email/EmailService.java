package com.chinese_dictation.email;

import com.chinese_dictation.repository.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Value("${application.email.brevo.api-key}")
    private String apiKey;
    @Value("${application.email.brevo.sender-email}")
    private String senderEnail;
    @Value("${application.email.brevo.sender-name}")
    private String senderName;
//    private final BlockingQueue<EmailRequest> queue;
    private final EmailTemplate emailTemplate;
    private final EmailClient emailClient;

    public void sendActivationEmail(String code, String fullName, String email) {
        String htmlContent = emailTemplate.generateActivationEmail(code, fullName);
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(EmailSender.builder()
                        .email(senderEnail)
                        .name(senderName)
                        .build())
                .to(List.of(
                        EmailRecipient.builder()
                                .email(email)
                                .name(fullName)
                                .build()
                ))
                .htmlContent(htmlContent)
                .subject("[Chinese-Dictaion] Mã xác thực tạo tài khoản")
                .build();
        emailClient.send(apiKey, emailRequest);
    }
}
