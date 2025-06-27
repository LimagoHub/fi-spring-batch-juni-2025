package de.fi.first.batchprocessing;


import de.fi.first.entity.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private final JobRepository repository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(final JobRepository repository, final PlatformTransactionManager transactionManager) {
        this.repository = repository;
        this.transactionManager = transactionManager;
    }



    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .linesToSkip(1)
                .delimited()
                .names("id","firstname","lastname","email","gender","balance")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                    setTargetType(Person.class);
                }})
                .build();


    }

    @Bean

    public MultiResourceItemReader<Person> multiResourceReader(@Value("classpath:inbox/*.csv") Resource[] inputResources ) {
        return new MultiResourceItemReaderBuilder<Person>()
                .name("multiResourceReader")
                .delegate(reader())
                .resources(inputResources)
                .build();
    }
//    // Dummy Processor
//    public ItemProcessor<Person, Person> processor() {
//        return person -> {
//            System.out.println("Verarbeite: " + person);
//            return person;
//        };
//    }

    // Dummy Writer
    public ItemWriter<Person> writer() {
        return items -> {
            for (Person p : items) {
                System.out.println("Schreibe: " + p);
            }
        };
    }

    @Bean
    public Step chunkStep(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      MultiResourceItemReader<Person> multiResourceReader, MyCompositeProcessor processor) {
        return new StepBuilder("chunkStep", jobRepository)
                .<Person, Person>chunk(10, transactionManager)
                .reader(multiResourceReader)

                .processor(processor)
                .writer(writer())
                .build();
    }

    @Bean

    public Step moveFileStep(Tasklet task1) {

        return new StepBuilder("moveFileStep", repository).tasklet( task1, transactionManager ).build();
    }

    @Bean

    public SystemCommandTasklet task1() {
        SystemCommandTasklet tasklet = new SystemCommandTasklet();

        
        tasklet.setCommand("cmd","/c","copy-rename.bat");
        tasklet.setWorkingDirectory("./src/main/resources/script");
        tasklet.setTimeout(5000);

        return tasklet;
    }

    @Bean
    public Job meinTaskletJob(Step moveFileStep, Step chunkStep) throws Exception
    {


        return new JobBuilder("meinTaskJob", repository).incrementer( new RunIdIncrementer() )
                .start( moveFileStep )
                .next( chunkStep )
                .build();
    }


}
