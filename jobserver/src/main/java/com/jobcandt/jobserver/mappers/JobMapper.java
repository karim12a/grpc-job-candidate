package com.jobcandt.jobserver.mappers;

import com.jobcandt.jobserver.entities.Job;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public Job fromGrpcJob (com.grpc.job.Job source){
       return modelMapper.map(source,Job.class);
    }
    public com.grpc.job.Job fromJob(Job source){
        return modelMapper.map(source, com.grpc.job.Job.Builder.class).build();
    }
}
