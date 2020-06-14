package metier;

import domaine.CardReader;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Filter {
    private static Filter instance = new Filter();
    private boolean active;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addObserver(PropertyChangeListener listener) {
        CardReader card = (CardReader) listener;
        pcs.addPropertyChangeListener(card.getDeviceName(), listener);
    }

    private Filter() {
    }

    public static Filter getInstance() {
        return instance;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(String deviceName, boolean active) {
        boolean oldActive = this.active;
        this.active = active;
        pcs.firePropertyChange(deviceName, oldActive, active);
    }
}