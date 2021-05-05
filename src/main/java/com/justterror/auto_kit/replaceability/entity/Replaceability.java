package com.justterror.auto_kit.replaceability.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "replaceability", schema = "public")
public class Replaceability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="part_id1")
    @NotNull
    private long partId1;

    @Column(name="part_model_year_id")
    @NotNull
    private long partModelYearId;

    @Column(name="part_id2")
    @NotNull
    private long partId2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPartId1() {
        return partId1;
    }

    public void setPartId1(long partId1) {
        this.partId1 = partId1;
    }

    public long getPartModelYearId() {
        return partModelYearId;
    }

    public void setPartModelYearId(long partModelYearId) {
        this.partModelYearId = partModelYearId;
    }

    public long getPartId2() {
        return partId2;
    }

    public void setPartId2(long partId2) {
        this.partId2 = partId2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Replaceability that = (Replaceability) o;

        if (id != that.id) return false;
        if (partId1 != that.partId1) return false;
        if (partModelYearId != that.partModelYearId) return false;
        return partId2 == that.partId2;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (partId1 ^ (partId1 >>> 32));
        result = 31 * result + (int) (partModelYearId ^ (partModelYearId >>> 32));
        result = 31 * result + (int) (partId2 ^ (partId2 >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Replaceability{" +
                "id=" + id +
                ", partId1=" + partId1 +
                ", partModelYearId=" + partModelYearId +
                ", partId2=" + partId2 +
                '}';
    }
}
