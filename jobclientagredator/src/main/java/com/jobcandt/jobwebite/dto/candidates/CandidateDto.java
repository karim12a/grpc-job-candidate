package com.jobcandt.jobwebite.dto.candidates;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {
    private  String firstName;
    private  String lastName;
    private  String jobId;
}
