package com.justterror.car_kit.partModelYear.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="PartModelYear")
public class PartModelYear implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "ModelYearID")
    @NotNull
    private long modelYearID;

    @Column(name = "PartTypeID")
    @NotNull
    private long partTypeID;

    @Column(name = "MeasureID")
    @NotNull
    private long measureID;

    @Column(name = "OEMPartID")
    @NotNull
    private long oemPartID;

    @Column(name = "Labour")
    @NotNull
    private long labour;

    @Column(name = "Quantity")
    @NotNull
    private long quantity;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getModelYearID() {
        return modelYearID;
    }

    public void setModelYearID(long modelYearID) {
        this.modelYearID = modelYearID;
    }

    public long getPartTypeID() {
        return partTypeID;
    }

    public void setPartTypeID(long partTypeID) {
        this.partTypeID = partTypeID;
    }

    public long getMeasureID() {
        return measureID;
    }

    public void setMeasureID(long measureID) {
        this.measureID = measureID;
    }

    public long getOemPartID() {
        return oemPartID;
    }

    public void setOemPartID(long oemPartID) {
        this.oemPartID = oemPartID;
    }

    public long getLabour() {
        return labour;
    }

    public void setLabour(long labour) {
        this.labour = labour;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartModelYear that = (PartModelYear) o;

        if (ID != that.ID) return false;
        if (modelYearID != that.modelYearID) return false;
        if (partTypeID != that.partTypeID) return false;
        if (measureID != that.measureID) return false;
        if (oemPartID != that.oemPartID) return false;
        if (labour != that.labour) return false;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (modelYearID ^ (modelYearID >>> 32));
        result = 31 * result + (int) (partTypeID ^ (partTypeID >>> 32));
        result = 31 * result + (int) (measureID ^ (measureID >>> 32));
        result = 31 * result + (int) (oemPartID ^ (oemPartID >>> 32));
        result = 31 * result + (int) (labour ^ (labour >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PartModelYear{" +
                "ID=" + ID +
                ", modelYearID=" + modelYearID +
                ", partTypeID=" + partTypeID +
                ", measureID=" + measureID +
                ", oemPartID=" + oemPartID +
                ", labour=" + labour +
                ", quantity=" + quantity +
                '}';
    }
}
