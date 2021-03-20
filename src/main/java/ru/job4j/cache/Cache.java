package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public void update(Base model) {
        memory.computeIfPresent(model.getId(), (id, base) -> {
                    if (base.getVersion() != model.getVersion()) {
                        throw new OptimisticException("The model was previously changed");
                    }
                    base = new Base(id, model.getVersion() + 1);
                    base.setName(model.getName());
                    return base;
                }
        );
    }

    public void delete(Base model) {
      memory.remove(model.getId());
    }
}
