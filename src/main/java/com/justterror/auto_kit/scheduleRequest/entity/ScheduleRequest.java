package com.justterror.auto_kit.scheduleRequest.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="ScheduleRequest")
public class ScheduleRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "IP")
    @NotNull
    private String iP;

    @Column(name = "Mileage")
    @NotNull
    private long mileage;

    @Column(name = "VINID")
    @NotNull
    private long vinID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getiP() {
        return iP;
    }

    public void setiP(String iP) {
        this.iP = iP;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public long getVinID() {
        return vinID;
    }

    public void setVinID(long vinID) {
        this.vinID = vinID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleRequest that = (ScheduleRequest) o;

        if (ID != that.ID) return false;
        if (mileage != that.mileage) return false;
        if (vinID != that.vinID) return false;
        return iP.equals(that.iP);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + iP.hashCode();
        result = 31 * result + (int) (mileage ^ (mileage >>> 32));
        result = 31 * result + (int) (vinID ^ (vinID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ScheduleRequest{" +
                "ID=" + ID +
                ", iP='" + iP + '\'' +
                ", mileage=" + mileage +
                ", vinID=" + vinID +
                '}';
    }
}
