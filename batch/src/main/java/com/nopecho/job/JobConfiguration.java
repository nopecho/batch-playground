package com.nopecho.job;

import com.nopecho.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job firstJob(JobExecutionListener listener, Step step) {
        return jobBuilderFactory.get("firstJob")
                .listener(listener)
                .start(step)
                .build();
    }

    @Bean
    @JobScope
    public Step firstStep() {
        return stepBuilderFactory.get("firstStep")
                .<Item, Item>chunk(1000)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean
    @StepScope
    public JdbcCursorItemReader<Item> itemReader() {
        return new JdbcCursorItemReaderBuilder<Item>()
                .sql("SELECT * FROM ITEM")
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Item, Item> itemProcessor() {
        return item -> {
            item.changeToken();
            return item;
        };
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<Item> itemWriter() {
        return new JdbcBatchItemWriter<>();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info(">>>>> start");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                log.info("<<<<< end");
            }
        };
    }
}
