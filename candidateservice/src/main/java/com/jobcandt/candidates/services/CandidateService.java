package com.jobcandt.candidates.services;

import com.grpc.candidates.*;
import com.grpc.common.Id;
import com.google.protobuf.Empty;
import com.jobcandt.candidates.entities.Confirmation;
import com.jobcandt.candidates.mappers.ConfirmationMapper;
import com.jobcandt.candidates.repositories.CandidateRepository;
import com.jobcandt.candidates.repositories.ConfirmationsRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class CandidateService extends candidatesServceGrpc.candidatesServceImplBase {
    @Autowired
    CandidateRepository candidateRepository;
    @Autowired
    ConfirmationsRepository confirmationsRepository;

    @Autowired
    ConfirmationMapper confirmationMapper;

    @Override
    public void save(CandidateRequest request, StreamObserver<ConfirmationResponse> responseObserver) {
        com.jobcandt.candidates.entities.Candidate candidate = new com.jobcandt.candidates.entities.Candidate();
        candidate.setLastName(request.getLastName());
        candidate.setFirstName(request.getFirstName());
        candidate.setJobId(request.getJobId());
        candidateRepository.save(candidate).block();
        // Build the  confirmation response
        Confirmation confirmation = new Confirmation();
        Confirmation savedConfirmation = confirmationsRepository.save(confirmation).block();
        confirmation.setConfirmationMessage("Thank " + candidate.getFirstName() +
                " your confirmation Id is: " + confirmation.getConfirmationId() + ", please keep this information"
        );

       // ConfirmationResponse confirmationResponse = confirmationMapper.fromConfirmation(savedConfirmation);

        ConfirmationResponse confirmationResponse = ConfirmationResponse.newBuilder()
                .setConfirmationId(savedConfirmation.getConfirmationId())
                .setConfirmatinonMessage(savedConfirmation.getConfirmationMessage())
                .build();


        ConfirmationResponse response = ConfirmationResponse.newBuilder()
                .setConfirmationId(confirmationResponse.getConfirmationId())
                .setConfirmatinonMessage(confirmationResponse.getConfirmatinonMessage())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(Empty request, StreamObserver<CandidatesResponse> responseObserver) {
        super.findAll(request, responseObserver);
        // TODO
    }

    @Override
    public void findById(Id request, StreamObserver<Candidate> responseObserver) {
        super.findById(request, responseObserver);
        // TODO
    }

    @Override
    public void deleteById(Id request, StreamObserver<Empty> responseObserver) {
        super.deleteById(request, responseObserver);
        // TODO
    }

    @Override
    public void update(CandidateToUpdateRequest request, StreamObserver<Candidate> responseObserver) {
        super.update(request, responseObserver);
        // TODO
    }
}
