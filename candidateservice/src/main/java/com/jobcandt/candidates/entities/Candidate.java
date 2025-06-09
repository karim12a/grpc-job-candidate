package com.jobcandt.candidates.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Candidate {

    @Id
    private String Id;
    private String firstName;
    private String lastName;
    private String jobId;
}
