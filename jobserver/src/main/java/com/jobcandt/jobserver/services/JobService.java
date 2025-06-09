package com.jobcandt.jobserver.services;

import com.grpc.common.Id;
import com.grpc.job.*;
import com.google.protobuf.Empty;
import com.jobcandt.jobserver.mappers.JobMapper;
import com.jobcandt.jobserver.repositories.JobRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class JobService extends jobServceGrpc.jobServceImplBase {
    @Autowired
    private JobRepository repository;
    @Autowired
    private JobMapper mapper;

    @Override
    public void save(JobToSaveRequest request, StreamObserver<JobResponse> responseObserver) { // OK

        com.jobcandt.jobserver.entities.Job job = new com.jobcandt.jobserver.entities.Job();
        job.setJobTitle(request.getJobTitle());
        job.setDescription(request.getDescription());
        com.jobcandt.jobserver.entities.Job savedJob = repository.save(job).block();

        Job grpcResponseJob = mapper.fromJob(savedJob);

        JobResponse jobResponse = JobResponse.newBuilder()
                .setJob(grpcResponseJob)
                .build();
        responseObserver.onNext(jobResponse);
        responseObserver.onCompleted();

    }
    @Override
    public void findById(Id request, StreamObserver<JobResponse> responseObserver) { //OK
        com.jobcandt.jobserver.entities.Job foundedjob = repository.findById(request.getId()).block();
        Job grpcResponseJob = mapper.fromJob(foundedjob);

        JobResponse jobResponse = JobResponse.newBuilder()
                .setJob(grpcResponseJob)
                .build();
        responseObserver.onNext(jobResponse);
        responseObserver.onCompleted();

    }

    @Override
    public void findAll(Empty request, StreamObserver<allFondedJobsResponse> responseObserver) {
        List<com.jobcandt.jobserver.entities.Job> jobList = repository.findAll().collectList().block();
        allFondedJobsResponse.Builder jobbuilder = allFondedJobsResponse.newBuilder();
        List<Job> jobs = jobList.stream().map(mapper::fromJob).collect(Collectors.toList());
        jobbuilder.addAllJobs(jobs);
        responseObserver.onNext(jobbuilder.build());
        responseObserver.onCompleted();

    }

    @Override
    public void deleteById(Id request, StreamObserver<Empty> responseObserver) { //  OK
        repository.deleteById(request.getId()).block();
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }


    @Override
    public void update(JobToUpdateRequest request, StreamObserver<JobResponse> responseObserver) { // OK

        com.jobcandt.jobserver.entities.Job originalJob = repository.findById(request.getJob().getId()).block();

        originalJob.setJobTitle(request.getJob().getJobTitle());
        originalJob.setDescription(request.getJob().getDescription());

        com.jobcandt.jobserver.entities.Job savedJob = repository.save(originalJob).block();
        Job grpcResponseJob = mapper.fromJob(savedJob);

        JobResponse jobResponse = JobResponse.newBuilder()
                .setJob(grpcResponseJob)
                .build();
        responseObserver.onNext(jobResponse);
        responseObserver.onCompleted();
    }
}
