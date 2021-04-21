package com.justterror.auto_kit.schedule_request.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "schedule_request", schema = "public")
public class ScheduleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="mileage")
    @NotNull
    private int mileage;

    @Column(name="vin_id")
    @NotNull
    private long vinID;

    @Column(name="ip")
    @NotNull
    private String ip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public long getVinID() {
        return vinID;
    }

    public void setVinID(long vinID) {
        this.vinID = vinID;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleRequest that = (ScheduleRequest) o;

        if (id != that.id) return false;
        if (mileage != that.mileage) return false;
        if (vinID != that.vinID) return false;
        return ip.equals(that.ip);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + mileage;
        result = 31 * result + (int) (vinID ^ (vinID >>> 32));
        result = 31 * result + ip.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ScheduleRequest{" +
                "id=" + id +
                ", mileage=" + mileage +
                ", vinID=" + vinID +
                ", ip='" + ip + '\'' +
                '}';
    }
}
