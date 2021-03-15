package com.justterror.car_kit.measure.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="Measure")
public class Measure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "TrimName")
    @NotNull
    private String trimName;

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

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measure measure = (Measure) o;

        if (ID != measure.ID) return false;
        if (!name.equals(measure.name)) return false;
        return trimName.equals(measure.trimName);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + trimName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", trimName='" + trimName + '\'' +
                '}';
    }
}
