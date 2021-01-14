package com.pachetepachete.utils;

import java.util.ArrayList;

/*
    Array custom de perechi cu cateva metode necesare.
 */
public class PairArray<K, V> extends ArrayList<Pair<K, V>> {
    public PairArray(int initialCapacity) {
        super(initialCapacity);
    }

    public PairArray() {
        super();
    }

    public boolean containKey(K key) {
        for (Pair<K, V> el : this) {
            if (el.getKey() == key) {
                return true;
            }
        }

        return false;
    }

    public boolean updateKey(K key, V value) {
        for (Pair<K, V> el : this) {
            if (el.getKey() == key) {
                el.setValue(value);
                return true;
            }
        }

        return false;
    }

    public boolean removeKey(K key) {
        for (Pair<K, V> el : this) {

            if (el.getKey() == key) {
                this.remove(el);
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();

        for (Pair<K, V> el : this) {
            ans.append(el.toString());
        }

        return ans.toString();
    }
}
