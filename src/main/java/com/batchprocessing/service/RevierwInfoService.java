package com.batchprocessing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batchprocessing.repository.ReviewInfoRepository;

@Service
public class RevierwInfoService {

	@Autowired
	private final ReviewInfoRepository reviewInfoRepository;

	
	public RevierwInfoService(ReviewInfoRepository reviewInfoRepository) {
		super();
		this.reviewInfoRepository = reviewInfoRepository;
	}
	
	
	
}
