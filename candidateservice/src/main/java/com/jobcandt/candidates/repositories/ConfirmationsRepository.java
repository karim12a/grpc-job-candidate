package com.jobcandt.candidates.repositories;

import com.jobcandt.candidates.entities.Confirmation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ConfirmationsRepository extends ReactiveMongoRepository<Confirmation, String> {
}
