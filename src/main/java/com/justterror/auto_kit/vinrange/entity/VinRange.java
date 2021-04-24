package com.justterror.auto_kit.vinrange.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vin_range", schema = "public")
public class VinRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name="model_year_id")
    @NotNull
    private String modelYearID;

    @Column(name="vin_mask")
    @NotNull
    private String vinMask;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModelYearID() {
        return modelYearID;
    }

    public void setModelYearID(String modelYearID) {
        this.modelYearID = modelYearID;
    }

    public String getVinMask() {
        return vinMask;
    }

    public void setVinMask(String vinMask) {
        this.vinMask = vinMask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VinRange vinRange = (VinRange) o;

        if (!id.equals(vinRange.id)) return false;
        if (!modelYearID.equals(vinRange.modelYearID)) return false;
        return vinMask.equals(vinRange.vinMask);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + modelYearID.hashCode();
        result = 31 * result + vinMask.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "VinRange{" +
                "id='" + id + '\'' +
                ", modelYearID='" + modelYearID + '\'' +
                ", vinMask='" + vinMask + '\'' +
                '}';
    }
}
