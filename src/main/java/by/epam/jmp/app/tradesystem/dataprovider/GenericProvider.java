package by.epam.jmp.app.tradesystem.dataprovider;

import by.epam.jmp.app.tradesystem.model.IdentifiedType;

import java.util.List;

/**
 * Generic provider with base operations
 *
 * @param <T> - Type of data which will be provided
 */
public interface GenericProvider<T extends IdentifiedType> {

    /**
     * Create item
     *
     * @param item - Element for creating
     * @return Element which was created
     */
    T create(T item);

    /**
     * Delete item
     *
     * @param item - Element for deleting
     */
    void delete(T item);

    /**
     * Update item (all fields will be rewrote, except ID field
     *
     * @param item - Element for update
     * @return Updated item
     */
    T update(T item);

    /**
     * Find item by ID
     *
     * @param id - ID value
     * @return Item with specified ID
     */
    T find(long id);

    /**
     * Get all items
     *
     * @return List with all items
     */
    List<T> getAll();

}