package com.justterror.auto_kit.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "model", schema = "public")
public class Model {

    @Id
    private long id;

    @Column(name="make_id")
    private long makeId;

    @Column(name="vpic_id")
    private long vpicId;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="trim_name")
    private String trimName;

    public Model(long id, long makeId, long vpicId, String name, String trimName) {
        this.id = id;
        this.makeId = makeId;
        this.vpicId = vpicId;
        this.name = name;
        this. trimName = trimName;
    }

    public Model() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMakeId() {
        return makeId;
    }

    public void setMakeId(long makeId) {
        this.makeId = makeId;
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

        Model model = (Model) o;

        if (id != model.id) return false;
        if (makeId != model.makeId) return false;
        if (vpicId != model.vpicId) return false;
        if (!name.equals(model.name)) return false;
        return trimName != null ? trimName.equals(model.trimName) : model.trimName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (makeId ^ (makeId >>> 32));
        result = 31 * result + (int) (vpicId ^ (vpicId >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (trimName != null ? trimName.hashCode() : 0);
        return result;
    }
}
