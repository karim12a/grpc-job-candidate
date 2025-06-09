package com.jobcandt.jobwebite.services;

import com.grpc.common.Id;
import com.grpc.job.*;
import com.google.protobuf.Empty;
import com.jobcandt.jobwebite.dto.jobs.JobDto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class JobService {

    @GrpcClient("job-service")
    private jobServceGrpc.jobServceStub newBlockingStub;
    @Transactional
    public JobDto save(JobDto dto) {
        JobToSaveRequest jobToSaveRequest = JobToSaveRequest.newBuilder()
                .setJobTitle(dto.getJobTitle())
                .setDescription(dto.getDescription())
                .build();

        JobResponse jobResponse = JobResponse.newBuilder().build();
        this.newBlockingStub.save(jobToSaveRequest, new StreamObserver<JobResponse>() {
            @Override
            public void onNext(JobResponse jobResponse) {
                dto.setJobTitle(jobResponse.getJob().getJobTitle());
                dto.setDescription(jobResponse.getJob().getDescription());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        });
        return dto;
    }
    @Transactional
    public List<JobDto> findAll(CountDownLatch latch) {
        List<JobDto> dtos = new ArrayList<>();
        this.newBlockingStub.findAll(null, new StreamObserver<allFondedJobsResponse>() {
            @Override
            public void onNext(allFondedJobsResponse allFondedJobsResponse) {
                allFondedJobsResponse.getJobsList().forEach(job -> {
                    JobDto dto = new JobDto();
                    dto.setJobTitle(job.getJobTitle());
                    dto.setDescription(job.getDescription());
                    dtos.add(dto);
                    System.out.println("DTO: " + dtos);
                });
                latch.countDown();
            }

            @Override
            public void onError(Throwable throwable) {
                latch.countDown();
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });
        return dtos;
    }
    @Transactional
    public JobDto findById(String id, CountDownLatch latch) {
        JobDto dto = new JobDto();
        Id grpcId = Id.newBuilder().setId(id).build();
        StreamObserver<JobResponse> streamObserver = new StreamObserver<>() {
            @Override
            public void onNext(JobResponse jobResponse) {
                dto.setJobTitle(jobResponse.getJob().getJobTitle());
                dto.setDescription(jobResponse.getJob().getDescription());
                latch.countDown();
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        };
        newBlockingStub.findById(grpcId, streamObserver);
        return dto;
    }
    @Transactional
    public void deleteById(String id) {
        StreamObserver<Empty> streamObserver = new StreamObserver<>() {
            @Override
            public void onNext(Empty empty) {
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
        newBlockingStub.deleteById(Id.newBuilder().setId(id).build(), streamObserver);
    }

    @Transactional
    public JobDto update(JobDto jobDto, String jobId, CountDownLatch latch) {

         JobToUpdateRequest updateRequest = JobToUpdateRequest.newBuilder()
                .setJob(Job.newBuilder()
                        .setId(jobId)
                        .setJobTitle(jobDto.getDescription())
                        .setDescription(jobDto.getDescription())
                        .build())
                .build();
        newBlockingStub.update(updateRequest, new StreamObserver<JobResponse>() {
            @Override
            public void onNext(JobResponse jobResponse) {
                jobDto.setJobTitle(jobResponse.getJob().getJobTitle());
                jobDto.setDescription(jobResponse.getJob().getDescription());
                latch.countDown();

            }
            @Override
            public void onError(Throwable throwable) {
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });
        return jobDto;
    }
}
