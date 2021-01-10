package cloud.marchand.renderer.interfaces;

import java.util.Iterator;
import java.util.Vector;

/**
 * Une classe qui implémente l'interface Observable
 */
public class Observable {

    private boolean hasChanged = false;

    /**
     * Liste des objets qui observent le statut de Observable
     */
    private Vector<Observer> observers = new Vector<>();

    /**
     * Faire observer un objet à objet
     */
    public boolean addObserver(Observer observer) {
        if (observer != null) {
            return this.observers.add(observer);
        }
        return false;
    }

    /**
     * Faire arrêter d'observer un objet à cet objet
     */
    public boolean removeObserver(Observer observer) {
        return this.observers.remove(observer);
    }

    /**
     * Fait arrêter d'observer tous les objets à cet objet
     */
    public void removeAllObservers() {
        this.observers.removeAllElements();
    }

    /**
     * Mets au courant les observateurs que l'objet a changé
     */
    public void notifyObservers() {
        this.notifyObservers(null);
    }

    /**
     * Mets au courant les observateurs que l'objet a changé, en leur donnant un
     * argument
     * 
     * @param arg Argument qui donne les raisons de la mise à jour
     */
    public void notifyObservers(String arg) {
        if (this.hasChanged()) {
            for (Iterator<Observer> iterator = this.observers.iterator(); iterator.hasNext();) {
                try {
                    iterator.next().update(this, arg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.clearState();
        } 
    }

    /**
     * Défini que l'objet a changé
     */
    public void setChanged() {
        this.hasChanged = true;
    }

    /**
     * Défini que l'objet n'a pas changé
     */
    public void clearState() {
        this.hasChanged = false;
    }

    /**
     * Indique si l'objet à changé
     */
    public boolean hasChanged() {
        return this.hasChanged;
    }

}
