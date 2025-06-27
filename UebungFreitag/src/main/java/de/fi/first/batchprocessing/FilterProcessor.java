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
public class FilterProcessor implements ItemProcessor<Person, Person>, StepExecutionListener {






    @Override
    public Person process(final Person item) throws Exception {



        if(item.getGender().equals("Female"))
            return item;

        return null;
    }
}
