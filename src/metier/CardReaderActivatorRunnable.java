package metier;

import domaine.CardReader;

import java.util.concurrent.Callable;

public class CardReaderActivatorRunnable implements Runnable {

    CardReader cardReader;
    boolean active;

    public CardReaderActivatorRunnable(CardReader cardReader, boolean active) {
        this.cardReader = cardReader;
        this.active = active;
    }

    @Override
    public void run() {
        boolean activated = cardReader.setActivated(active);
        Filter filter = Filter.getInstance();
        filter.setActive(cardReader.getDeviceName(), activated);
    }
}
