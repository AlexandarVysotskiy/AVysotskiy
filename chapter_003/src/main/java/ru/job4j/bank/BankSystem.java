package ru.job4j.bank;

import java.util.*;
import java.util.stream.Stream;

public class BankSystem {
    private Map<User, List<Account>> repository = new HashMap<User, List<Account>>();


    /**
     * Метод добавляет нового пользователя.
     *
     * @param user
     */
    public void addUser(User user) {
        this.repository.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод удаляет пользователя.
     *
     * @param user
     */
    public void deleteUser(User user) {
        this.repository.remove(user);
    }

    /**
     * Вспомогательный метод, возвращает спиок пользователей по указаному ключу.
     *
     * @param passport - ключ, по которому осуществяется поиск.
     */
    public User getUser(String passport) {
        User result;
        Stream<User> streamUsers = repository.keySet().stream();
        result = streamUsers.filter(i -> (i.getPassport().equals(passport))).findFirst().get();
        if (result.getPassport() == null) {
            throw new NullPointerException("Пользователь с таким именнем не найден.");
        }
        return result;
    }

    /**
     * Метод добавляет счёт пользователю.
     *
     * @param passport - ключ, по которому добавляют счет.
     * @param account  - счет пользоваетя.
     */
    public void addAccountToUser(String passport, Account account) {
        this.repository.get(getUser(passport)).add(account);
    }

    /**
     * Метод удаляет счёт пользователя.
     *
     * @param passport - ключ, по которому удаляет счёт.
     * @param account  - счет пользоваетя.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        this.repository.get(getUser(passport)).remove(account);
    }

    /**
     * Метод возвращает спиок счетов пользователя по указаному ключу.
     */
    public List<Account> getUserAccount(String passport) {
        return this.repository.get(getUser(passport));
    }

    /**
     * Вспомогательный метод возращает счет пользоваетля по паспортным данным и реквизитам.
     */
    private Account getAccountByPassportAndRequisites(String passport, String requisites) {
        Account result;
        Stream<Account> streamUserAccount = this.repository.get(getUser(passport)).stream();
        result = streamUserAccount.filter(i -> (i.getRequisites().equals(requisites))).findFirst().get();
        return result;
    }

    /**
     * Метод для перечисления денег с одного счёта на другой счёт.
     *
     * @param srcPassport   - пароль пользователя со счета, с которого переводят средства.
     * @param srcRequisites - реквизиты счета, с которого переводят средства.
     * @param destPassport  - пароль пользователя, на счет которого переводят средства.
     * @param dstRequisites - реквизиты счета, на который будут переводиться средства.
     * @param amount        - кол-во переведенных средств.
     * @return Если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisites, String destPassport, String dstRequisites, double amount) {
        Boolean result = false;
        Account source = getAccountByPassportAndRequisites(srcPassport, srcRequisites);
        Account destination = getAccountByPassportAndRequisites(destPassport, dstRequisites);
        if (source == null && destination == null) {
            System.out.println("Несуществующий акаунт");
        } else if (source.getValue() <= amount) {
            System.out.println("Недостаточное кол-во средств для перевода");
        } else {
            source.setValue(source.getValue() - amount);
            destination.setValue(destination.getValue() + amount);
            result = true;
        }
        return result;
    }

    /**
     * @return Метод возвращает кол-во пользователей.
     */
    public Set<User> getAllUser() {
        return this.repository.keySet();
    }
}