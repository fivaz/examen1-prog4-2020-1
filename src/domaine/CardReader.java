package domaine;

import metier.ControlSystemUsable;
import outils.SystemKeso;
import outils.SystemStanley;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CardReader implements PropertyChangeListener {

    private int id;
    private Device device;
    private boolean activated;
    private String controlSystem;

    public CardReader(int id, Device device, boolean activated, String controlSystem) {
        this.id = id;
        this.device = device;
        this.activated = activated;
        this.controlSystem = controlSystem;
    }

    public String getDeviceName() {
        return device.getName();
    }

    public boolean isActivated() {
        return activated;
    }

    public boolean setActivated(boolean activated) {
        this.activated = activated;     // Attention! à corriger : l'activation/désactivation n'a pas forcément marché, il faut vérifier les codes retour des différentes méthodes des controlSystem
        ControlSystemUsable controlSystemUsable = new ControlSystemUsable();
        return controlSystemUsable.activeControlSystem(controlSystem, activated);
    }

    @Override
    public String toString() {
        return "CardReader{" + id + ":" + device + "=" + activated + "(" + controlSystem + ")";
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
//        System.out.println("Printer B4.18: true ==> false");
        System.out.println(getDeviceName() + ": " + propertyChangeEvent.getOldValue() + " ==> " + propertyChangeEvent.getNewValue());
    }
}