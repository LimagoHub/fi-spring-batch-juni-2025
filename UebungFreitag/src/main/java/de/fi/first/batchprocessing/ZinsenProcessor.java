package de.fi.first.batchprocessing;

import de.fi.first.entity.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ZinsenProcessor implements ItemProcessor<Person, Person> {

    private final double zinssatz;

    public ZinsenProcessor(@Value("${ZinsenProcessor.zinssatz}") final double zinssatz) {
        this.zinssatz = zinssatz;
    }

    @Override
    public Person process(final Person item) throws Exception {
        item.setBalance(item.getBalance() * zinssatz);
        return item;
    }
}
