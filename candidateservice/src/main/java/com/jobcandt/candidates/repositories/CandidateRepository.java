package com.jobcandt.candidates.repositories;

import com.jobcandt.candidates.entities.Candidate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CandidateRepository  extends ReactiveMongoRepository<Candidate, String> {

}
