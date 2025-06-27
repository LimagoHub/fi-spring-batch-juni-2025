package de.fi.first.batchprocessing;

import de.fi.first.entity.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class WeltSpartagProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(final Person item) throws Exception {
        item.setBalance(item.getBalance() + 5.0);
        return item;
    }
}
