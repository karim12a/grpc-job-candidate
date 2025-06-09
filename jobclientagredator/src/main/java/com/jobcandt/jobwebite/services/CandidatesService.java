package com.jobcandt.jobwebite.services;

import com.grpc.candidates.CandidateRequest;
import com.grpc.candidates.ConfirmationResponse;
import com.grpc.candidates.candidatesServceGrpc;
import com.jobcandt.jobwebite.dto.candidates.ConfirmationDto;
import com.jobcandt.jobwebite.dto.candidates.CandidateDto;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandidatesService {
    @GrpcClient("candidate-service")
    private candidatesServceGrpc.candidatesServceBlockingStub newBlockingStub;

    @Transactional
    public ConfirmationDto save(CandidateDto dto) {
        CandidateRequest candidateRequest = CandidateRequest.newBuilder()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setJobId(dto.getJobId())
                .build();
        ConfirmationResponse confirmationResponse = this.newBlockingStub.save(candidateRequest);
        return new ConfirmationDto(confirmationResponse.getConfirmationId(), confirmationResponse.getConfirmatinonMessage());
    }
}
