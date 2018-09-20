package ru.job4j.store;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StoreTest {
    Store garage = new Store();
    List<Store.User> oldGarage = new LinkedList<>();
    List<Store.User> newGarage = new LinkedList<>();

    @Before
    public void addElementInCollections() {
        oldGarage.add(new Store.User(1, "ЗАЗ 965"));
        oldGarage.add(new Store.User(2, "Москвич 415"));
        oldGarage.add(new Store.User(3, "МТЗ 80"));
        oldGarage.add(new Store.User(4, "Opel"));
        oldGarage.add(new Store.User(5, "Заз"));
    }

    @Test
    public void whenBothCollectionsIsSame() {
        newGarage.add(new Store.User(1, "ЗАЗ 965"));
        newGarage.add(new Store.User(2, "Москвич 415"));
        newGarage.add(new Store.User(3, "МТЗ 80"));
        newGarage.add(new Store.User(4, "Opel"));
        newGarage.add(new Store.User(5, "Заз"));
        HashMap result = garage.isChange(oldGarage, newGarage);
        assertThat(result.get("Amount new add: "), is(0));
        assertThat(result.get("Amount new change: "), is(0));
        assertThat(result.get("Amount new delete: "), is(0));
    }

    @Test
    public void isChange() {
        newGarage.add(new Store.User(1, "Audi RS"));
        newGarage.add(new Store.User(4, "Fend 930"));
        newGarage.add(new Store.User(5, "Санки"));
        newGarage.add(new Store.User(6, "BMW"));
        newGarage.add(new Store.User(7, "Tesla"));
        newGarage.add(new Store.User(8, "Lamborghini"));
        HashMap result = garage.isChange(oldGarage, newGarage);
        assertThat(result.get("Amount new add: "), is(3));
        assertThat(result.get("Amount new change: "), is(3));
        assertThat(result.get("Amount new delete: "), is(2));
    }
}