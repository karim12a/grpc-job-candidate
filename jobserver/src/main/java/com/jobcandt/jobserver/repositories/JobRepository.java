
package com.jobcandt.jobserver.repositories;
import com.jobcandt.jobserver.entities.Job;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
public interface JobRepository extends ReactiveMongoRepository<Job, String> {
}

