package com.jobcandt.jobwebite.controller;

import com.jobcandt.jobwebite.dto.jobs.JobDto;
import com.jobcandt.jobwebite.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RestController("/jobs")
public class JobsController {

    @Autowired
    private JobService service;
    @PostMapping("/saveJob")
   public JobDto save(JobDto dto) {
        this.service.save(dto);
        return dto;
    }

    @GetMapping("/findAllJob")
  public   List<JobDto> findAll() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        List<JobDto> dtos = service.findAll(latch);
        latch.await();
        return dtos;
    }
    @GetMapping("/findJobBy/{id}")
    public JobDto findById (@PathVariable String id) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        JobDto dto = service.findById(id, latch);
        latch.await();
        return dto;
    }
    @DeleteMapping("/deleteJobById/{id}")
    public  void deleteById(@PathVariable String id){
        service.deleteById(id);
    }

    @PutMapping("/updateJob/{id}")
    public JobDto update( @RequestBody JobDto jobDto, @PathVariable String id ) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        JobDto dto = service.update(jobDto, id, latch);
        latch.await();
        return  dto;
    }
}
