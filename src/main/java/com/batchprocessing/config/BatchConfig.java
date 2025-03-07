package com.batchprocessing.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import com.batchprocessing.entity.ReviewInfo;
import com.batchprocessing.repository.ReviewInfoRepository;


@Configuration
@EnableBatchProcessing

public class BatchConfig{
	
	
	 @Autowired
    private final ReviewInfoRepository reviewInfoRepository;

 
    public BatchConfig (ReviewInfoRepository reviewInfoRepository) {
		this.reviewInfoRepository = reviewInfoRepository;
    }
	
	//create Reader
	@Bean
	public FlatFileItemReader<ReviewInfo> reviewReader(){
		FlatFileItemReader<ReviewInfo> itemReader=new FlatFileItemReader<>();//create the object
		itemReader.setResource(new FileSystemResource("src/main/resources/generated_data.csv"));// give where is my source file
		itemReader.setName("generated_data");//given some name
		itemReader.setLinesToSkip(1);// skip the first line
		itemReader.setLineMapper(lineMapper());//here we have to tell that each line belongs to 1 review object
		return itemReader;
	}

	private LineMapper<ReviewInfo> lineMapper() {
		// TODO Auto-generated method stub
		 DefaultLineMapper<ReviewInfo> lineMapper= new DefaultLineMapper<>();
		 //for CSV file, beacuse CSV(common separated value)
		 DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();//here telling that my data is separated by comma  
		 lineTokenizer.setDelimiter(",");
		 lineTokenizer.setStrict(false);//if colunm is not available,we will consider as null value
		 lineTokenizer.setNames("username","ratings","text","email","phoneNumber");//it should be in order
		 //now whatever the data is available , take the data and convert into bean object
		 BeanWrapperFieldSetMapper<ReviewInfo> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
		 fieldSetMapper.setTargetType(ReviewInfo.class); 
		 lineMapper.setLineTokenizer(lineTokenizer);
		 lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	//create processor
	@Bean
	public ReviewItemProcessor reviewItemProcessor() {
		return new ReviewItemProcessor();	
		}
	
	//create writer
	@Bean
	public RepositoryItemWriter<ReviewInfo> reviewWriter(){// write the data into the database
		RepositoryItemWriter<ReviewInfo> repositoryItemWriter=new RepositoryItemWriter<>();
		repositoryItemWriter.setRepository(reviewInfoRepository);
		repositoryItemWriter.setMethodName("save");
		return repositoryItemWriter;
	}
	//create step
	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	    return new StepBuilder("step1", jobRepository)
	            .<ReviewInfo, ReviewInfo>chunk(10, transactionManager)
	            .reader(reviewReader())
	            .processor(reviewItemProcessor())
	            .writer(reviewWriter())
	            .build();
	


		//here you can create multiple steps also.
}
	
	//create job 
	@Bean
	public Job job(JobRepository jobRepository, Step step1) {
	    return new JobBuilder("customer-job", jobRepository)
	            .flow(step1)
	            .end()
	            .build();
	}
//	// Create Job
//	@Bean
//	public Job job(JobRepository jobRepository) {
//	    return new jobBuilderFactory("customer-job", jobRepository)
//	            .flow(step1(jobRepository))
//	            .end()
//	            .build();
//	}
//
//	// Create Step
//	@Bean
//	public Step step1(JobRepository jobRepository) {
//	    return new stepBuilderFactory("step-1", jobRepository)
//	            .<ReviewInfo, ReviewInfo>chunk(10)
//	            .reader(reviewReader())
//	            .processor(reviewItemProcessor())
//	            .writer(reviewWriter())
//	            .build();
//	}
	

}
