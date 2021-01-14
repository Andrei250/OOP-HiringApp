package com.pachetepachete.utils;

/*
    Observer pentru a notifica userii in caz de modificari la joburi.
 */
public interface ObserverJob {
    void update(String message);
}
