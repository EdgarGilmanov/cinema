package ru.job4j.cinema.store;

import org.junit.Assert;
import org.junit.Test;

public class PsqlTest {

    @Test
    public void getAllPlaces() {
        boolean[][] test = {
                {true, false, true},
                {false, true, false},
                {true, false, false}
        };
        Store store = PsqlStore.instOf();
        boolean[][] rsl = store.getAllPlaces();
        Assert.assertArrayEquals(test, rsl);
    }

    @Test
    public void findUserByPhoneTest() {
        Store store = PsqlStore.instOf();
        Assert.assertTrue(store.findUserByPhone("30405").isPresent());
        Assert.assertTrue(store.findUserByPhone("not true phone").isEmpty());
    }

    @Test
    public void testIsTakenPlaceMethod() {
        Store store = PsqlStore.instOf();
        Assert.assertTrue(store.isPlaceTaken(1, 1));
        Assert.assertTrue(store.isPlaceTaken(3, 1));
        Assert.assertFalse(store.isPlaceTaken(1, 2));
        Assert.assertFalse(store.isPlaceTaken(2, 3));
    }

    @Test
    public void takeTakenPlaceTest() {
        Store store = PsqlStore.instOf();
        Assert.assertFalse(store.takePlace(1, 1));
        Assert.assertFalse(store.takePlace(3, 1));
    }

    @Test
    public void takeFreePlaceTest() {
        Store store = PsqlStore.instOf();
        Assert.assertTrue(store.takePlace(1, 2));
        Assert.assertTrue(store.takePlace(2, 3));
    }
}
