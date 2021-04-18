package com.justterror.auto_kit.partProvider.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="PartProvider")
public class PartProvider implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "Name")
    @NotNull
    private String name;

    @Column(name = "URL")
    @NotNull
    private String url;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartProvider that = (PartProvider) o;

        if (ID != that.ID) return false;
        if (!name.equals(that.name)) return false;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + url.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PartProvider{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
