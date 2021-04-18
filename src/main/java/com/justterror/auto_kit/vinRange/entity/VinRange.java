package com.justterror.auto_kit.vinRange.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="VINRange")
public class VinRange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "VinMask")
    @NotNull
    private String vinMask;

    @Column(name = "ModelYearID")
    @NotNull
    private long modelYearID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getVinMask() {
        return vinMask;
    }

    public void setVinMask(String vinMask) {
        this.vinMask = vinMask;
    }

    public long getModelYearID() {
        return modelYearID;
    }

    public void setModelYearID(long modelYearID) {
        this.modelYearID = modelYearID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VinRange vinRange = (VinRange) o;

        if (ID != vinRange.ID) return false;
        if (modelYearID != vinRange.modelYearID) return false;
        return vinMask.equals(vinRange.vinMask);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + vinMask.hashCode();
        result = 31 * result + (int) (modelYearID ^ (modelYearID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "VinRange{" +
                "ID=" + ID +
                ", vinMask='" + vinMask + '\'' +
                ", modelYearID=" + modelYearID +
                '}';
    }
}
