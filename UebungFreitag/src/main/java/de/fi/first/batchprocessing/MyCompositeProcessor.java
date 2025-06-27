package de.fi.first.batchprocessing;

import de.fi.first.entity.Person;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class MyCompositeProcessor extends CompositeItemProcessor<Person, Person> {


    public MyCompositeProcessor(final WeltSpartagProcessor weltSpartagProcessor,
                                final ZinsenProcessor zinsenProcessor,
                                final FilterProcessor filterProcessor,
                                SumBalanceProcessor sumBalanceProcessor) {
        super(filterProcessor,weltSpartagProcessor, zinsenProcessor, sumBalanceProcessor);
    }
}
