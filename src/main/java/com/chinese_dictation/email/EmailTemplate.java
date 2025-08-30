package com.chinese_dictation.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailTemplate {
    private final TemplateEngine templateEngine;

    public String generateActivationEmail(String code, String name){
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", name);
        variables.put("code", code);
        variables.put("year", LocalDateTime.now());

        return processTemplate("activation-email", variables);
    }

    public String processTemplate(String templateName, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(templateName, context);
    }
}
