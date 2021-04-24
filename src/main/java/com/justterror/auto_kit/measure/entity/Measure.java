package com.justterror.auto_kit.measure.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "measure", schema = "public")
public class Measure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="trim_name")
    private String trimName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measure measure = (Measure) o;

        if (id != measure.id) return false;
        if (!name.equals(measure.name)) return false;
        return trimName != null ? trimName.equals(measure.trimName) : measure.trimName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (trimName != null ? trimName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Measure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trimName='" + trimName + '\'' +
                '}';
    }
}
