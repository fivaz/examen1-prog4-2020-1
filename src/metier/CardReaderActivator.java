package metier;

import domaine.CardReader;

import java.util.concurrent.Callable;

public class CardReaderActivator implements Callable<Boolean> {

    CardReader cardReader;
    boolean active;

    public CardReaderActivator(CardReader cardReader, boolean active) {
        this.cardReader = cardReader;
        this.active = active;
    }

    @Override
    public Boolean call() throws Exception {
        boolean activated = cardReader.setActivated(active);
        Filter filter = Filter.getInstance();
        filter.setActive(cardReader.getDeviceName(), activated);
        return activated;
    }
}
