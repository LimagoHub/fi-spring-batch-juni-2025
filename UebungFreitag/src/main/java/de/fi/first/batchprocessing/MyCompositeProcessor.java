package de.fi.first.batchprocessing;

import de.fi.first.entity.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyCompositeProcessor extends CompositeItemProcessor<Person, Person> {


    public MyCompositeProcessor(final WeltSpartagProcessor weltSpartagProcessor, final ZinsenProcessor zinsenProcessor, final ItemProcessor<?, ?>... delegates) {
        super(weltSpartagProcessor, zinsenProcessor);
    }
}
