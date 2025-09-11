package com.chinese_dictation.model.dto.response;

import java.util.List;

public record UserBasicInfoResponse (
        String fullName,
        String avatarUrl,
        List<String> roles
){
}
