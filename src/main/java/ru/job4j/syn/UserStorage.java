package ru.job4j.syn;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage implements Storage<User>,TransferStorage {
    @GuardedBy("this")
    private final Map<Integer,User> users = new HashMap<>();

    @Override
    public synchronized boolean transfer(int firstId, int secondId, int amount) {
        if(!users.containsKey(firstId) || !users.containsKey(secondId))
        {
            System.out.println("Incorrect id");
            return false;
        }
        User userFirst = users.get(firstId);
        User userSecond = users.get(secondId);
        if(userFirst.getAmount() - amount < 0) {
            System.out.println("Insufficient funds");
            return false;
        }
        userFirst.setAmount(userFirst.getAmount() - amount);
        userSecond.setAmount(userSecond.getAmount() + amount);
        return true;
    }

    @Override
    public synchronized boolean add(User elem) {
        users.putIfAbsent(elem.getId(),elem);
        return true;
    }

    @Override
    public synchronized boolean update(User elem) {
        if(users.containsKey(elem.getId())) {
            return false;
        }
        users.put(elem.getId(),elem);
        return true;
    }

    @Override
    public synchronized boolean remove(User elem) {
        return users.remove(elem.getId(),elem);
    }
}
