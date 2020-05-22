package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.cinema.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Optional;
import java.util.Properties;

public class PsqlStore implements Store {
    private final BasicDataSource pool = new BasicDataSource();

    public PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    @Override
    public Optional<User> findUserByPhone() {
        return Optional.empty();
    }

    @Override
    public boolean isTaken() {
        return false;
    }

    @Override
    public boolean takePlace(int row, int place) {
        return false;
    }
}
