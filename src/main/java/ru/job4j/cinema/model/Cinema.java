package ru.job4j.cinema.model;

import ru.job4j.cinema.store.PsqlStore;
import ru.job4j.cinema.store.Store;

public class Cinema {
    private boolean[][] model;
    private final Store store = PsqlStore.instOf();
    private final static Cinema CINEMA = new Cinema();

    private Cinema() {
        refresh();
    }

    public static Cinema instOf() {
        return CINEMA;
    }

    public boolean isPlaceTaken(int row, int place) {
        return store.isPlaceTaken(row, place);
    }

    public boolean buyTicket(int row, int place) {
        return store.takePlace(row, place);
    }

    public boolean[][] getModel() {
        return model;
    }

    public void refresh() {
        model = store.getAllPlaces();
    }
}
