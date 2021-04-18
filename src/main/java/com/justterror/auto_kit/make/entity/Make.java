package com.justterror.auto_kit.make.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="Make")
public class Make implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "VpicID")
    @NotNull
    private long vPicID;

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

    public long getvPicID() {
        return vPicID;
    }

    public void setvPicID(long vPicID) {
        this.vPicID = vPicID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Make make = (Make) o;

        if (ID != make.ID) return false;
        if (vPicID != make.vPicID) return false;
        return name.equals(make.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (vPicID ^ (vPicID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Make{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", vPicID=" + vPicID +
                '}';
    }
}
