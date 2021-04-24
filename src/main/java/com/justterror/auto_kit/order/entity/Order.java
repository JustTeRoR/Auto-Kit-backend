package com.justterror.auto_kit.order.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order", schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="order_status_id")
    @NotNull
    private long orderStatusId;

    @Column(name="price")
    @NotNull
    private BigDecimal price;

    @Column(name="creation_date")
    @NotNull
    private Date creationDate;

    @Column(name="change_date")
    @NotNull
    private Date changeDate;

    @Column(name="user_id")
    @NotNull
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (orderStatusId != order.orderStatusId) return false;
        if (userId != order.userId) return false;
        if (!price.equals(order.price)) return false;
        if (!creationDate.equals(order.creationDate)) return false;
        return changeDate.equals(order.changeDate);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (orderStatusId ^ (orderStatusId >>> 32));
        result = 31 * result + price.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + changeDate.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderStatusId=" + orderStatusId +
                ", price=" + price +
                ", creationDate=" + creationDate +
                ", changeDate=" + changeDate +
                ", userId=" + userId +
                '}';
    }
}
