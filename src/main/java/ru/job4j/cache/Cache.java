package ru.job4j.cache;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) throws OptimisticException {
        return memory.putIfAbsent(model.id(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        try {
            memory.computeIfPresent(model.id(), (integer, base) -> {
                if (model.version() != base.version()) {
                    throw new NullPointerException("Versions are not equal");
                }
                return new Base(model.id(), model.name(), model.version() + 1);
            });
        } catch (NullPointerException e) {
            throw new OptimisticException(e.getMessage());
        }
        return true;
    }

    public void delete(int id) {
        memory.remove(id);
    }

    public Optional<Base> findById(int id) {
        return Stream.of(memory.get(id))
                .filter(Objects::nonNull)
                .findFirst();
    }
}