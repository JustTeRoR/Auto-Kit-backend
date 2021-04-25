package com.justterror.auto_kit.replaceability.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "replaceability", schema = "public")
public class Replaceability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="part_id")
    @NotNull
    private long partId;

    @Column(name="part_model_year_id")
    @NotNull
    private long partModelYearId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public long getPartModelYearId() {
        return partModelYearId;
    }

    public void setPartModelYearId(long partModelYearId) {
        this.partModelYearId = partModelYearId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Replaceability that = (Replaceability) o;

        if (id != that.id) return false;
        if (partId != that.partId) return false;
        return partModelYearId == that.partModelYearId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (partId ^ (partId >>> 32));
        result = 31 * result + (int) (partModelYearId ^ (partModelYearId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Replaceability{" +
                "id=" + id +
                ", partId=" + partId +
                ", partModelYearId=" + partModelYearId +
                '}';
    }
}
