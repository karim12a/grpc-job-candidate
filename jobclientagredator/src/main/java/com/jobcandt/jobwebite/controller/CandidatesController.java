package com.jobcandt.jobwebite.controller;

import com.jobcandt.jobwebite.dto.candidates.CandidateDto;
import com.jobcandt.jobwebite.dto.candidates.ConfirmationDto;
import com.jobcandt.jobwebite.services.CandidatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController ("/candidates")
public class CandidatesController {

    @Autowired
    private CandidatesService services;
/*/ B) CRUD
    rpc save(CandidateRequest) returns (ConfirmationResponse);  //(Save)
    rpc findAll (google.protobuf.Empty) returns (CandidatesResponse); //(find all)
    rpc findById (Id) returns (Candidate);  //(find By Id)
    rpc deleteById (Id) returns (google.protobuf.Empty); //(deleteBy By Id)
    rpc deleteAll (google.protobuf.Empty) returns (google.protobuf.Empty); //(delete all)
    rpc update(CandidateToUpdateRequest) returns (Candidate); // (update)*/
    @PostMapping("/saveCandidate")
    public ConfirmationDto save( @RequestBody CandidateDto dto){
        return services.save(dto);
    }
}
