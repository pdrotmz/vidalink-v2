package com.vidalink.healthcare.assessment.application.usecase;

import com.vidalink.healthcare.assessment.application.dto.SubmissionFileResponse;
import com.vidalink.healthcare.assessment.domain.exception.SubmissionNotFoundByIdException;
import com.vidalink.healthcare.assessment.domain.model.Submission;
import com.vidalink.healthcare.assessment.domain.repository.SubmissionRepository;
import com.vidalink.healthcare.shared.application.port.out.FileStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetSubmissionFileUseCaseImpl implements GetSubmissionFileUseCase {

    private final SubmissionRepository submissionRepository;
    private final FileStorage fileStorage;

    @Override
    public SubmissionFileResponse execute(UUID submissionId) {

        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(
                        () -> new SubmissionNotFoundByIdException(submissionId)
                );

        InputStream inputStream =
                fileStorage.download(submission.getFile());

        return new SubmissionFileResponse(
                inputStream,
                MediaType.APPLICATION_PDF_VALUE
        );
    }
}
