package com.batchprocessing.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ReviewBatchController {
	
	private final JobLauncher jobLauncher;
	private final Job job;
	
	@Autowired
	public ReviewBatchController(JobLauncher jobLauncher,Job job) {
		this.jobLauncher=jobLauncher;
		this.job=job;
	}
	
	@GetMapping("/reviewInfo")
	public void processReviewInfoData()throws Exception {//JobLauncher or Load file to database
		JobParameters jobParams=new JobParametersBuilder().addLong("Start-At", System.currentTimeMillis()).toJobParameters();
		jobLauncher.run(job, jobParams);
	}

}
