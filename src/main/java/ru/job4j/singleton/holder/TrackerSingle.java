package ru.job4j.singleton.holder;

import ru.job4j.singleton.Item;

public class TrackerSingle {
    private TrackerSingle() {
    }

    public static TrackerSingle getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static final class Holder {
        static {
            System.out.println("Holder loaded");
        }

        private static final TrackerSingle INSTANCE = new TrackerSingle();
    }

    public static void main(String[] args) {
        System.out.println("TrackerSingle main");
        TrackerSingle tracker = TrackerSingle.getInstance();
        System.out.println("TrackerSingle main end");
    }
}
