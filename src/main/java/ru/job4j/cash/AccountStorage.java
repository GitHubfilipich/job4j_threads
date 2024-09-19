package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Account fromAccount = getById(fromId).orElse(null);
        Account toAccount = getById(toId).orElse(null);
        if (fromAccount != null && fromAccount.amount() >= amount && toAccount != null) {
            update(new Account(fromAccount.id(), fromAccount.amount() - amount));
            update(new Account(toAccount.id(), toAccount.amount() + amount));
            return true;
        }
        return false;
    }
}