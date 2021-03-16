package com.justterror.car_kit.orderPart.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="OrderPart")
public class OrderPart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "OrderID")
    @NotNull
    private long orderID;

    @Column(name = "OrderPartStatusID")
    @NotNull
    private long orderPartStatusID;

    @Column(name = "PartProviderID")
    @NotNull
    private long partProviderID;

    @Column(name = "PurchasePrice")
    @NotNull
    private BigDecimal purchasePrice;

    @Column(name = "Price")
    @NotNull
    private BigDecimal price;

    @Column(name = "LabourPrice")
    @NotNull
    private BigDecimal labourPrice;

    @Column(name = "Count")
    @NotNull
    private int count;

    @Column(name = "PartID")
    @NotNull
    private long partID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getOrderPartStatusID() {
        return orderPartStatusID;
    }

    public void setOrderPartStatusID(long orderPartStatusID) {
        this.orderPartStatusID = orderPartStatusID;
    }

    public long getPartProviderID() {
        return partProviderID;
    }

    public void setPartProviderID(long partProviderID) {
        this.partProviderID = partProviderID;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLabourPrice() {
        return labourPrice;
    }

    public void setLabourPrice(BigDecimal labourPrice) {
        this.labourPrice = labourPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        this.partID = partID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPart orderPart = (OrderPart) o;

        if (ID != orderPart.ID) return false;
        if (orderID != orderPart.orderID) return false;
        if (orderPartStatusID != orderPart.orderPartStatusID) return false;
        if (partProviderID != orderPart.partProviderID) return false;
        if (count != orderPart.count) return false;
        if (partID != orderPart.partID) return false;
        if (!purchasePrice.equals(orderPart.purchasePrice)) return false;
        if (!price.equals(orderPart.price)) return false;
        return labourPrice.equals(orderPart.labourPrice);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (orderID ^ (orderID >>> 32));
        result = 31 * result + (int) (orderPartStatusID ^ (orderPartStatusID >>> 32));
        result = 31 * result + (int) (partProviderID ^ (partProviderID >>> 32));
        result = 31 * result + purchasePrice.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + labourPrice.hashCode();
        result = 31 * result + count;
        result = 31 * result + (int) (partID ^ (partID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OrderPart{" +
                "ID=" + ID +
                ", orderID=" + orderID +
                ", orderPartStatusID=" + orderPartStatusID +
                ", partProviderID=" + partProviderID +
                ", purchasePrice=" + purchasePrice +
                ", price=" + price +
                ", labourPrice=" + labourPrice +
                ", count=" + count +
                ", partID=" + partID +
                '}';
    }
}
