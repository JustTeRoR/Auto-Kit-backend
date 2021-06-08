package com.justterror.auto_kit.part.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "part", schema = "public")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="quantity")
    @NotNull
    private int quantity;

    @Column(name="measure_id")
    @NotNull
    private long measureId;

    @Column(name="make_id")
    @NotNull
    private long makeId;

    @Column(name="part_type_id")
    @NotNull
    private long partTypeId;

    @Column(name="is_oem")
    @NotNull
    private boolean isOEM;

    @Column(name="last_purchase_price")
    @NotNull
    private BigDecimal lastPurchasePrice;

    @Column(name="last_delivery_time")
    @NotNull
    private LocalDateTime lastDeliveryTime;

    @Column(name="serial_number")
    @NotNull
    private String serialNumber;



    public Part(int quantity, long measureId, long makeId, long partTypeId, boolean isOEM, BigDecimal lastPurchasePrice, LocalDateTime lastDeliveryTime, String serialNumber) {
        this.quantity = quantity;
        this.measureId = measureId;
        this.makeId = makeId;
        this.partTypeId = partTypeId;
        this.isOEM = isOEM;
        this.lastPurchasePrice = lastPurchasePrice;
        this.lastDeliveryTime = lastDeliveryTime;
        this.serialNumber = serialNumber;
    }

    public Part () {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getMeasureId() {
        return measureId;
    }

    public void setMeasureId(long measureId) {
        this.measureId = measureId;
    }

    public long getMakeId() {
        return makeId;
    }

    public void setMakeId(long makeId) {
        this.makeId = makeId;
    }

    public long getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(long partTypeId) {
        this.partTypeId = partTypeId;
    }

    public boolean isOEM() {
        return isOEM;
    }

    public void setOEM(boolean OEM) {
        isOEM = OEM;
    }

    public BigDecimal getLastPurchasePrice() {
        return lastPurchasePrice;
    }

    public void setLastPurchasePrice(BigDecimal lastPurchasePrice) {
        this.lastPurchasePrice = lastPurchasePrice;
    }

    public LocalDateTime getLastDeliveryTime() {
        return lastDeliveryTime;
    }

    public void setLastDeliveryTime(LocalDateTime lastDeliveryTime) {
        this.lastDeliveryTime = lastDeliveryTime;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        if (id != part.id) return false;
        if (quantity != part.quantity) return false;
        if (measureId != part.measureId) return false;
        if (makeId != part.makeId) return false;
        if (partTypeId != part.partTypeId) return false;
        if (isOEM != part.isOEM) return false;
        if (!lastPurchasePrice.equals(part.lastPurchasePrice)) return false;
        if (!lastDeliveryTime.equals(part.lastDeliveryTime)) return false;
        return serialNumber.equals(part.serialNumber);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (int) (measureId ^ (measureId >>> 32));
        result = 31 * result + (int) (makeId ^ (makeId >>> 32));
        result = 31 * result + (int) (partTypeId ^ (partTypeId >>> 32));
        result = 31 * result + (isOEM ? 1 : 0);
        result = 31 * result + lastPurchasePrice.hashCode();
        result = 31 * result + lastDeliveryTime.hashCode();
        result = 31 * result + serialNumber.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", measureId=" + measureId +
                ", makeId=" + makeId +
                ", partTypeId=" + partTypeId +
                ", isOEM=" + isOEM +
                ", lastPurchasePrice=" + lastPurchasePrice +
                ", lastDeliveryTime=" + lastDeliveryTime +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
