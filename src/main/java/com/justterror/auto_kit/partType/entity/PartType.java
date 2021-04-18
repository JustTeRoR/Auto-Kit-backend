package com.justterror.auto_kit.partType.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="PartType")
public class PartType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "Description")
    @NotNull
    private String description;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartType partType = (PartType) o;

        if (ID != partType.ID) return false;
        if (!name.equals(partType.name)) return false;
        return description.equals(partType.description);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PartType{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
