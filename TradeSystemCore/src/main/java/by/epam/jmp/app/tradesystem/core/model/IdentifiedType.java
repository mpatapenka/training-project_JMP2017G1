package by.epam.jmp.app.tradesystem.core.model;

import java.io.Serializable;

public abstract class IdentifiedType implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IdentifiedType{" +
                "id=" + id +
                '}';
    }

}