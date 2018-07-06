package ru.job4j.hash;

public class SimpleHashSet<E> {
    private Object[] array;

    /**
     * Счетчик элементов данных.
     */
    private int counter;

    private int size;

    public SimpleHashSet(int size) {
        this.size = size;
        this.array = new Object[size];
    }

    /**
     * Метод производит линейное хэширование.
     */
    private int simpleHashFunction(E key) {
        return key.hashCode() % array.length;
    }

    /**
     * Метод производит двойное хэширование.
     */
    private int doubleHashFunction(E key) {
        return 5 - (key.hashCode() % 5);
    }

    public boolean findCell(E value) {
        boolean result = true;
        int hashValue = simpleHashFunction(value);
        int hashValueDouble = doubleHashFunction(value);
        while (array[hashValue] != null) {
            if (array[hashValue].equals(value)) {
                result = false;
                break;
            }
            hashValue += hashValueDouble;
            hashValue %= array.length;
        }
        array[hashValue] = value;
        return result;
    }

    public boolean add(E value) {
        boolean result = false;
        if (findCell(value)) {
            result = true;
            counter++;
        }
        if (this.array.length < counter * 1.1) {
            grow();
        }
        return result;
    }

    /**
     * Метод увеличивает массив в два раза когда он заполнен на 90 процентов.
     */
    private void grow() {
        Object[] tempArray = this.array;
        this.array = new Object[array.length * 2];
        for (Object i : tempArray) {
            if (i != null) {
                add((E) i);
            }
        }
    }

    public boolean find(E element) {
        boolean result = false;
        for (Object i : array) {
            if (i != null) {
                if (i.equals(element)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean remove(E value) {
        boolean result = false;

        for (int index = 0; index < array.length; index++) {
            if (array[index] == value) {
                this.array[index] = null;
                result = true;
            }
        }
        return result;
    }
}