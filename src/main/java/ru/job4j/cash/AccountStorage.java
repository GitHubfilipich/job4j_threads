package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        if (getById(account.id()).isEmpty()) {
            accounts.put(account.id(), account);
            return true;
        }
        return false;
    }

    public synchronized boolean update(Account account) {
        if (getById(account.id()).isPresent()) {
            accounts.put(account.id(), account);
            return true;
        }
        return false;
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
        if (fromAccount != null && toAccount != null) {
            update(new Account(fromAccount.id(), fromAccount.amount() - amount));
            update(new Account(toAccount.id(), toAccount.amount() + amount));
            return true;
        }
        return false;
    }
}