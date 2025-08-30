package com.chinese_dictation.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    private EmailSender sender;
    private List<EmailRecipient> to;
    private String htmlContent;
    private String subject;
}
