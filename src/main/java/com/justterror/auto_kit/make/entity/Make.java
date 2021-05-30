package com.justterror.auto_kit.make.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "make", schema = "public")
public class Make {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="vpic_id")
    private long vpicId;

    @Column(name = "name")
    @NotNull
    private String name;

    public Make() {}
    public Make(long id, long vpicId, String name) {
        this.id = id;
        this.name = name;
        this.vpicId = vpicId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVpicId() {
        return vpicId;
    }

    public void setVpicId(long vpicId) {
        this.vpicId = vpicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Make make = (Make) o;

        if (id != make.id) return false;
        if (vpicId != make.vpicId) return false;
        return name.equals(make.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (vpicId ^ (vpicId >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Make{" +
                "id=" + id +
                ", vpicId=" + vpicId +
                ", name='" + name + '\'' +
                '}';
    }
}
