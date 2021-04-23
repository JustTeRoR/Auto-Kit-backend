package com.justterror.auto_kit.part_model_year.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "part_model_year", schema = "public")
public class PartModelYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="model_year_id")
    @NotNull
    private long modelYearId;

    @Column(name="part_type_id")
    @NotNull
    private long partTypeId;

    @Column(name="measure_id")
    @NotNull
    private long measureId;

    @Column(name="oem_part_id")
    @NotNull
    private long oemPartId;

    @Column(name="labour")
    @NotNull
    private int labour;

    @Column(name="quantity")
    @NotNull
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getModelYearId() {
        return modelYearId;
    }

    public void setModelYearId(long modelYearId) {
        this.modelYearId = modelYearId;
    }

    public long getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(long partTypeId) {
        this.partTypeId = partTypeId;
    }

    public long getMeasureId() {
        return measureId;
    }

    public void setMeasureId(long measureId) {
        this.measureId = measureId;
    }

    public long getOemPartId() {
        return oemPartId;
    }

    public void setOemPartId(long oemPartId) {
        this.oemPartId = oemPartId;
    }

    public int getLabour() {
        return labour;
    }

    public void setLabour(int labour) {
        this.labour = labour;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartModelYear that = (PartModelYear) o;

        if (id != that.id) return false;
        if (modelYearId != that.modelYearId) return false;
        if (partTypeId != that.partTypeId) return false;
        if (measureId != that.measureId) return false;
        if (oemPartId != that.oemPartId) return false;
        if (labour != that.labour) return false;
        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (modelYearId ^ (modelYearId >>> 32));
        result = 31 * result + (int) (partTypeId ^ (partTypeId >>> 32));
        result = 31 * result + (int) (measureId ^ (measureId >>> 32));
        result = 31 * result + (int) (oemPartId ^ (oemPartId >>> 32));
        result = 31 * result + labour;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "PartModelYear{" +
                "id=" + id +
                ", modelYearId=" + modelYearId +
                ", partTypeId=" + partTypeId +
                ", measureId=" + measureId +
                ", oemPartId=" + oemPartId +
                ", labour=" + labour +
                ", quantity=" + quantity +
                '}';
    }
}
