package com.batchprocessing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.batchprocessing.entity.ReviewInfo;

@Repository
public interface ReviewInfoRepository extends JpaRepository<ReviewInfo, Long>{

}
