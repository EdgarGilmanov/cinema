package ru.job4j.cinema.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.cinema.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Optional<User> findUserByPhone(String phone) {
        Optional<User> rsl = Optional.empty();
        try (Connection cnn = pool.getConnection();
             PreparedStatement st = cnn.prepareStatement(
                     "SELECT * FROM accounts WHERE phone = ?")) {
                 st.setString(1, phone);
                 ResultSet set = st.executeQuery();
                 if (set.next()) {
                     int id = Integer.parseInt(set.getString(1));
                     String name = set.getString(2);
                     String hl = set.getString(4);
                     String pl = set.getString(5);
                     int hall = 0;
                     int place = 0;
                     if (hl != null) {
                         hall = Integer.parseInt(hl);
                     }
                     if (pl != null) {
                         place = Integer.parseInt(pl);
                     }
                     User user = new User(id, name, phone, hall, place);
                     rsl = Optional.of(user);
                 }
        } catch (Exception e) {
                 e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean isPlaceTaken(int row, int place) {
        try (Connection cnn = pool.getConnection();
             PreparedStatement st = cnn.prepareStatement(
                     "SELECT row, p.place, p.status FROM hall AS h" +
                             "INNER JOIN place AS p ON placeId = p.id " +
                             "WHERE row = ? AND p.place = ?")) {
            st.setInt(1, row);
            st.setInt(2, place);
            ResultSet set = st.executeQuery();
            if (set.next()) {
                return set.getBoolean(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean[][] getAllPlaces() {
        boolean[][] model = new boolean[3][3];
        try (Connection cnn = pool.getConnection();
             PreparedStatement st = cnn.prepareStatement(
                     "SELECT row, p.place, p.status FROM hall AS h" +
                             "INNER JOIN place AS p ON placeId = p.id ORDER BY p.id")) {
            ResultSet set = st.executeQuery();
            while (set.next()) {
                int row = set.getInt(1);
                int place = set.getInt(2);
                boolean status = set.getBoolean(3);
                System.out.println(set.getBoolean(3));
                model[--row][--place] = status;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public boolean takePlace(int row, int place) {
        if (!isPlaceTaken(row, place)) {
            int placeId = getPlaceId(row, place);
            if (placeId > 0) {
                try (Connection cnn = pool.getConnection()) {
                    cnn.setAutoCommit(false);
                     try (PreparedStatement st = cnn.prepareStatement(
                                 "UPDATE place SET status = TRUE WHERE id = ?")) {
                        st.setInt(1, placeId);
                        st.executeUpdate();
                        cnn.commit();
                        return true;
                     }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private Integer getPlaceId(int row, int place) {
        try (Connection cnn = pool.getConnection();
             PreparedStatement st = cnn.prepareStatement(
                     "SELECT p.id FROM hall AS h" +
                             "INNER JOIN place AS p ON placeId = p.id " +
                             "WHERE row = ? AND p.place = ?")) {
            st.setInt(1, row);
            st.setInt(2, place);
            ResultSet set = st.executeQuery();
            if (set.next()) {
                return set.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return - 1;
    }
}
