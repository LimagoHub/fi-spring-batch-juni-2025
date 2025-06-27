package de.fi.first.batchprocessing;

import de.fi.first.entity.Person;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepSynchronizationManager;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@StepScope
public class WeltSpartagProcessor implements ItemProcessor<Person, Person>, StepExecutionListener {






    @Override
    public Person process(final Person item) throws Exception {



        StepExecution stepExecution = StepSynchronizationManager.getContext().getStepExecution();
        int maleCount =  stepExecution.getExecutionContext().getInt("MaleCount",0);
        if(item.getGender().equals("Male")) maleCount++;
        stepExecution.getExecutionContext().putInt("MaleCount",maleCount);
        item.setBalance(item.getBalance() + 5.0);
        return item;
    }
}
