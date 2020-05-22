package ru.job4j.cinema.store;

import ru.job4j.cinema.model.User;

import java.util.Optional;

public interface Store {
    Optional<User> findUserByPhone(String phone);

    boolean isPlaceTaken(int row, int place);

    boolean[][] getAllPlaces();

    boolean takePlace(int row, int place);

}
