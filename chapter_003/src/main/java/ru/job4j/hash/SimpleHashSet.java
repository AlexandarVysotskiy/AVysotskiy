package ru.job4j.hash;

/**
 * Класс реализует коллекцию типа Set на базе хэш-таблицы.
 * <p>
 * Version 1.2
 */
public class SimpleHashSet<E> {
    private Object[] array;

    /**
     * Счетчик элементов данных.
     */
    private int counter;

    private int size;

    private int tempHashElement;

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
        this.array = new Object[findPrime(array.length * 2)];
        for (Object i : tempArray) {
            if (i != null) {
                add((E) i);
            }
        }
    }

    private int findPrime(int min) {
        for (int i = min + 1; true; i++) {
            if (i % 2 == 0) {
                i++;
                if (isPrime(i)) {
                    return i;
                }
            }
        }
    }

    private boolean isPrime(int num) {
        boolean result = true;
        for (int j = 2; j * j <= num; j++) {
            if (num % j == 0) {
                result = false;
            }
        }
        return result;
    }

    public int find(E element) {
        int result = -1;
        int hashVal = simpleHashFunction(element);
        int stepSize = doubleHashFunction(element);
        while (array[hashVal] != null) {
            if (array[hashVal].equals(element)) {
                result = (int) array[hashVal];
                tempHashElement = (int) array[hashVal];
                break;
            }
            hashVal += stepSize;
            hashVal %= array.length;
        }
        return result;
    }

    public boolean remove(E value) {
        boolean result = false;
        if (find(value) != -1) {
            array[tempHashElement] = -1;
            result = true;
        }
        return result;
    }
}