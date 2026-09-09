package com.vidalink.healthcare.assessment.application.dto;

import java.io.InputStream;

public record SubmissionFileResponse(
        InputStream inputStream,
        String contentType
) {
}
