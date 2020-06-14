package metier;

import dao.LoadData;
import domaine.CardReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CardReaderManager {
    private Map<Integer, CardReader> cardReaders;

    public CardReaderManager() {
        Filter filter = Filter.getInstance();
        cardReaders = LoadData.getCardReaders();
        for (Map.Entry<Integer, CardReader> entry : cardReaders.entrySet()) {
            System.out.println("observer ajouté " + entry.getValue());
            filter.addObserver(entry.getValue());
        }
    }

    public boolean activate(int cardReaderId, boolean active) {
        CardReader cardReader = cardReaders.get(cardReaderId);
        if (cardReader != null) {
            try {
                return new CardReaderActivator(cardReader, active).call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void activateNoWait(int cardReaderId, boolean active) {
        CardReader cardReader = cardReaders.get(cardReaderId);
        if (cardReader != null) {
            new Thread(new CardReaderActivatorRunnable(cardReader, active)).start();
        }
    }

    public boolean activateAll(boolean active) {
        List<CardReaderActivator> cardReaderActivators = new ArrayList<>();
        ExecutorService exec = Executors.newCachedThreadPool();
        for (CardReader cardReader : cardReaders.values()) {
            cardReaderActivators.add(new CardReaderActivator(cardReader, active));
        }
        try {
            List<Future<Boolean>> futures = exec.invokeAll(cardReaderActivators);
            for (Future<Boolean> future : futures) {
                if (!future.get()) {
                    return false;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        exec.shutdown();
        return true;
    }

    @Override
    public String toString() {
        return cardReaders.toString();
    }
}