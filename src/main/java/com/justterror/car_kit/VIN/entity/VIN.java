package com.justterror.car_kit.VIN.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="VIN")
public class VIN implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "VIN")
    @NotNull
    private String vin;

    @Column(name = "ModelYearID")
    @NotNull
    private long modelYearID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
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

        VIN vin1 = (VIN) o;

        if (ID != vin1.ID) return false;
        if (modelYearID != vin1.modelYearID) return false;
        return vin.equals(vin1.vin);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + vin.hashCode();
        result = 31 * result + (int) (modelYearID ^ (modelYearID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "VIN{" +
                "ID=" + ID +
                ", vin='" + vin + '\'' +
                ", modelYearID=" + modelYearID +
                '}';
    }
}
