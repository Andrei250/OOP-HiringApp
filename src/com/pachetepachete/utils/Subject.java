package com.pachetepachete.utils;

import com.pachetepachete.Models.Consumer;
import com.pachetepachete.Models.User;

import java.util.ArrayList;

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
}
