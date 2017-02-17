package by.epam.jmp.app.tradesystem.dataprovider.inmemory;

import by.epam.jmp.app.tradesystem.dataprovider.GenericProvider;
import by.epam.jmp.app.tradesystem.model.IdentifiedType;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class IMGenericProviderImpl<T extends IdentifiedType> implements GenericProvider<T> {

    private volatile long idCounter = 0;
    private final Map<Long, T> entries = new HashMap<Long, T>();

    private long generateId() {
        return ++idCounter;
    }

    @Override
    public T create(T item) {
        if (item.isNew()) {
            synchronized (this) {
                long id = generateId();
                item.setId(id);
                entries.put(id, item);
                return SerializationUtils.clone(item);
            }
        }
        throw new IllegalArgumentException("Can't create item with ID because it should not be new.");
    }

    @Override
    public void delete(T item) {
        if (!item.isNew()) {
            synchronized (this) {
                entries.remove(item.getId());
            }
        }
    }

    @Override
    public T update(T item) {
        if (!item.isNew()) {
            synchronized (this) {
                entries.put(item.getId(), item);
                return SerializationUtils.clone(item);
            }
        }
        throw new IllegalArgumentException("Can't update item without ID because it should be new element.");
    }

    @Override
    public T find(long id) {
        return entries.get(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<T>(entries.values());
    }

}