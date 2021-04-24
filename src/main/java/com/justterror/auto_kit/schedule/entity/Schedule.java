package com.justterror.auto_kit.schedule.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "schedule", schema = "public" )
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="mileage")
    @NotNull
    private long mileage;

    @Column(name="part_model_year_id")
    @NotNull
    private long partModelYearID;

    @Column(name="model_year_id")
    @NotNull
    private long modelYearID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

        if (id != schedule.id) return false;
        if (mileage != schedule.mileage) return false;
        if (partModelYearID != schedule.partModelYearID) return false;
        return modelYearID == schedule.modelYearID;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (mileage ^ (mileage >>> 32));
        result = 31 * result + (int) (partModelYearID ^ (partModelYearID >>> 32));
        result = 31 * result + (int) (modelYearID ^ (modelYearID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", mileage=" + mileage +
                ", partModelYearID=" + partModelYearID +
                ", modelYearID=" + modelYearID +
                '}';
    }
}
