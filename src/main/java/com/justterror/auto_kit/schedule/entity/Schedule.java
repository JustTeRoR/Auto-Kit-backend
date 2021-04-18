package com.justterror.auto_kit.schedule.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="Schedule")
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Mileage")
    @NotNull
    private long mileage;

    @Column(name = "PartModelYearID")
    @NotNull
    private long partModelYearID;

    @Column(name = "ModelYearID")
    @NotNull
    private long modelYearID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public long getPartModelYearID() {
        return partModelYearID;
    }

    public void setPartModelYearID(long partModelYearID) {
        this.partModelYearID = partModelYearID;
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

        Schedule schedule = (Schedule) o;

        if (ID != schedule.ID) return false;
        if (mileage != schedule.mileage) return false;
        if (partModelYearID != schedule.partModelYearID) return false;
        return modelYearID == schedule.modelYearID;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (mileage ^ (mileage >>> 32));
        result = 31 * result + (int) (partModelYearID ^ (partModelYearID >>> 32));
        result = 31 * result + (int) (modelYearID ^ (modelYearID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "ID=" + ID +
                ", mileage=" + mileage +
                ", partModelYearID=" + partModelYearID +
                ", modelYearID=" + modelYearID +
                '}';
    }
}
