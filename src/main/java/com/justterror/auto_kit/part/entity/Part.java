package com.justterror.auto_kit.part.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="Part")
public class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Quantity")
    @NotNull
    private long quantity;

    @Column(name = "MeasureID")
    @NotNull
    private long measureID;

    @Column(name = "MakeID")
    @NotNull
    private long makeID;

    @Column(name = "PartTypeID")
    @NotNull
    private long partTypeID;

    @Column(name = "IsOEM")
    @NotNull
    private boolean isOEM;

    @Column(name = "LastPurchasePrice")
    @NotNull
    private BigDecimal lastPurchasePrice;

    @Column(name = "LastDeliveryTime")
    @NotNull
    private LocalDateTime lastDeliveryTime;

    @Column(name = "SerialNumber")
    @NotNull
    private String serialNumber;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getMeasureID() {
        return measureID;
    }

    public void setMeasureID(long measureID) {
        this.measureID = measureID;
    }

    public long getMakeID() {
        return makeID;
    }

    public void setMakeID(long makeID) {
        this.makeID = makeID;
    }

    public long getPartTypeID() {
        return partTypeID;
    }

    public void setPartTypeID(long partTypeID) {
        this.partTypeID = partTypeID;
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

        if (ID != part.ID) return false;
        if (quantity != part.quantity) return false;
        if (measureID != part.measureID) return false;
        if (makeID != part.makeID) return false;
        if (partTypeID != part.partTypeID) return false;
        if (isOEM != part.isOEM) return false;
        if (!lastPurchasePrice.equals(part.lastPurchasePrice)) return false;
        if (!lastDeliveryTime.equals(part.lastDeliveryTime)) return false;
        return serialNumber.equals(part.serialNumber);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (quantity ^ (quantity >>> 32));
        result = 31 * result + (int) (measureID ^ (measureID >>> 32));
        result = 31 * result + (int) (makeID ^ (makeID >>> 32));
        result = 31 * result + (int) (partTypeID ^ (partTypeID >>> 32));
        result = 31 * result + (isOEM ? 1 : 0);
        result = 31 * result + lastPurchasePrice.hashCode();
        result = 31 * result + lastDeliveryTime.hashCode();
        result = 31 * result + serialNumber.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Part{" +
                "ID=" + ID +
                ", quantity=" + quantity +
                ", measureID=" + measureID +
                ", makeID=" + makeID +
                ", partTypeID=" + partTypeID +
                ", isOEM=" + isOEM +
                ", lastPurchasePrice=" + lastPurchasePrice +
                ", lastDeliveryTime=" + lastDeliveryTime +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
