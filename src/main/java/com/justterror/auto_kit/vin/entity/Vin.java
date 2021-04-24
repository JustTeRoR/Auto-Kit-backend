package com.justterror.auto_kit.vin.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vin", schema = "public")
public class Vin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="vin")
    @NotNull
    private String vin;

    @Column(name="model_year_id")
    @NotNull
    private long modelYearID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

        Vin vin1 = (Vin) o;

        if (id != vin1.id) return false;
        if (modelYearID != vin1.modelYearID) return false;
        return vin.equals(vin1.vin);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + vin.hashCode();
        result = 31 * result + (int) (modelYearID ^ (modelYearID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Vin{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", modelYearID=" + modelYearID +
                '}';
    }
}
