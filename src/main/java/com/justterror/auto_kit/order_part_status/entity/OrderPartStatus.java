package com.justterror.auto_kit.order_part_status.entity;

import com.justterror.auto_kit.order_status.entity.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_part_status", schema = "public")
public class OrderPartStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="key")
    @NotNull
    private String key;

    //TODO:: to think about enum for this field...

    @Column(name="title")
    @NotNull
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPartStatus that = (OrderPartStatus) o;

        if (id != that.id) return false;
        if (!key.equals(that.key)) return false;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + key.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderPartStatus{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
