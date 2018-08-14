package ru.job4j.map;

public class CatHash {
    byte ears = 2;

    char paws = 4;

    short mustache = 20;

    long wool = 999999999999999999L;

    /**
     * 1. Присваиваем переменной result любое не нолевое значение.
     * 2. Присваиваем переменной result значение = умноженную переменную result на 31 и добавляем значищие поля.
     * 3. Возращаем полученный result.
     */
    @Override
    public int hashCode() {
        int result = 37;
        result = 31 * result + (int) ears;
        result = 31 * result + (int) paws;
        result = 31 * result + (int) mustache;
        result = 31 * result + (int) (wool ^ (wool >>> 32));
        return result;
    }
}
