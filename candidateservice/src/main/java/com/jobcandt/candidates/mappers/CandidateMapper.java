package com.jobcandt.candidates.mappers;

import com.grpc.candidates.CandidatesResponse;
import com.jobcandt.candidates.entities.Candidate;
import org.modelmapper.ModelMapper;

public class CandidateMapper {

    private ModelMapper modelMapper = new ModelMapper();

    public Candidate fromGrpcCandidate(CandidatesResponse source){
        return  modelMapper.map(source,Candidate.class );
    }
    public  CandidatesResponse fromCandidate(Candidate source){
        return modelMapper.map(source, CandidatesResponse.class);
    }
}
