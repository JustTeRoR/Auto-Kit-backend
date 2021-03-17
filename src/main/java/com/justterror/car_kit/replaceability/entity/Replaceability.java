package com.justterror.car_kit.replaceability.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="Replaceability")
public class Replaceability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "PartID")
    @NotNull
    private long partID;

    @Column(name = "PartModelYearID")
    @NotNull
    private long partModelYearID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        this.partID = partID;
    }

    public long getPartModelYearID() {
        return partModelYearID;
    }

    public void setPartModelYearID(long partModelYearID) {
        this.partModelYearID = partModelYearID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Replaceability that = (Replaceability) o;

        if (ID != that.ID) return false;
        if (partID != that.partID) return false;
        return partModelYearID == that.partModelYearID;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (partID ^ (partID >>> 32));
        result = 31 * result + (int) (partModelYearID ^ (partModelYearID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Replaceability{" +
                "ID=" + ID +
                ", partID=" + partID +
                ", partModelYearID=" + partModelYearID +
                '}';
    }
}
