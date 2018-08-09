package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankSystemTest {
    BankSystem bankSystem = new BankSystem();

    @Before
    public void addUserInRepository() {
        bankSystem.addUser(new User("Elon Mask", "123"));
        bankSystem.addUser(new User("Bell Gets", "PassportOfBill"));
        bankSystem.addUser(new User("Vasya Pupkin", "PassportOfVasya"));
    }

    @Test
    public void addUserTestIfKeyIs() {
        assertThat("Elon Mask", is(bankSystem.getUser("123").getName()));
    }

    @Test(expected = NullPointerException.class)
    public void addUserTestIfKeyNot() {
        assertThat("Пользователь с таким именнем не найден.", is(bankSystem.getUser("Вася").getName()));
    }

    @Test
    public void deleteUserTest() {
        bankSystem.deleteUser(bankSystem.getUser("123"));
        assertThat(2, is(bankSystem.getAllUser().size()));
    }

    @Test
    public void addAccountToUserTest() {
        bankSystem.addAccountToUser("123", new Account(10000000, "Money"));
        assertThat("Money", is(bankSystem.getUserAccount("123").get(0).getRequisites()));
    }

    @Test
    public void deleteAccountFromUser() {
        Account accountMasc = new Account(10000000, "Money");
        bankSystem.addAccountToUser("123", accountMasc);
        bankSystem.deleteAccountFromUser("123", accountMasc);
        assertThat(0, is(bankSystem.getUserAccount("123").size()));
    }

    @Test
    public void getUserAccountTest() {
        bankSystem.addAccountToUser("123", new Account(123, "qwe"));
        bankSystem.addAccountToUser("123", new Account(124, "asd"));
        assertThat(2, is(bankSystem.getUserAccount("123").size()));
    }

    @Test
    public void transferMoneyTest() {
        bankSystem.addAccountToUser("PassportOfBill", new Account(1000, "BellRequsites"));
        bankSystem.addAccountToUser("PassportOfVasya", new Account(500, "RequisitesOfVasya"));
        bankSystem.transferMoney("PassportOfBill", "BellRequsites", "PassportOfVasya", "RequisitesOfVasya", 1);
        assertThat(999.0, is(bankSystem.getUserAccount("PassportOfBill").get(0).getValue()));
        assertThat(501.0, is(bankSystem.getUserAccount("PassportOfVasya").get(0).getValue()));
    }

    @Test(expected = AssertionError.class)
    public void whenNoMoneyForTransfer() {
        bankSystem.addAccountToUser("PassportOfBill", new Account(1000, "BellRequsites"));
        bankSystem.addAccountToUser("PassportOfVasya", new Account(500, "RequisitesOfVasya"));
        bankSystem.transferMoney("PassportOfBill", "BellRequsites", "PassportOfVasya", "RequisitesOfVasya", 2000);
        assertThat(999.0, is(bankSystem.getUserAccount("PassportOfBill").get(0).getValue()));
        assertThat(501.0, is(bankSystem.getUserAccount("PassportOfVasya").get(0).getValue()));
    }
}