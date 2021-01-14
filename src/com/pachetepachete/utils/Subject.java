package com.pachetepachete.utils;
import java.util.ArrayList;

/*
    Entitatea subject din design pattern-ul observer pentru joburi.
 */
public class Subject {
    private ArrayList<ObserverJob> observers = new ArrayList<>();

    public void attach(ObserverJob observer) {
        if (this.observers.contains(observer)) {
            return;
        }

        this.observers.add(observer);
    }

    public void dettach(ObserverJob observer) {
        this.observers.remove(observer);
    }

    public void notifyAllObserverOfCanceling(String message) {
        for (ObserverJob observer : this.observers) {
            observer.update(message);
        }
    }

    public void notifyOneObserver(ObserverJob observer, String message) {
        observer.update(message);
    }

    public void removeAll() {
        observers.removeAll(observers);
    }
}
