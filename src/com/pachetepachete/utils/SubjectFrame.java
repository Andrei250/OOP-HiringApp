package com.pachetepachete.utils;

import com.pachetepachete.Models.User;

import java.util.ArrayList;

public class SubjectFrame {
    private ArrayList<ObserverFrame> observers;

    public SubjectFrame() {
    }
    public void attach(ObserverFrame observer) {
        if (this.observers.contains(observer)) {
            return;
        }

        this.observers.add(observer);
    }

    public void dettach(ObserverFrame observer) {
        this.observers.remove(observer);
    }

    public void notifyAllObserverOfCanceling(User user) {
        for (ObserverFrame observer : this.observers) {
            observer.update(user);
        }
    }
}
