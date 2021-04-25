package com.justterror.auto_kit.order_part.entity;

import com.justterror.auto_kit.order.entity.Order;
import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "order_part", schema = "public")
public class OrderPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="order_id")
    @NotNull
    private long orderId;

    @Column(name="order_part_status_id")
    @NotNull
    private long orderPartStatusId;

    @Column(name="part_provider_id")
    @NotNull
    private long partProviderId;

    @Column(name="purchase_price")
    @NotNull
    private BigDecimal purchasePrice;

    @Column(name="price")
    @NotNull
    private BigDecimal price;

    @Column(name="labour_price")
    @NotNull
    private BigDecimal labourPrice;

    @Column(name="count")
    @NotNull
    private int count;

    @Column(name="part_id")
    @NotNull
    private long partId;

    public OrderPart(long orderId, long orderPartStatusId, long partProviderId, BigDecimal purchasePrice, BigDecimal price, BigDecimal labourPrice, int count, long partId) {
        this.orderId = orderId;
        this.orderPartStatusId = orderPartStatusId;
        this.partProviderId = partProviderId;
        this.purchasePrice = purchasePrice;
        this.price = price;
        this.labourPrice = labourPrice;
        this.count = count;
        this.partId = partId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderPartStatusId() {
        return orderPartStatusId;
    }

    public void setOrderPartStatusId(long orderPartStatusId) {
        this.orderPartStatusId = orderPartStatusId;
    }

    public long getPartProviderId() {
        return partProviderId;
    }

    public void setPartProviderId(long partProviderId) {
        this.partProviderId = partProviderId;
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

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPart orderPart = (OrderPart) o;

        if (id != orderPart.id) return false;
        if (orderId != orderPart.orderId) return false;
        if (orderPartStatusId != orderPart.orderPartStatusId) return false;
        if (partProviderId != orderPart.partProviderId) return false;
        if (count != orderPart.count) return false;
        if (partId != orderPart.partId) return false;
        if (!purchasePrice.equals(orderPart.purchasePrice)) return false;
        if (!price.equals(orderPart.price)) return false;
        return labourPrice.equals(orderPart.labourPrice);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (orderPartStatusId ^ (orderPartStatusId >>> 32));
        result = 31 * result + (int) (partProviderId ^ (partProviderId >>> 32));
        result = 31 * result + purchasePrice.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + labourPrice.hashCode();
        result = 31 * result + count;
        result = 31 * result + (int) (partId ^ (partId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OrderPart{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderPartStatusId=" + orderPartStatusId +
                ", partProviderId=" + partProviderId +
                ", purchasePrice=" + purchasePrice +
                ", price=" + price +
                ", labourPrice=" + labourPrice +
                ", count=" + count +
                ", partId=" + partId +
                '}';
    }
}
