package de.fi.verzweigungdemo;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

//@Component

public class AppRunner2 implements CommandLineRunner {


    private final JobLauncher jobLauncher;
    private final Job job;

    public AppRunner2(final JobLauncher jobLauncher, final Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Override
    public void run(final String... args) throws Exception {
        System.out.println( "\nJoblauf mit Job-Parameter 'Fehler':" );
        JobExecution je = jobLauncher.run( job, new JobParametersBuilder().addString("UUID", UUID.randomUUID().toString()).addString(
                ConditionalFlowJobConfiguration.OK_ODER_FEHLER, "Fehler" ).toJobParameters() );

    }

}
