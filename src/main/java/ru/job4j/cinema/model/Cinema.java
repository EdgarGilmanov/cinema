package ru.job4j.cinema.model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class Cinema {
    private final boolean[][] model = new boolean[3][3];


    public boolean isTaken(int row, int place) {
        return model[row][place];
    }

    public void buyPlace(int row, int place) {

    }
}
