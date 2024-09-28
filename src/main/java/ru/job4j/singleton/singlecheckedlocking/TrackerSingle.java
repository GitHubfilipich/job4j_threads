package ru.job4j.singleton.singlecheckedlocking;

import ru.job4j.singleton.Item;

public class TrackerSingle {

    private static TrackerSingle instance;

    private TrackerSingle() {
    }

    public static synchronized TrackerSingle getInstance() {
        if (instance == null) {
            instance = new TrackerSingle();
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
