package de.fi.first.batchprocessing;

import de.fi.first.entity.Person;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SumBalanceProcessor implements ItemProcessor<Person, Person>, StepExecutionListener {






    @Override
    public Person process(final Person item) throws Exception {



        StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
        double sumBalance =  stepExecution.getJobExecution().getExecutionContext().getDouble("SumBalance",0);
        sumBalance += item.getBalance();
        stepExecution.getJobExecution().getExecutionContext().putDouble("SumBalance",sumBalance);

        return item;
    }
}
