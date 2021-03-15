package com.justterror.car_kit.order.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="Order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "OrderStatusID")
    @NotNull
    private long orderStatusID;

    @Column(name = "Price")
    @NotNull
    private BigDecimal price;

    @Column(name = "CreationDate")
    @NotNull
    private LocalDate creationDate;

    @Column(name = "ChangeDate")
    @NotNull
    private LocalDate changeDate;

    @Column(name = "CustomerID")
    @NotNull
    private long customerID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getOrderStatusID() {
        return orderStatusID;
    }

    public void setOrderStatusID(long orderStatusID) {
        this.orderStatusID = orderStatusID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDate changeDate) {
        this.changeDate = changeDate;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (ID != order.ID) return false;
        if (orderStatusID != order.orderStatusID) return false;
        if (customerID != order.customerID) return false;
        if (!price.equals(order.price)) return false;
        if (!creationDate.equals(order.creationDate)) return false;
        return changeDate.equals(order.changeDate);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (orderStatusID ^ (orderStatusID >>> 32));
        result = 31 * result + price.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + changeDate.hashCode();
        result = 31 * result + (int) (customerID ^ (customerID >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", orderStatusID=" + orderStatusID +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", changeDate=" + changeDate +
                ", customerID=" + customerID +
                '}';
    }
}
