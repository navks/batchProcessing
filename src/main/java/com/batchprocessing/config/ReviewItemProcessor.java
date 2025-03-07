package com.batchprocessing.config;

import org.springframework.batch.item.ItemProcessor;

import com.batchprocessing.entity.ReviewInfo;
import org.springframework.context.annotation.Bean;

public class ReviewItemProcessor implements ItemProcessor<ReviewInfo, ReviewInfo> {

	@Override
	public ReviewInfo process(ReviewInfo item) throws Exception {
		// TODO Auto-generated method stub
		//here we have to write process logic 
//		if(item.getRatings().equals("5")) {
//			return item;
//		}
//		return null;// like this we have write the logic , that if this happen then it will take the data in the itemWriter
		return item;
	}
	

}
