package com.vidalink.healthcare.assessment.application.usecase;

import com.vidalink.healthcare.assessment.application.dto.SubmissionFileResponse;

import java.util.UUID;

public interface GetSubmissionFileUseCase {

    SubmissionFileResponse execute(UUID submissionId);
}
