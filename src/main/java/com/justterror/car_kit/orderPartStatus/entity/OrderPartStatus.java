package com.justterror.car_kit.orderPartStatus.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="OrderPartStatus")
public class OrderPartStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Key")
    @NotNull
    private String  key;

    @Column(name = "Title")
    @NotNull
    private String  title;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

        if (ID != that.ID) return false;
        if (!key.equals(that.key)) return false;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + key.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrderPartStatus{" +
                "ID=" + ID +
                ", key='" + key + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
