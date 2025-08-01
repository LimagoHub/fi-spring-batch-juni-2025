package de.fi.first.batchprocessing;

import de.fi.first.entity.Person;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class ZinsenProcessor implements ItemProcessor<Person, Person>, StepExecutionListener {

    private final double zinssatz;





    public ZinsenProcessor(@Value("${ZinsenProcessor.zinssatz}") final double zinssatz) {
        this.zinssatz = zinssatz;

    }


    @Override
    public Person process(final Person item) throws Exception {
        StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
        int maleCount =  stepExecution.getExecutionContext().getInt("MaleCount",0);
        System.out.println(maleCount);
        item.setBalance(item.getBalance() * zinssatz);
        return item;
    }
}
